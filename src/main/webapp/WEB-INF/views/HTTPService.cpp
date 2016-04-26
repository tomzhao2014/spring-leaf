// HTTPService.cpp: implementation of the CHTTPService class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
#include "../include/HTTPService.h"
#include "../include/Log.h"


extern CLog g_Log;

#define MAX_EPOLL_EVENTS 200

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

CHTTPService::CHTTPService()
{
	m_strServer			= "qiktone";
	m_hServiceThread	= 0;
	m_nAcceptExpires	= 80;
	m_nExpires			= 90;
	m_nMinExpires		= 60;
	m_bRunning			= false;
	m_nCPUCores			= 1;
	m_nServicePort		= 8888;
	
	m_nTimeout = 1200;
	
	m_s					= INVALID_SOCKET;

	
#ifdef WIN32
	m_strLogFile		= "./httpservice.log";
#else
	m_strLogFile		= "/var/httpservice.log";
#endif

	srand((uint32)time(NULL));
	m_nClientCount = rand();
	m_nPresentationID = 1;
	m_nFileThreadCount = 0;
	m_nFileThreads = 1;
	
	m_MIMETable.AddItem("html","text/html");
	m_MIMETable.AddItem("kgi","text/html");
	m_MIMETable.AddItem("htm","text/html");
	m_MIMETable.AddItem("css","text/css");
	m_MIMETable.AddItem("txt","text/plain");
	m_MIMETable.AddItem("jpg","image/jpeg");
	m_MIMETable.AddItem("jpeg","image/jpeg");
	m_MIMETable.AddItem("ico","image/x-icon");
	m_MIMETable.AddItem("bmp","image/bmp");
	m_MIMETable.AddItem("gif","image/gif");
	m_MIMETable.AddItem("zip","application/zip","attachment");
	m_MIMETable.AddItem("kzip","application/octet-stream","attachment");
	m_MIMETable.AddItem("msi","application/octet-stream","attachment");
	m_MIMETable.AddItem("m3u8","application/vnd.apple.mpegurl");	
}

CHTTPService::~CHTTPService()
{
	if(m_s != INVALID_SOCKET)
	{
		closesocket(m_s);
		m_s	= INVALID_SOCKET;
	}
	std::map<std::string,CHTTPUserInfo*>::iterator it;
	while(m_UserMap.size() > 0)
	{
		it = m_UserMap.begin();
		delete it->second;
		m_UserMap.erase(it);
	}
}
CHTTPClientInfo *CHTTPService::CreateClient()
{
	return new CHTTPClientInfo;
}
int32 CHTTPService::LoadConfig(const int8 *pfilename)
{
	int8 buf[1024];
	int32	sc = 1;
	FILE *fp;
	int32 cms = 0;
	fp = fopen(pfilename,"rt");
	if(fp)
	{
		int8 *p;
		while(fgets(buf,511,fp))
		{
			p = strstr(buf,";");//remark
			if(p) 
				*p = '\0';
						
			TrimLeft(buf);
			TrimRight(buf);			
			for(uint32 i=0;i<strlen(buf);i++)
			{
				if(buf[i] == '\t')buf[i] = ' ';
			}
	
			if(strncmp(buf,"http_service_ip",15) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					g_Log.printf(LOG_LEVEL_MSG,"http_service_ip=%s",p);
					m_strServiceIP = p;					
				}
			}		
			else if(strncmp(buf,"http_service_port",17) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;					
					TrimLeft(p);
					g_Log.printf(LOG_LEVEL_MSG,"http_service_port=%s",p);
					if(atoi(p) > 0)
					{
						m_nServicePort = (uint16)atoi(p);
					}
				}
			}	
			else if(strncmp(buf,"root_path",9) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					TrimRight(p);
					int n = strlen(p);
					g_Log.printf(LOG_LEVEL_MSG,"root_path=%s",p);
					if(p[n-1] != '/')
					{
						p[n] = '/';
						p[n+1] = '\0';
					}
					this->m_strDocRoot = p;	
					
				}
			}	
			
			else if(strncmp(buf,"log_file",8) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					g_Log.printf(LOG_LEVEL_MSG,"log_file=%s",p);
					m_strLogFile = p;	
					
				}
			}			
			else if(strncmp(buf,"log_level",9) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					g_Log.printf(LOG_LEVEL_MSG,"log_level=%s",p);
					g_nLogLevel = atoi(p);			
				}
			}				
			else if(strncmp(buf,"max_log_file_size",17) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					TrimRight(p);
					g_Log.printf(LOG_LEVEL_MSG,"max_log_file_size=%s",p);

					int64 sz = atol(p);
					int64 s = 1024*1024;
					s = s * 1024;
					s = s * 2;
					s -= 1024;
					sz = sz *1024*1024;
					if(sz > s)
					{
						g_Log.printf(LOG_LEVEL_WARNING,"max_log_file_size too large, pthread->m_rset size = %u",(uint32)s);
						sz = s;
					}
					CLog::m_nMaxLogFileSize = (uint32)sz;					
				}				
			}
			else if(strncmp(buf,"realm",5) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					g_Log.printf(LOG_LEVEL_MSG,"realm=%s",p);
					m_strRealm = p;					
				}
			}	
			else if(strncmp(buf,"db_ip",5) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					g_Log.printf(LOG_LEVEL_MSG,"db_ip=%s",p);
					m_strDBServerIP = p;					
				}
			}
			else if(strncmp(buf,"db_port",7) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					g_Log.printf(LOG_LEVEL_MSG,"db_port=%s",p);
					if(atoi(p)> 0)
						m_nDBServerPort = (uint16)atoi(p);
				}
			}
			else if(strncmp(buf,"db_user",7) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					g_Log.printf(LOG_LEVEL_MSG,"db_user=%s",p);
					m_strDBUserName = p;					
				}
			}
			else if(strncmp(buf,"db_passwd",9) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					g_Log.printf(LOG_LEVEL_MSG,"db_passwd=%s",p);
					m_strDBPassword = p;					
				}
			}
			
			else if(strncmp(buf,"db_name",7) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					g_Log.printf(LOG_LEVEL_MSG,"db_name=%s",p);
					m_strDBName = p;								
				}
			}
			else if(strncmp(buf,"path",4) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					int8 *pp = strstr(p," ");
					if(pp)
					{
						*pp = '\0';
						pp++;
						TrimLeft(pp);
						TrimRight(pp);
						if(strlen(p) > 0 && strlen(pp) > 0)
						{
							PathInfo *pi = new PathInfo;
							pi->m_strPath = pp;
							pi->m_strType = p;
							this->m_PathMap[pi->m_strPath] = pi;
						}
					}
				}
			}
			else if(strncmp(buf,"host",4) == 0)
			{
				p = strstr(buf,"=");
				if(p)
				{
					p++;
					TrimLeft(p);
					int8 *pp = strstr(p," ");
					if(pp)
					{
						*pp = '\0';
						pp++;
						TrimLeft(pp);
						TrimRight(pp);
						if(strlen(p) > 0 && strlen(pp) > 0)
						{
							HostInfo *pi = new HostInfo;
							pi->m_strPath = pp;
							pi->m_strHost = p;
							this->m_HostMap[pi->m_strHost] = pi;
						}
					}
				}
			}

		}
		fclose(fp);	

		m_nCPUCores = GetCPUInfo(64);
		if(m_nCPUCores <= 0 || m_nCPUCores > 32)
			m_nCPUCores = 1;
		return 0;
	}
	else
		g_Log.printf(LOG_LEVEL_MSG,"%s,%d\r\nload config file error filename=%s",__FILE__,__LINE__,pfilename);
	return -1;
}

bool CHTTPService::Startup()
{	
#ifdef WIN32
	bool iOptVal = 1;
#else
  	int32 iOptVal = 1;
#endif
	sockaddr_in addr;

	m_s = socket(AF_INET,SOCK_STREAM,0);
	if(m_s == INVALID_SOCKET)
	{
#ifndef WIN32
		g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\r\n Create rtsp socket error, %s",__FILE__,__LINE__,strerror(errno));
#else
		g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\r\n Create rtsp socket error, %d",__FILE__,__LINE__,WSAGetLastError());
#endif
		return false;
	}	

#ifdef WIN32

	if(-1 == setsockopt(m_s, SOL_SOCKET, SO_REUSEADDR,(int8*)&iOptVal, sizeof(iOptVal)))
		g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\r\nsetsockopt SO_REUSEADDR error",__FILE__,__LINE__);
#else
  	
	if(-1 == setsockopt(m_s, SOL_SOCKET, SO_REUSEADDR,(void*)&iOptVal, sizeof(iOptVal)))
		g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\r\nsetsockopt SO_REUSEADDR error, %s",__FILE__,__LINE__,strerror(errno));
#endif

	
	addr.sin_family = AF_INET;
	addr.sin_port = htons(m_nServicePort);
	if(m_strServiceIP.empty() == false)
		addr.sin_addr.s_addr = inet_addr(m_strServiceIP.c_str());
	else
		addr.sin_addr.s_addr = 0;

	if(bind(m_s,(sockaddr *)&addr,sizeof(addr)) == -1)
	{
#ifndef WIN32
		g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\r\n Bind rtsp socket error, addr=%s:%d, %s",__FILE__,__LINE__,m_strServiceIP.c_str(),m_nServicePort,strerror(errno));
#else
		g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\r\n Bind rtsp socket error, addr=%s:%d,%d",__FILE__,__LINE__,m_strServiceIP.c_str(),m_nServicePort,WSAGetLastError());
#endif

		closesocket(m_s);
		m_s = INVALID_SOCKET;
		return false;
	}
	listen(m_s,50);

	m_bRunning = true;
	uint32 i;
	for(i=0;i<m_nCPUCores;i++)
	{		
		m_Threads[i].m_bRunning = true;
		m_Threads[i].m_pServer = this;
#ifdef INCLUDE_MYSQL
		m_Threads[i].m_pDB = new CIDBMysql;
		
		//m_DBReadThreads[i].m_pDB->SetCharset(m_strDBCharset.c_str());
		//g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n connect to db %s:%d, DBNAME=%s",__FILE__,__LINE__,m_strDBServerIP.c_str(),m_nDBServerPort,m_strDBName.c_str());

		if(-1 == m_Threads[i].m_pDB->Connect(m_strDBServerIP.c_str(),m_nDBServerPort,m_strDBUserName.c_str(),m_strDBPassword.c_str(),m_strDBName.c_str()))
		{
			g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\nmysql connect error: %s, db=%s, user=%s,password=%s\n", __FILE__,__LINE__,m_Threads[i].m_pDB->GetLastError(),m_strDBName.c_str(),m_strDBUserName.c_str(),m_strDBPassword.c_str());
			return false;
		}
#endif

		pthread_create(&m_Threads[i].m_hThread,NULL,HTTPProc,&m_Threads[i]);
	}	
	/*
	for(i=0;i<m_nFileThreads;i++)
	{		
		m_FileThreads[i].m_bRunning = true;
		m_FileThreads[i].m_pServer = this;
		pthread_create(&m_FileThreads[i].m_hThread,NULL,FileProc,&m_FileThreads[i]);
	}
	*/
	g_Log.printf(LOG_LEVEL_MSG,"%s,%d\nHTTP Service Start...\n",__FILE__,__LINE__);
	return true;
}

void CHTTPService::StopService()
{
	m_bRunning = false;
	for(int32 i = 0; i < MAX_THREADS; i++)
	{
		m_Threads[i].m_bRunning = false;
		if(m_Threads[i].m_hThread)
		{
			pthread_join(m_Threads[i].m_hThread,NULL);
			pthread_detach(m_Threads[i].m_hThread);
			m_Threads[i].m_hThread = 0;
		}
	}
}

void CHTTPService::DoService()
{		
	time_t					t,t1,t2;
	int64					mseconds = 0;
	socklen_t				socklen = 0;
	int32					c = 0;
	int32					sentsocks = 0;
	SOCKET					temps;
	sockaddr_in				addr;
	CHTTPClientInfo*	pclient = NULL;		
	int32 cc = 0;
	
	

	fd_set					rset;	
	timeval					tv;

	time(&t1);
	t2 = t1;
	srand(t1%20000);
	
	max_thread_priority();

	while(m_bRunning)
	{			
		time(&t);
	//	if(t - t2 > 10)
		//	break;		
		
		tv.tv_sec = 0;
		tv.tv_usec = 100000;
		FD_ZERO(&rset);
		FD_SET(m_s,&rset);
		c = select(m_s+1,&rset,NULL,NULL,&tv);
		if(c == 0)
			continue;
		if(c < 0)
		{//error
			g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n select error,%s",__FILE__,__LINE__,strerror(errno));
			continue;
		}
		
	
		socklen = sizeof(addr);
		temps = accept(m_s,(sockaddr *)&addr,&socklen);
		if(temps != INVALID_SOCKET)
		{
			pclient = CreateClient();
			pclient->m_s = temps;
			pclient->m_nTimeout = t + m_nAcceptExpires;
			
			struct linger lg;
			int32 one = 1024*1024*2;
			setsockopt(temps,SOL_SOCKET,SO_RCVBUF,(int8 *)&one,sizeof(int32));
			one = 1024*1024;
			setsockopt(temps,SOL_SOCKET,SO_SNDBUF,(int8 *)&one,sizeof(int32));
			one = 1;
			setsockopt(temps, IPPROTO_TCP, TCP_NODELAY, (int8*)&one, sizeof(int32));
			one = 1;
			setsockopt(temps, SOL_SOCKET, SO_KEEPALIVE, (int8*)&one, sizeof(int32));
			lg.l_onoff = 1;
			lg.l_linger = 0;
			setsockopt(temps,SOL_SOCKET,SO_LINGER,(int8 *)&lg,sizeof(lg));
#if defined(WIN32)
			u_long enable = 1;    
			ioctlsocket(temps, FIONBIO, &enable);
#else
			fcntl(temps, F_SETFL, fcntl(temps, F_GETFL)|O_NONBLOCK);
#endif
			pclient->m_PeerAddr = addr;
			socklen = sizeof(addr);
			getsockname(temps,(sockaddr *)&pclient->m_LocalAddr,&socklen);
			
			pclient->m_nID = m_nClientCount++;
			Msg *pcmd = new Msg;
			pcmd->m_eMsg = msg_NewItem;
			pcmd->m_pBody = pclient;
			m_Threads[cc%m_nCPUCores].m_MsgLock.Lock();
			m_Threads[cc%m_nCPUCores].m_listMsg.push_back(pcmd);
			m_Threads[cc%m_nCPUCores].m_MsgLock.Unlock();
			pclient->m_pThreadInfo = &m_Threads[cc%m_nCPUCores];
			
			cc++;
			g_Log.printf(LOG_LEVEL_MSG,"accept a client request from = %s:%d localaddr=%s:%d",inet_ntoa(pclient->m_PeerAddr.sin_addr),ntohs(pclient->m_PeerAddr.sin_port),inet_ntoa(pclient->m_LocalAddr.sin_addr),ntohs(pclient->m_LocalAddr.sin_port));
		}
	}
}

void *CHTTPService::HTTPProc(void *argv)
{		
	time_t					t,t1,t2;
	int64					mseconds = 0;
	socklen_t				socklen = 0;
	int32					c = 0;
	int32					n;
	int32					sentsocks = 0;
	std::string				sessionid;


	std::list<Msg*>::iterator mit;
	Msg *pcmd;

	CHTTPClientInfo*	pclient = NULL,*ptempclient = NULL;
	std::map<SOCKET,CHTTPClientInfo*>::iterator cit,delcit;

	std::map<uint32,CHTTPClientInfo*>::iterator it;
	
	CHTTPThreadInfo *pthread = (CHTTPThreadInfo*)argv;

	int32 cc = 0;
	max_thread_priority();
#ifdef USE_EPOLL	
	struct epoll_event ev,*pevents = NULL;	
	pthread->m_epfd=epoll_create(MAX_EPOLL_EVENTS);
	pevents = new struct epoll_event[MAX_EPOLL_EVENTS];   
#else
	fd_set					rset,wset;	
	timeval					tv;	
#endif

	time(&t1);
	t2 = t1;
	srand(t1%20000);
	
	while(pthread->m_bRunning)
	{			
		time(&t);
	//	if(t - t2 > 30)
	//		break;
		pcmd = NULL;
		pthread->m_MsgLock.Lock();
		if((c = pthread->m_listMsg.size()) > 0)
		{
			pcmd = pthread->m_listMsg.front();
			pthread->m_listMsg.pop_front();
			g_Log.printf(LOG_LEVEL_MSG,"msg size = %d",c);
		}
		pthread->m_MsgLock.Unlock();
		
		if(pcmd)
		{
			switch(pcmd->m_eMsg)
			{
			case msg_NewItem:
				{					
					pclient = (CHTTPClientInfo *)pcmd->m_pBody;
					g_Log.printf(LOG_LEVEL_MSG,"a new client id=%u",pclient->m_nID);
					pclient->m_nUsedCount = 1;
					pthread->m_ClientIDMap[pclient->m_nID] = pclient;
					pthread->m_ClientMap[pclient->m_s] = pclient;					

					pclient->m_pNext = pthread->m_pClients;
					if(pthread->m_pClients)
						pthread->m_pClients->m_pPrev = pclient;
					pthread->m_pClients = pclient;
					if(pthread->m_pCurClient == NULL)
						pthread->m_pCurClient = pclient;
	#ifdef USE_EPOLL
					ev.data.fd = pclient->m_s;
					//设置要处理的事件类型
					ev.events=EPOLLIN ;// read. 
					//注册epoll事件
					epoll_ctl(pthread->m_epfd,EPOLL_CTL_ADD,pclient->m_s,&ev);
	#else
					if((int32)pclient->m_s > pthread->m_maxfd)
						pthread->m_maxfd = pclient->m_s;
					FD_SET(pclient->m_s,&pthread->m_rset);				
	#endif			
					
				}
				break;
			case msg_DeleteItem:
				{

				}
				break;
			}
			delete pcmd;
		}

		if(pthread->m_pCurClient)
		{
			pclient = (CHTTPClientInfo*)pthread->m_pCurClient;
			if(pclient->m_nTimeout && pclient->m_nTimeout <= t)
			{				
				pclient->m_nTimeout = 0;
				g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n the client timeout, id=%u, peeraddr=%s:%d",__FILE__,__LINE__,
					pclient->m_nID,
					inet_ntoa(pclient->m_PeerAddr.sin_addr),ntohs(pclient->m_PeerAddr.sin_port));

				pthread->m_pServer->DestroyClient(pthread,pclient);		

			}
			else
			{
				pthread->m_pCurClient = (CHTTPClientInfo*)pthread->m_pCurClient->m_pNext;
				if(pthread->m_pCurClient == NULL)
					pthread->m_pCurClient = pthread->m_pClients;
			}
		}
		else
		{
			usleep(100000);
			continue;
		}
		time(&t);
#ifdef USE_EPOLL
		c = epoll_wait(pthread->m_epfd, pevents, MAX_EPOLL_EVENTS, 100);
		if(c == 0)
			continue;
		else if(c < 0)
		{
			g_Log.printf(LOG_LEVEL_ERROR,"epoll_wait error: %s",strerror(errno));
			continue;
		}
#else
		tv.tv_sec = 0;
		tv.tv_usec = 100000;
		rset = pthread->m_rset;
		wset = pthread->m_wset;
		c = select(pthread->m_maxfd +1,&rset,&wset,NULL,&tv);
		if(c == 0)
			continue;
		if(c < 0)
		{//error
			g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n select error,%s",__FILE__,__LINE__,strerror(errno));
			continue;
		}
#endif		
		
		for(cc=0;cc < c; cc++)
		{				
			pclient = NULL;
#ifdef USE_EPOLL				
			cit = pthread->m_ClientMap.find(pevents[cc].data.fd);
			if(cit == pthread->m_ClientMap.end())
			{
				g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n socket is not in client map. socket = %d",__FILE__,__LINE__,pevents[cc].data.fd);
				continue;
			}
			pclient = (CHTTPClientInfo*)cit->second;
#else
			if(cc < rset.fd_count)
			{
				cit = pthread->m_ClientMap.find(rset.fd_array[cc]);
				if(cit != pthread->m_ClientMap.end())
				{
					pclient = (CHTTPClientInfo*)cit->second;
				}
			}
#endif			
				
#ifdef USE_EPOLL
			if(pevents[cc].events & EPOLLIN)			
#else
			if(pclient)
#endif
			{
				n = recv(pclient->m_s,&pclient->m_pRecvBuf[pclient->m_nRecvPtr],MAX_HTTP_BUF_SIZE - pclient->m_nRecvPtr,0);
				if(n > 0)
				{					
					pclient->m_nRecvPtr+=n;
					pclient->m_pRecvBuf[pclient->m_nRecvPtr] = '\0';
					g_Log.printf(LOG_LEVEL_MSG,"%s,%d\npeeraddr=%s:%d \n %s",__FILE__,__LINE__,inet_ntoa(pclient->m_PeerAddr.sin_addr),ntohs(pclient->m_PeerAddr.sin_port),pclient->m_pRecvBuf);
					
					while(1)
					{
						n = pclient->ProcessRecv();
						g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n process recv return %d",__FILE__,__LINE__,n);
						if(n == HTTP_CLIENT_RECV_ERROR)
						{
							goto removeclient;
						}
						else if (n == HTTP_CLIENT_RECV_END)
						{
							pthread->m_pServer->ProcessMessage(pthread,pclient);
							g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n got message *************************************",__FILE__,__LINE__);
						}
						else if(n == HTTP_CLIENT_RECV_GOT_CHUNK)
						{
							pthread->m_pServer->ProcessMessage(pthread,pclient);
						}
						else if(n == HTTP_CLIENT_RECV_CONTINUE || n == HTTP_CLIENT_RECV_ERROR)
							break;
					}
				}
				else if(n < 0)//error
				{
#ifdef WIN32
					n = WSAGetLastError();
					if(n == WSAEINPROGRESS || n == WSAEINTR || n == WSAEWOULDBLOCK)
#else
					if(errno == EINPROGRESS || errno == EINTR || errno ==  EAGAIN || errno == EWOULDBLOCK)
#endif
					{						
					}
					else
					{				
#ifdef WIN32
						g_Log.printf(LOG_LEVEL_DEBUG,"%s,%d\nsocket error. %u",__FILE__,__LINE__,n);
#else
						g_Log.printf(LOG_LEVEL_DEBUG,"%s,%d\nsocket error. %s",__FILE__,__LINE__,strerror(errno));
#endif
						goto removeclient;
					}
				}
				else
				{
					g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n Peer Closed.",__FILE__,__LINE__);
					goto removeclient;
				}			
			}
#ifdef USE_EPOLL
			if(pevents[cc].events & EPOLLOUT)
#else
			pclient = NULL;
			if(cc < wset.fd_count)
			{
				cit = pthread->m_ClientMap.find(wset.fd_array[cc]);
				if(cit != pthread->m_ClientMap.end())
				{
					pclient = (CHTTPClientInfo*)cit->second;
				}
			}
			if(pclient)
#endif
			{
				if(pclient->m_pSendingMsg == NULL)
				{					
					if(pclient->m_listSendMsg.size() == 0)
					{
#ifdef USE_EPOLL								
						ev.data.fd = pclient->m_s;
						ev.events = EPOLLIN; 
						epoll_ctl(pthread->m_epfd,EPOLL_CTL_MOD,pclient->m_s,&ev);
#else
						FD_CLR(pclient->m_s,&pthread->m_wset);
#endif
					}
					else
					{
						pclient->m_pSendingMsg = pclient->m_listSendMsg.front();
						pclient->m_listSendMsg.pop_front();
						pclient->m_nSentPtr = 0;
						//pclient->m_nContentSent = 0;
						std::string str;
						str = pclient->m_pSendingMsg->EncodeHeaders().c_str();
						if(str.length() > MAX_HTTP_BUF_SIZE -1)
						{
							g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\nmessage header is to large. drop it. %s",__FILE__,__LINE__,str.c_str());
							delete pclient->m_pSendingMsg;
							pclient->m_pSendingMsg = NULL;
						}
						else
						{
							memcpy(pclient->m_pSentBuf,str.c_str(),str.length());
							pclient->m_nSentLen = str.length();
							pclient->m_pSentBuf[pclient->m_nSentLen] = '\0';
							pclient->m_eSendStatus = CHTTPClient::send_Headers;
							g_Log.printf(LOG_LEVEL_MSG,"%s,%d\r\n send to %s:%d\n %s",__FILE__,__LINE__,inet_ntoa(pclient->m_PeerAddr.sin_addr),ntohs(pclient->m_PeerAddr.sin_port),pclient->m_pSentBuf);
						}
					}
				}

				if(pclient->m_nSentLen > 0)
				{
					n = send(pclient->m_s,&pclient->m_pSentBuf[pclient->m_nSentPtr],pclient->m_nSentLen - pclient->m_nSentPtr,0);
					if(n > 0)
					{
						pclient->m_nSentPtr += n;
						if(pclient->m_nSentPtr >= pclient->m_nSentLen)
						{
							pclient->m_nSentLen = 0;
							pclient->m_nSentPtr = 0;
							if(pclient->m_eSendStatus == CHTTPClient::send_Headers)
							{
								if(pclient->m_pSendingMsg->m_nContentLength > 0)
								{
									pclient->m_eSendStatus = CHTTPClient::send_Content;
									if(pclient->m_pSendingMsg->m_nContentLength < MAX_HTTP_BUF_SIZE - 1)
										pclient->m_nSentLen = pclient->m_pSendingMsg->m_nContentLength;
									else
										pclient->m_nSentLen = MAX_HTTP_BUF_SIZE-1;
									if(pclient->m_pSendingMsg->m_fp)
									{
										int32 l = fread(pclient->m_pSentBuf,1,pclient->m_nSentLen,pclient->m_pSendingMsg->m_fp);
										if(l != pclient->m_nSentLen)
										{
											goto removeclient;
										}
									}
									else
									{
										memcpy(pclient->m_pSentBuf,&pclient->m_pSendingMsg->m_pContent[pclient->m_pSendingMsg->m_nContentSent],pclient->m_nSentLen);
									}
									pclient->m_pSendingMsg->m_nContentSent = pclient->m_nSentLen;
								}
								else
								{
									pclient->m_eSendStatus = CHTTPClient::send_Null;
								}
							}
							else if(pclient->m_eSendStatus == CHTTPClient::send_Content)
							{
								if(pclient->m_pSendingMsg->m_nContentLength > pclient->m_pSendingMsg->m_nContentSent)
								{
									if(pclient->m_pSendingMsg->m_nContentLength - pclient->m_pSendingMsg->m_nContentSent < MAX_HTTP_BUF_SIZE - 1)
										pclient->m_nSentLen = pclient->m_pSendingMsg->m_nContentLength - pclient->m_pSendingMsg->m_nContentSent;
									else
										pclient->m_nSentLen = MAX_HTTP_BUF_SIZE - 1;
									if(pclient->m_pSendingMsg->m_fp)
									{
										int32 l = fread(pclient->m_pSentBuf,1,pclient->m_nSentLen,pclient->m_pSendingMsg->m_fp);
										if(l != pclient->m_nSentLen)
										{
											goto removeclient;
										}
									}
									else
									{
										memcpy(pclient->m_pSentBuf,&pclient->m_pSendingMsg->m_pContent[pclient->m_pSendingMsg->m_nContentSent],pclient->m_nSentLen);
									}									
									pclient->m_pSendingMsg->m_nContentSent += pclient->m_nSentLen;
								}
								else
								{
									pclient->m_eSendStatus = CHTTPClient::send_Null;
								}
							}
							pclient->m_pSentBuf[pclient->m_nSentLen] = '\0';
						//	g_Log.printf(LOG_LEVEL_MSG,"%s,%d\r\n send to %s:%d\n len = %d, %s",__FILE__,__LINE__,inet_ntoa(pclient->m_PeerAddr.sin_addr),ntohs(pclient->m_PeerAddr.sin_port),n,pclient->m_pSentBuf);
							if(pclient->m_nSentLen <= 0)
							{
								delete pclient->m_pSendingMsg;
								pclient->m_pSendingMsg = NULL;
								
								if(pclient->m_eSendStatus == CHTTPClient::send_Null)
								{
									if(pclient->m_pRecvingMsg)
									{
										CHTTPHeader *ph = pclient->m_pRecvingMsg->FindFirstHeader("connection");
										if(ph && ph->GetValue() == "close")
											goto removeclient;
									}
								}
							}							
						}
					}
					else
					{
#ifdef WIN32
						n = WSAGetLastError();
						if(n == WSAEINPROGRESS || n == WSAEINTR || n == WSAEWOULDBLOCK)
#else
						if(errno == EINPROGRESS || errno == EINTR || errno ==  EAGAIN || errno == EWOULDBLOCK)
#endif
						{							
							pclient->m_nTimeout = t + pthread->m_pServer->m_nTimeout;	
						}
						else
						{					
#ifdef WIN32
							g_Log.printf(LOG_LEVEL_DEBUG,"%s,%d\nsocket error. %u",__FILE__,__LINE__,n);
#else
							g_Log.printf(LOG_LEVEL_DEBUG,"%s,%d\nsocket error. %s",__FILE__,__LINE__,strerror(errno));
#endif
							goto removeclient;
						}
					}
				}
			}
			continue;
removeclient:
		//	pthread->m_pServer->DestroyClient(pthread,pclient);

#ifdef USE_EPOLL		
			epoll_ctl(pthread->m_epfd,EPOLL_CTL_DEL,pclient->m_s,NULL);
#else
			FD_CLR(pclient->m_s,&pthread->m_wset);
			FD_CLR(pclient->m_s,&pthread->m_rset);
			if(pthread->m_maxfd == pclient->m_s)
				pthread->m_maxfd --;
#endif
			pthread->m_pServer->DestroyClient(pthread,pclient);

			
			g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n destroyrtspclient",__FILE__,__LINE__);	
		}
	}
#ifdef USE_EPOLL
	if(pevents)
		delete[] pevents;
#endif
	return 0;
}

void CHTTPService::DestroyClient(CHTTPThreadInfo*pthread,CHTTPClientInfo *pclient)
{
	g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n destroy client.id=%u, peeraddr=%s:%d",__FILE__,__LINE__,
		pclient->m_nID,	inet_ntoa(pclient->m_PeerAddr.sin_addr),ntohs(pclient->m_PeerAddr.sin_port));

	if(pclient->m_pNext)
		pclient->m_pNext->m_pPrev = pclient->m_pPrev;
	if(pclient->m_pPrev)
		pclient->m_pPrev->m_pNext = pclient->m_pNext;
	if(pclient == pthread->m_pClients)
		pthread->m_pClients = pclient->m_pNext;
	if(pclient == pthread->m_pCurClient)
		pthread->m_pCurClient = pthread->m_pCurClient->m_pNext;
	if(pthread->m_pCurClient == NULL)
		pthread->m_pCurClient = pthread->m_pClients;

	std::map<uint32,CHTTPClientInfo*>::iterator cit;
	std::map<SOCKET,CHTTPClientInfo*>::iterator sit;
	cit = pthread->m_ClientIDMap.find(pclient->m_nID);
	if(cit != pthread->m_ClientIDMap.end())
		pthread->m_ClientIDMap.erase(cit);
	else
	{
		g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n can't find client %s from clientidmap. id=%u", __FILE__,__LINE__,pclient->m_strUserName.c_str(),pclient->m_nID);
	}
	
	sit = pthread->m_ClientMap.find(pclient->m_s);
	if(sit != pthread->m_ClientMap.end())
		pthread->m_ClientMap.erase(sit);
	else
	{
		g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n can't find client %s from clientmap. socket=%u", __FILE__,__LINE__,pclient->m_strUserName.c_str(),pclient->m_s);
	}

#ifdef USE_EPOLL		
	epoll_ctl(pthread->m_epfd,EPOLL_CTL_DEL,pclient->m_s,NULL);
#else
	FD_CLR(pclient->m_s,&pthread->m_wset);
	FD_CLR(pclient->m_s,&pthread->m_rset);
	if(pthread->m_maxfd == pclient->m_s)
		pthread->m_maxfd --;
#endif

	std::map<std::string,CHTTPUserInfo*>::iterator uit;
	CHTTPUserInfo *puser;
	m_UserMapLock.Lock();
	if(pclient->m_pUserInfo)
	{
		CHTTPClientInfo *pc;
		std::list<CHTTPClientInfo*>::iterator it;
		for(it = pclient->m_pUserInfo->m_listClients.begin(); it != pclient->m_pUserInfo->m_listClients.end(); it++)
		{
			pc = (*it);
			if(pc == pclient)
			{
				pclient->m_pUserInfo->m_listClients.erase(it);
				if(pclient->m_pUserInfo->m_listClients.size() == 0)
				{
					uit = m_UserMap.find(pclient->m_strUserName);
					if(uit != m_UserMap.end())
					{
						g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n user's client is 0, delete user %s",__FILE__,__LINE__,pclient->m_pUserInfo->m_strUserName.c_str());
						puser = uit->second;
						delete uit->second;
						m_UserMap.erase(uit);
					}
					else
					{
						g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n can't find user = %s form usermap",__FILE__,__LINE__,pclient->m_pUserInfo->m_strUserName.c_str());
					}
				}
				break;
			}
		}		
	}
	
	m_UserMapLock.Unlock();
	
	std::map<std::string,CPresentation*>::iterator pit;
	CPresentation *ppr;
	m_PresentationMapLock.Lock();
	pit = m_PresentationMap.find(pclient->m_URI.GetPath());
	if(pit != m_PresentationMap.end())
	{
		std::map<uint32,CHTTPThreadInfo*>::iterator cit;
		ppr = pit->second;
		
		cit = ppr->m_ClientMap.find(pclient->m_nID);
		if(cit != ppr->m_ClientMap.end())
		{			
			Msg *pmsg = new Msg;
			pmsg->m_eMsg = msg_ReleaseContent;
			pmsg->m_nClientID = pclient->m_nID;
			pmsg->m_nPresentationID = ppr->m_nID;					
			ppr->m_pFileThread->AddMsg(pmsg);
		}			
		if(ppr->m_nSourceCID == pclient->m_nID)
		{
			m_PresentationMap.erase(pit);			
			Msg *pmsg = new Msg;
			pmsg->m_eMsg = msg_DeleteItem;
			pmsg->m_nClientID = pclient->m_nID;
			pmsg->m_nPresentationID = ppr->m_nID;					
			ppr->m_pFileThread->AddMsg(pmsg);
		}	
	}
	m_PresentationMapLock.Unlock();
	delete pclient;
}
void CHTTPService::ProcessCONNECT(CHTTPThreadInfo *pthread,CHTTPClientInfo *pclient)
{
}
void CHTTPService::ProcessTRACE(CHTTPThreadInfo *pthread,CHTTPClientInfo *pclient)
{
}
void CHTTPService::ProcessDELETE(CHTTPThreadInfo *pthread,CHTTPClientInfo *pclient)
{
}
void CHTTPService::ProcessPUT(CHTTPThreadInfo *pthread,CHTTPClientInfo *pclient)
{
#if 0
	std::string path = pmsg->m_RequestLine.GetURI()->GetPath();

	std::map<std::string,CPresentation*>::iterator it;
	CPresentation *pp;
	
	int32 statuscode = 200;
	if(pmsg->m_bTransferEncoding == false)
	{	
		if(pmsg->m_nContentLength > 0 && pmsg->m_pContent)
		{
			std::string filename;
				
			filename = m_strDocRoot + path.substr(1,path.length());


			FILE *fp = fopen(filename.c_str(),"w+b");
			if(fp == NULL)
			{
				statuscode = 500;					
			}
			else
			{
				int32 l = fwrite(pmsg->m_pContent,1,pmsg->m_nContentLength,fp);
				if(l != pmsg->m_nContentLength)
				{
					statuscode = 500;
				}
				fclose(fp);
			}
		}
		pmsg->CreateResponse(statuscode);
	}
	else
	{	
	//	g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n PUT client = %s, path=%s",__FILE__,__LINE__,pclient->m_strUserName.c_str(),path.c_str());
		m_PresentationMapLock.Lock();
		it = m_PresentationMap.find(path);
		if(it != m_PresentationMap.end())
		{
			pp = it->second;
			if(pp->m_eType == CPresentation::p_File)
			{
				statuscode = 403;
				g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n the path already exist. type = file, %s,%u",__FILE__,__LINE__,path.c_str(),pp->m_nID);
				pp = NULL;			
			}
			else if(pp->m_nID != pclient->m_nPID)
			{
				statuscode = 403;
				g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n the path already exist. release the old path. type = stream, %s,%u,%u",__FILE__,__LINE__,path.c_str(),pp->m_nID,pclient->m_nPID);			
				pp = NULL;
			}		
		}
		else
		{
			pp = new CPresentation;
			pp->m_strPath = path.c_str();
			pp->m_strFileName = m_strDocRoot + path.substr(1,path.length());
			pp->m_eType = CPresentation::p_Stream;
			g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n PUT client = %s, path=%s",__FILE__,__LINE__,pclient->m_strUserName.c_str(),path.c_str());		
			if(pp)
			{
				if(pmsg->m_pContentType)
				{
					pp->m_pContentType = new CHTTPContentType;
					*pp->m_pContentType = *pmsg->m_pContentType;
				}
				else 
				{
					std::string ext;
					int32 cc = path.rfind('.');
					if(cc > 0 && cc < 8)
					{
						ext = path.substr(cc+1,path.length());
					
						const MIMETypeTable::MIMEItem *pitem = m_MIMETable.FindItem(ext);
						if(pitem)
						{
							pp->m_pContentType = new CHTTPContentType;
							if(pp->m_pContentType->Parse(pitem->m_strType.c_str(),pitem->m_strType.length()) <= 0)
							{
								delete pp->m_pContentType;
								pp->m_pContentType = NULL;
							}
						}
					}
				}	
			
				pp->m_nID = m_nPresentationID++;
				pp->m_nSourceCID = pclient->m_nID;
				pclient->m_nPID = pp->m_nID;
				m_PresentationMap[path] = pp;
				pp->m_pFileThread = &m_FileThreads[m_nFileThreadCount++%m_nFileThreads];
				g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n put presentation to map. %s",__FILE__,__LINE__,path.c_str());
				Msg *pm = new Msg;
				pm->m_eMsg = msg_NewItem;
				pm->m_pBody = pp;
				pm->m_nPresentationID = pp->m_nID;
				pp->m_pFileThread->AddMsg(pm);
			}
		}	
		if(pp && pmsg->m_nChunkSize > 0)
		{
			Msg *pm = new Msg;
			pm->m_eMsg = msg_PutContent;
			pm->m_nClientID = pclient->m_nID;
			pm->m_nPresentationID = pp->m_nID;
			pm->m_pThread = pthread;
			CMemBuffer *pmem = new CMemBuffer;
			pm->SetBuffer(pmsg->m_pContent,pmsg->m_nChunkSize);
			pm->m_pBody = pmem;
			pp->m_pFileThread->AddMsg(pm);
		}
		m_PresentationMapLock.Unlock();
		if(pmsg->m_nChunkSize == 0)
		{
			pmsg->CreateResponse(statuscode);
		}
	}
	time_t t;
	time(&t);
	pclient->m_nTimeout = t + m_nTimeout;	
#endif
}
void CHTTPService::ProcessPOST(CHTTPThreadInfo *pthread,CHTTPClientInfo *pclient)
{
}
void CHTTPService::ProcessHEAD(CHTTPThreadInfo *pthread,CHTTPClientInfo *pclient)
{
}

void CHTTPService::ProcessGET(CHTTPThreadInfo *pthread,CHTTPClientInfo *pclient)
{
	std::string path = pclient->m_pRecvingMsg->m_RequestLine.GetURI()->GetPath();

	if(path.empty() || path == "/")
		path = "/index.html";
	
	
	CHTTPHeader *ph = pclient->m_pRecvingMsg->FindFirstHeader( "host");
	if(ph)
	{
		pclient->m_strHost = ph->GetValue();
	}
	else
		pclient->m_strHost = "www.qik56.com";

	CHTTPMsg *presp = NULL;
	int32 cc = CheckPath(path.c_str());
	g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n path= %s",__FILE__,__LINE__,path.c_str());
	if(pclient->m_strHost.empty())
	{
		presp = pclient->MakeResponse(404,NULL);
	}
	else if(cc < 0)
	{
		presp = pclient->MakeResponse(403,NULL);
	}
	else
	{
		std::map<std::string,HostInfo*>::iterator it;
		it = this->m_HostMap.find(pclient->m_strHost);
		if(it == m_HostMap.end())
		{
			presp = pclient->MakeResponse(404,NULL);
			goto end;
		}

		HostInfo *phi = it->second;
		pclient->m_nStatusCode = 200;
		pclient->m_strFileName = m_strDocRoot + phi->m_strPath + path.substr(1,path.length());

		FILE *fp = fopen(pclient->m_strFileName.c_str(),"rb");
		g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n %s",__FILE__,__LINE__,pclient->m_strFileName.c_str());
		
		if(fp == NULL)
		{		
			presp = pclient->MakeResponse(404,NULL);
		}
		else
		{		
			std::map<std::string,PathInfo*>::iterator it;
			
			PathInfo *pi = NULL;
			m_PathLock.Lock();
			it = m_PathMap.find(path);
			if(it != m_PathMap.end())
			{
				pi = it->second;
				m_PathLock.Unlock();
				this->WriteWEBLog(pthread,pclient,pi->m_strType.c_str());
			}
			else
				m_PathLock.Unlock();
			
			
			presp = pclient->MakeResponse(200,NULL);
			std::string ext;
			int32 cc = path.rfind('.');
			g_Log.printf(LOG_LEVEL_MSG,"%s,%d,cc = %d",__FILE__,__LINE__,cc);
			if(cc > 0)
			{
				ext = path.substr(cc+1,path.length());
				
				g_Log.printf(LOG_LEVEL_MSG,"%s,%d,ext = %s",__FILE__,__LINE__,ext.c_str());
				const MIMETypeTable::MIMEItem *pitem = m_MIMETable.FindItem(ext);
				if(pitem)
				{
					g_Log.printf(LOG_LEVEL_MSG,"%s,%d,ext = %s",__FILE__,__LINE__,ext.c_str());
					presp->m_pContentType = new CHTTPContentType;
					if(presp->m_pContentType->Parse(pitem->m_strType.c_str(),pitem->m_strType.length()) <= 0)
					{
						delete presp->m_pContentType;
						presp->m_pContentType = NULL;
					}
					if(pitem->m_strContentDisposition.empty() == false)
					{
						CHTTPHeader *ph = new CHTTPHeader;
						ph->SetName("Content-Disposition");
						ph->SetValue(pitem->m_strContentDisposition.c_str());

						cc = path.rfind('/');
						if(cc > 0)
						{
							ext = path.substr(cc+1,path.length());
							ph->AddParam("filename",ext.c_str(),true);
						}
						presp->AddHeader(ph);
					}
				}
			}
			fseek(fp,0,SEEK_END);
			presp->m_nContentLength = ftell(fp);	
			fseek(fp,0,SEEK_SET);
			CHTTPHeader *ph = pclient->m_pRecvingMsg->FindFirstHeader("connection");
			if(ph && stricmp(ph->GetValue().c_str(),"keep-alive") == 0)
			{
				CHTTPHeader *pkl = new CHTTPHeader;
				pkl->SetName("Connection");
				pkl->SetValue("keep-alive");
				presp->AddHeader(pkl);
			}
			
	#if 0
			presp->m_fp = fp;
	#else
			presp->m_pContent = new int8[presp->m_nContentLength + 1];
			long l = fread(presp->m_pContent,1,presp->m_nContentLength,fp);
			presp->m_pContent[presp->m_nContentLength] = '\0';
			fclose(fp);
	#endif
			if(ext == "kgi")
			{
				ProcessKGI(pthread,pclient,presp);
			}			
		}	
	}
end:
	//if(m_strServer.empty() == false)
	//	presp->Server().AddServerVal(m_strServer.c_str());
	SendResponseMessage(pthread,pclient,presp);
	time_t t;
	time(&t);
	pclient->m_nTimeout = t + m_nTimeout;
}
void CHTTPService::ProcessOPTIONS(CHTTPThreadInfo *pthread,CHTTPClientInfo *pclient)
{
		
}
void CHTTPService::ProcessMessage(CHTTPThreadInfo *pthread,CHTTPClientInfo *pclient)
{
	if(pclient->m_pRecvingMsg->m_bIsRequest)
	{
		int32 responsecode = 200;
		CHTTPMsg *presp = NULL;
		std::string content;
		std::string type,subtype;
		std::string path;
	
		if(pclient->m_pRecvingMsg->m_RequestLine.GetMethod() == http_OPTIONS)//" ; Section 9.2
		{
			ProcessOPTIONS(pthread,pclient);
		}
		else if(pclient->m_pRecvingMsg->m_RequestLine.GetMethod() == http_GET)//" ; Section 9.3
		{
			ProcessGET(pthread,pclient);
		}
		else if(pclient->m_pRecvingMsg->m_RequestLine.GetMethod() == http_HEAD)//" ; Section 9.4
		{
			ProcessHEAD(pthread,pclient);
		}
		else if(pclient->m_pRecvingMsg->m_RequestLine.GetMethod() == http_POST)//" ; Section 9.5
		{
			ProcessPOST(pthread,pclient);
		}
		else if(pclient->m_pRecvingMsg->m_RequestLine.GetMethod() == http_PUT)//" ; Section 9.6
		{
			ProcessPUT(pthread,pclient);
		}
		else if(pclient->m_pRecvingMsg->m_RequestLine.GetMethod() == http_DELETE)// ; Section 9.7
		{
			ProcessDELETE(pthread,pclient);
		}
		else if(pclient->m_pRecvingMsg->m_RequestLine.GetMethod() == http_TRACE)//" ; Section 9.8
		{
			ProcessTRACE(pthread,pclient);
		}
		else if(pclient->m_pRecvingMsg->m_RequestLine.GetMethod() == http_CONNECT)// ; Section 9.9
		{
			ProcessCONNECT(pthread,pclient);
		}		
		else
		{
			responsecode = 405;// Method Not Allowed
			responsecode = 200;			
			type = "text";
			subtype = "plain";
			if(pclient->m_URI.FindHeader("echostr"))
			{			
				content = pclient->m_URI.GetHeaderValue("echostr");
			}
			presp = MakeResponse(pclient,200,NULL);
		
			if(    content.empty() 
				&& pclient->m_URI.FindHeader("echostr"))
			{
				content = pclient->m_URI.GetHeaderValue("echostr");
				type = "text";
				subtype = "plain";
			}

			if(content.empty() == false)
			{
				presp->ContentType().SetType(type.c_str());
				presp->ContentType().SetSubType(subtype.c_str());
				presp->SetContent(content.c_str(),content.length());
			}		
			SendResponseMessage(pthread,pclient,presp);
		}
	}	
}


CHTTPMsg* CHTTPService::MakeResponse(CHTTPClient *pclient, int32 code, const int8 *preason)
{
	CHTTPMsg *presp = CHTTPClient::MakeResponse(code,NULL);
	
	/*
	presp->Server().AddServerVal(m_strServer.c_str(),false);
	presp->Connection().SetConnection("Keep-Alive");
	presp->Date().SetDateType(CHTTPDate::http_date_RFC1123);
	time_t t;
	time(&t);
	tm *ptm = gmtime(&t);
	presp->Date().SetYear(ptm->tm_year + 1900);
	presp->Date().SetMonth(ptm->tm_mon);
	presp->Date().SetMDay(ptm->tm_mday);
	if(ptm->tm_wday == 0)
		presp->Date().SetWeekDay(6);
	else
		presp->Date().SetWeekDay(ptm->tm_wday - 1);
	presp->Date().SetHour(ptm->tm_hour);
	presp->Date().SetMinute(ptm->tm_min);
	presp->Date().SetSecond(ptm->tm_sec);
	*/
	if (preason && strlen(preason))
	{
		presp->m_StatusLine.SetReason(preason);
	}
	else
	{
		switch (code)
		{
         case 100: presp->m_StatusLine.SetReason("Trying"); break;
         case 180: presp->m_StatusLine.SetReason("Ringing"); break;
         case 181: presp->m_StatusLine.SetReason("Call Is Being Forwarded"); break;
         case 182: presp->m_StatusLine.SetReason("Queued"); break;
         case 183: presp->m_StatusLine.SetReason("Session Progress"); break;
         case 200: presp->m_StatusLine.SetReason("OK"); break;
         case 300: presp->m_StatusLine.SetReason("Multiple Choices"); break;
         case 301: presp->m_StatusLine.SetReason("Moved Permanently"); break;
         case 302: presp->m_StatusLine.SetReason("Moved Temporarily"); break;
         case 305: presp->m_StatusLine.SetReason("Use Proxy"); break;
         case 380: presp->m_StatusLine.SetReason("Alternative Service"); break;
         case 400: presp->m_StatusLine.SetReason("Bad Request"); break;
         case 401: presp->m_StatusLine.SetReason("Unauthorized"); break;
         case 402: presp->m_StatusLine.SetReason("Payment Required"); break;
         case 403: presp->m_StatusLine.SetReason("Forbidden"); break;
         case 404: presp->m_StatusLine.SetReason("Not Found"); break;
         case 405: presp->m_StatusLine.SetReason("Method Not Allowed"); break;
         case 406: presp->m_StatusLine.SetReason("Not Acceptable"); break;
         case 407: presp->m_StatusLine.SetReason("Proxy Authentication Required"); break;
         case 408: presp->m_StatusLine.SetReason("Request Timeout"); break;
         case 410: presp->m_StatusLine.SetReason("Gone"); break;
		 case 411: presp->m_StatusLine.SetReason("Length Required"); break;
         case 412: presp->m_StatusLine.SetReason("Precondition Failed"); break;
         case 413: presp->m_StatusLine.SetReason("Request Entity Too Large"); break;
         case 414: presp->m_StatusLine.SetReason("Request-URI Too Long"); break;
         case 415: presp->m_StatusLine.SetReason("Unsupported Media Type"); break;
         case 416: presp->m_StatusLine.SetReason("Unsupported URI Scheme"); break;
         case 420: presp->m_StatusLine.SetReason("Bad Extension"); break;
         case 421: presp->m_StatusLine.SetReason("Extension Required"); break;
	     //added by James
         case 422: presp->m_StatusLine.SetReason("Session Interval Too Small"); break;
		 //end adding
         case 423: presp->m_StatusLine.SetReason("Interval Too Brief"); break;
		 case 451: presp->m_StatusLine.SetReason("Parameter Not Understood"); break;
         case 452: presp->m_StatusLine.SetReason("Conference Not Found"); break;
         case 453: presp->m_StatusLine.SetReason("Not Enough Bandwidth"); break;
         case 454: presp->m_StatusLine.SetReason("Session Not Found"); break;
         case 455: presp->m_StatusLine.SetReason("Method Not Valid in This State"); break;
         case 456: presp->m_StatusLine.SetReason("Header Field Not Valid for Resource"); break;
         case 457: presp->m_StatusLine.SetReason("Invalid Range"); break;
         case 458: presp->m_StatusLine.SetReason("Parameter Is Read-Only"); break;
         case 459: presp->m_StatusLine.SetReason("Aggregate operation not allowed"); break;
         case 460: presp->m_StatusLine.SetReason("Only aggregate operation allowed"); break;
         case 461: presp->m_StatusLine.SetReason("Unsupported transport"); break;
         case 462: presp->m_StatusLine.SetReason("Destination unreachable"); break;
         case 480: presp->m_StatusLine.SetReason("Temporarily Unavailable"); break;
         case 481: presp->m_StatusLine.SetReason("Call/Transaction Does Not Exist"); break;
         case 482: presp->m_StatusLine.SetReason("Loop Detected"); break;
         case 483: presp->m_StatusLine.SetReason("Too Many Hops"); break;
         case 484: presp->m_StatusLine.SetReason("Address Incomplete"); break;
         case 485: presp->m_StatusLine.SetReason("Ambiguous"); break;
         case 486: presp->m_StatusLine.SetReason("Busy Here"); break;
         case 487: presp->m_StatusLine.SetReason("Request Terminated"); break;
         case 488: presp->m_StatusLine.SetReason("Not Acceptable Here"); break;
         case 491: presp->m_StatusLine.SetReason("Request Pending"); break;
         case 493: presp->m_StatusLine.SetReason("Undecipherable"); break;
         case 500: presp->m_StatusLine.SetReason("Server Internal Error"); break;
         case 501: presp->m_StatusLine.SetReason("Not Implemented"); break;
         case 502: presp->m_StatusLine.SetReason("Bad Gateway"); break;
         case 503: presp->m_StatusLine.SetReason("Service Unavailable"); break;
         case 504: presp->m_StatusLine.SetReason("Server Time-out"); break;
         case 505: presp->m_StatusLine.SetReason("Version Not Supported"); break;
         case 513: presp->m_StatusLine.SetReason("Message Too Large"); break;
		 case 551: presp->m_StatusLine.SetReason("Option not supported"); break;
         case 600: presp->m_StatusLine.SetReason("Busy Everywhere"); break;
         case 603: presp->m_StatusLine.SetReason("Decline"); break;
         case 604: presp->m_StatusLine.SetReason("Does Not Exist Anywhere"); break;
         case 606: presp->m_StatusLine.SetReason("Not Acceptable"); break;
		}
	}
	
	return presp;
}

/*
void CHTTPService::SendMessage(CHTTPThreadInfo *pthread, CHTTPClientInfo *pclient, CHTTPMsg *pmsg)
{
	if(pmsg->IsRequest())
		SendRequestMessage(pthread,pclient,pmsg);
	else
		SendResponseMessage(pthread,pclient,pmsg);
}
*/
void CHTTPService::SendRequestMessage(CHTTPThreadInfo *pthread, CHTTPClientInfo *pclient, CHTTPMsg *pmsg)
{
	pclient->AddSendMsg(pmsg);
#ifdef USE_EPOLL	
	struct epoll_event ev;
	ev.data.fd = pclient->m_s;
	//设置要处理的事件类型
	ev.events=EPOLLIN | EPOLLOUT;// read. 
	//注册epoll事件
	epoll_ctl(pthread->m_epfd,EPOLL_CTL_MOD,pclient->m_s,&ev);
#else		
	if(!FD_ISSET(pclient->m_s,&pthread->m_wset))
		FD_SET(pclient->m_s,&pthread->m_wset);		
#endif
}
void CHTTPService::SendResponseMessage(CHTTPThreadInfo *pthread, CHTTPClientInfo *pclient, CHTTPMsg *pmsg)
{
	pclient->AddSendMsg(pmsg);
g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n send response",__FILE__,__LINE__);
#ifdef USE_EPOLL	
	struct epoll_event ev;
	ev.data.fd = pclient->m_s;
	//设置要处理的事件类型
	ev.events=EPOLLIN | EPOLLOUT;// read. 
	//注册epoll事件
	epoll_ctl(pthread->m_epfd,EPOLL_CTL_MOD,pclient->m_s,&ev);
#else		
	if(!FD_ISSET(pclient->m_s,&pthread->m_wset))
		FD_SET(pclient->m_s,&pthread->m_wset);		
#endif
}
#if 0
int32 CHTTPService::SendMessageTo(const std::string &username,const std::string &method, const std::string &content,const std::string &type,const std::string &subtype)
{
	g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n",__FILE__,__LINE__);
	std::map<std::string,CHTTPUserInfo*>::iterator it;
	std::list<CHTTPClientInfo*>::iterator cit;
	CHTTPUserInfo *puser;
	CHTTPClientInfo *pclient;

	m_UserMapLock.Lock();

	it = m_UserMap.find(username);
	if(it != m_UserMap.end())
	{
		g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n",__FILE__,__LINE__);
		puser = it->second;
		for(cit = puser->m_listClients.begin(); cit != puser->m_listClients.end(); cit++)
		{
			pclient = (*cit);
			g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n",__FILE__,__LINE__);
			if(pclient->m_pThreadInfo)
			{
				CHTTPMsg *pmsg = new CHTTPMsg;
				pmsg->RequestLine().SetMethod(http_GET);
				CURI uri;
				std::string path;
				path = method.c_str();
				TrimLeft(path);
				TrimRight(path);
				if(path.empty() || path.at(0) != '/')
					path = "/" + path;	
				uri.SetPath(path.c_str());	
				pmsg->RequestLine().SetURI(uri);

				g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n",__FILE__,__LINE__);
			
	//			pmsg->Host().SetHost(m_strServerHost.c_str());
			//	CHTTPAccept *pa = new CHTTPAccept;
			//	pa->SetType("*");
			//	pa->SetSubType("*");
			//	pmsg->Accepts().push_back(pa);

			//	pmsg->UserAgent().SetUserAgent(m_strUserAgent.c_str());
				if(type.empty() == false)
					pmsg->ContentType().SetType(type.c_str());
				if(subtype.empty() == false)
					pmsg->ContentType().SetSubType(subtype.c_str());
				if(content.empty() == false)
				{
					pmsg->ContentLength().SetContentLength(content.length());
					pmsg->Content().SetContent(content.c_str(),content.length());
				}
				g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n",__FILE__,__LINE__);
				Msg *pm = new Msg;
				pm->m_eMsg = msg_Msg;
				pm->m_nClientID = pclient->m_nID;
				pm->m_pBody = pmsg;
				pclient->m_pThreadInfo->AddMsg(pm);	
				g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n",__FILE__,__LINE__);
			}
			else
			{
				g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n pthreadinfo is null. %s:%d",__FILE__,__LINE__,inet_ntoa(pclient->m_PeerAddr.sin_addr),ntohs(pclient->m_PeerAddr.sin_port));
			}
		}
	}
	m_UserMapLock.Unlock();

	g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n",__FILE__,__LINE__);
	return 0;
}

void* CHTTPService::FileProc(void *argv)
{
	time_t					t,t1,t2;
	int64					mseconds = 0;
	socklen_t				socklen = 0;
	int32					c = 0;
	int32					sentsocks = 0;
	std::string				sessionid;
	
	std::list<Msg*>::iterator mit;
	Msg *pcmd;

	CHTTPClientInfo*	pclient = NULL,*ptempclient = NULL;
	std::map<SOCKET,CHTTPClientInfo*>::iterator cit,delcit;

	std::map<uint32,CHTTPClientInfo*>::iterator it;

	std::map<uint32,CPresentation*>::iterator pit;
	CPresentation *pp;
	CFileThreadInfo *pthread = (CFileThreadInfo*)argv;
	Msg *pmsg;
	int8 buf[32];
	int32 cc = 0;
	max_thread_priority();


	time(&t1);
	t2 = t1;
	srand(t1%20000);
	
	while(pthread->m_bRunning)
	{			
		time(&t);
	//	if(t - t2 > 30)
	//		break;
		pcmd = NULL;
		pthread->m_MsgLock.Lock();
		if(pthread->m_listMsg.size() > 0)
		{
			pcmd = pthread->m_listMsg.front();
			pthread->m_listMsg.pop_front();

		}
		pthread->m_MsgLock.Unlock();
		if(pcmd == NULL)
		{
			usleep(100000);
			continue;
		}
		if(pcmd)
		{
			switch(pcmd->m_eMsg)
			{
			case msg_PutContent:
				{
					pit = pthread->m_PresentationMap.find(pcmd->m_nPresentationID);
					if(pit != pthread->m_PresentationMap.end())
					{
						pp = pit->second;
						if(pp->m_eType == CPresentation::p_File)
						{
							CMemBuffer *pm = (CMemBuffer *)pcmd->m_pBody;
							int32 n = fwrite(pm->GetBuffer(),1,pm->GetLength(),pp->m_fp);
							if(n != pm->GetLength())
							{
								g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n can't write file %s,buf size = %d",__FILE__,__LINE__,pp->m_strFileName.c_str(),pm->GetLength());
							}
							else
								pp->m_nBytesWritten += pm->GetLength();

							delete pm;
						}
						else if(pp->m_eType == CPresentation::p_Stream)
						{
							CMemBuffer *pm = (CMemBuffer *)pcmd->m_pBody;
							if(pp->m_fp == NULL && pm->GetBuffer()[3] == 'I')
							{
								time_t t;
								time(&t);
								tm *ptm = localtime(&t);
								int8 buf[32];
								sprintf(buf,"%04d%02d%02d%02d%02d%02d",ptm->tm_year + 1900,ptm->tm_mon,ptm->tm_mday,ptm->tm_hour,ptm->tm_min,ptm->tm_sec);
								pp->m_strRecordFileName = pthread->m_pServer->m_strDocRoot + pp->m_strUserName + buf;
								if(pp->m_pContentType)
									pp->m_strRecordFileName += "." + pp->m_pContentType->GetSubType();
								pp->m_fp = fopen(pp->m_strRecordFileName.c_str(),"w+b");
								if(pp->m_fp == NULL)
								{
									g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n can't create file %s,",__FILE__,__LINE__,pp->m_strRecordFileName.c_str());
								}
							}
							if(pp->m_fp)
							{
								int32 n = fwrite(&pm->GetBuffer()[8],1,pm->GetLength() - 8,pp->m_fp);
								if(n != pm->GetLength() - 8)
								{
									g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n can't write file %s,buf size = %d",__FILE__,__LINE__,pp->m_strFileName.c_str(),pm->GetLength());
								}
								else
									pp->m_nBytesWritten += pm->GetLength() - 8;
							}
							if(pp->m_pCurBuf)
								delete pp->m_pCurBuf;
							pp->m_pCurBuf = pm;

							std::map<uint32,CHTTPThreadInfo*>::iterator it;
							CHTTPThreadInfo *pth;
							//hex
							sprintf(buf,"%x\r\n",pm->m_nLength);
							c = strlen(buf);
							for(it = pp->m_ClientMap.begin(); it != pp->m_ClientMap.end(); it++)
							{
								pth = it->second;
								pmsg = new Msg;
								pmsg->m_eMsg = msg_Content;
								pmsg->m_nClientID = it->first;
								CMemBuffer *pmm = new CMemBuffer;
								if(pmm == NULL || pmm->Allocate(c + pm->m_nLength) == NULL)
								{
									g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n can't allocate memory for file buf.",__FILE__,__LINE__);
									if(pmm)
										delete pmm;
								}
								else
								{
									memcpy(pmm->m_pBuf,buf,c);
									memcpy(&pmm->m_pBuf[c],pm->GetBuffer(),pm->GetLength());
									pmsg->m_pBody = pmm;
									pmm->m_nLength = c + pm->GetLength();
									pth->AddMsg(pmsg);	
								}
							}
						}
					}
				}
				break;
			case msg_NewItem:
				{
					pp = (CPresentation *)pcmd->m_pBody;
					pthread->m_PresentationMap[pp->m_nID] = pp;
				}
				break;
			case msg_GetContent:
				{
					pit = pthread->m_PresentationMap.find(pcmd->m_nPresentationID);
					if(pit != pthread->m_PresentationMap.end())
					{
						pp = pit->second;
						if(pp->m_eType == CPresentation::p_File)
						{
							if(pp->m_pContent == NULL)
							{
								pp->m_pContent = new int8[pp->m_nContentLength];
								fseek(pp->m_fp,0,SEEK_SET);
								long n = fread(pp->m_pContent,1,pp->m_nContentLength,pp->m_fp);							
							}
							if(pp->m_pContent)
							{							
								pmsg = new Msg;
								pmsg->m_eMsg = msg_Content;
								pmsg->m_nClientID = pcmd->m_nClientID;
								CMemBuffer *pm = new CMemBuffer;
								pm->SetBuffer(pp->m_pContent,pp->m_nContentLength);
								pmsg->m_pBody = pm;							
								pcmd->m_pThread->AddMsg(pmsg);							
							}
						}
						else if(pp->m_eType == CPresentation::p_Stream)
						{
							if(pp->m_pCurBuf)
							{
								pmsg = new Msg;
								pmsg->m_eMsg = msg_Content;
								pmsg->m_nClientID = pcmd->m_nClientID;

								
								sprintf(buf,"%u\r\n",pp->m_pCurBuf->m_nLength);
								c = strlen(buf);
								CMemBuffer *pmm = new CMemBuffer;
								if(pmm == NULL || pmm->Allocate(c + pp->m_pCurBuf->m_nLength) == NULL)
								{
									g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n can't allocate memory for file buf.",__FILE__,__LINE__);
									if(pmm)
										delete pmm;
								}
								else
								{
									memcpy(pmm->m_pBuf,buf,c);
									memcpy(&pmm->m_pBuf[c],pp->m_pCurBuf->GetBuffer(),pp->m_pCurBuf->GetLength());
									pmsg->m_pBody = pmm;
									pmm->m_nLength = c + pp->m_pCurBuf->GetLength();
									pcmd->m_pThread->AddMsg(pmsg);
								}									
								
							}
							std::map<uint32,CHTTPThreadInfo*>::iterator it;
							it = pp->m_ClientMap.find(pcmd->m_nClientID);
							if(it == pp->m_ClientMap.end())
								pp->m_ClientMap[pcmd->m_nClientID] = pcmd->m_pThread;
						}
					}
				}
				break;
			case msg_ReleaseContent:
				{
					pit = pthread->m_PresentationMap.find(pcmd->m_nPresentationID);
					if(pit != pthread->m_PresentationMap.end())
					{
						pp = pit->second;
						std::map<uint32,CHTTPThreadInfo*>::iterator it;
						it = pp->m_ClientMap.find(pcmd->m_nClientID);
						if(it != pp->m_ClientMap.end())
							pp->m_ClientMap.erase(it);

						if(pp->m_eType == CPresentation::p_File)
						{
							
						}
						else if(pp->m_eType == CPresentation::p_Stream)
						{					
							
						}
					}

				}
				break;
			case msg_DeleteItem:
				{
					pit = pthread->m_PresentationMap.find(pcmd->m_nPresentationID);
					if(pit != pthread->m_PresentationMap.end())
					{
						pp = pit->second;
						std::map<uint32,CHTTPThreadInfo*>::iterator it;
						CHTTPThreadInfo *pth;
						Msg *pmsg;
						for(it = pp->m_ClientMap.begin(); it != pp->m_ClientMap.end(); it++)
						{
							pth = it->second;
							pmsg = new Msg;
							pmsg->m_eMsg = msg_ReleaseContent;
							pmsg->m_nClientID = it->first;
							pmsg->m_nPresentationID = pp->m_nID;
							pth->AddMsg(pmsg);
						}					
					}
				}
				break;
			case msg_Msg:
				{
				
				}
				break;
			}
			delete pcmd;
		}
	}
	return 0;
}

bool CHTTPService::ProcessHTTPMessage(CHTTPThreadInfo *pthread,CHTTPClientInfo *pclient,CHTTPMsg *pmsg)
{
	if(pclient->pmsg->IsRequest())
	{
		std::string path = pclient->pmsg->GetRequestLine()->GetURI()->GetPath();
		std::string filename = m_strRootDIR.c_str();
		if(path[0] == '/')
			filename += path.substr(1,-1);
		else
			filename += path;
		FILE *fp = fopen(filename.c_str(),"rb");
		path = "HTTP/1.1 200 OK\r\n";
		
		g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n file=%s",__FILE__,__LINE__,filename.c_str());
		int mm = filename.find("m3u8");
		if(mm != -1)
			path += "Content-Type: application/vnd.apple.mpegurl\r\n";
		else if(filename.find(".html") != -1)
		{
			path += "Content-Type: text/html\r\n";
		}
		else if(filename.find(".jpg") != -1)
			path += "Content-Type: image/jpeg\r\n";
		else if(filename.find(".mp4") != -1)
			path += "Content-Type: video/mp4\r\n";
		else
		{
			path += "Content-Type: video/mp2t\r\n";
		}
		path += "Content-Length: ";
		char buf[128];
		size_t pos;
		if(fp)
		{
			fseek(fp,0,SEEK_END);
			
			//fgetpos(fp,&pos);
			pos = ftell(fp);
			fseek(fp,0,SEEK_SET);
			sprintf(buf,"%u\r\n",pos);
		}
		else
		{
			sprintf(buf,"%d\r\n",0);
			g_Log.printf(LOG_LEVEL_WARNING,"%s,%d\n can't open file",__FILE__,__LINE__);
		}
		g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n file size = %s",__FILE__,__LINE__,buf);
		path += buf;
	//	path += "Connection: close\r\n";
		path += "\r\n";
		memcpy(&pclient->m_pSendBuf[pclient->m_nSentLength],path.c_str(),path.length());
		pclient->m_nSentLength += path.length();
		if(fp)
		{
			if(pos + pclient->m_nSentLength >= pclient->m_nSendBufLen)
			{
				pclient->m_nSendBufLen = pos + pclient->m_nSentLength + 128;
				int8 *pb = new int8[pclient->m_nSendBufLen];
				memcpy(pb,pclient->m_pSendBuf,pclient->m_nSentLength);
				delete pclient->m_pSendBuf;
				pclient->m_pSendBuf = pb;
			}
			
			size_t n = fread(&pclient->m_pSendBuf[pclient->m_nSentLength],1,pos,fp);
			fclose(fp);
			pclient->m_nSentLength += pos;
		}
		pclient->m_pSendBuf[pclient->m_nSentLength] = '\0';

#ifdef USE_EPOLL
		struct epoll_event ev;
		ev.data.fd = pclient->m_s;
		//设置要处理的事件类型
		ev.events = EPOLLIN | EPOLLOUT;// read. 
		//注册epoll事件
		epoll_ctl(pthread->m_epfd,EPOLL_CTL_MOD,pclient->m_s,&ev);


#else
		if(FD_ISSET(pclient->m_s,&pthread->m_wset) == false)
		{
			FD_SET(pclient->m_s,&pthread->m_wset);
		}
#endif

	}
	return true;
}
#endif

int32 CHTTPService::CheckPath(const int8 *ppath)
{
	int32 c = 0;
	int32 dot = 0;
	const int8 *p = ppath;
	std::string segment;
	while(*p)
	{
		if(*p == '.')
			dot++;
		else
		{
			if(dot == 2)
				c--;
			dot = 0;
			if(*p == '/')
			{
				if(segment.empty() == false)
					c++;
				segment.erase();				
			}
			else
			{
				segment += *p;
			}
		}
		p++;
	}
	return c;
}

void CHTTPService::WriteWEBLog(CHTTPThreadInfo *pthread,CHTTPClient *pclient, const char *ptype)
{
	
	if(pthread->m_pDB)
	{
		std::string sql;
		sql = "insert into WEB_LOG(remote_ip,remote_port,access_type,created_time) values('";
		sql += inet_ntoa(pclient->m_PeerAddr.sin_addr);
		char buf[32];
		sprintf(buf,"%d",ntohs(pclient->m_PeerAddr.sin_port));
		sql += "',";
		sql += buf;
		sql += ",'";
		sql += ptype;
		time_t t;
		time(&t);
		tm *ptm = localtime(&t);
		sprintf(buf,"','%04d-%02d-%02d %02d:%02d:%02d')",ptm->tm_year + 1900,ptm->tm_mon + 1,ptm->tm_mday,ptm->tm_hour,ptm->tm_min,ptm->tm_sec);
		sql += buf;
		pthread->m_pDB->execSQL(sql);
	}
}

void CHTTPService::ProcessKGI(CHTTPThreadInfo *pthread, CHTTPClientInfo *pclient, CHTTPMsg *presp)
{
	int8 *pstr = presp->m_pContent;
	int8 *ps,*p;
	int32 c = 0;
	bool skipspace = false;
	std::string content,method;
	//<kjcgi></kjcgi>
	while((ps = strstr(pstr,"<kjcgi>")))
	{		
		*ps = '\0';
		content += pstr;
		pstr = ps + 7;
		ps = strstr(pstr,"</kjcgi>");
		if(ps == NULL)
		{
			content += "<";
			content += pstr;
			break;
		}
		p = pstr;
		
		*ps = '\0';
		pstr = ps + 8;
		skipspace = false;
		while(*p)
		{
			if(*p == ';')
			{
				TrimLeft(method);
				TrimRight(method);
				if(method == "GetShipmentSummary()")
				{
					content += GetShipmentSummary(pthread, pclient, presp);
				}
				else
				{
					content = "cgi method error.";
					goto end;
				}
				p++;
				method.erase();
				skipspace = false;
			}
			else if(*p == '(')
			{
				method += *p++;				
				skipspace = true;
			}
			else if(*p == ')' && skipspace)
			{
				method += *p++;
				skipspace = false;
			}
			else if(*p == ' ' && skipspace)
				p++;
			else
				method += *p++;
		}
	}
	if(pstr)
	content += pstr;
end:
	if(presp->m_pContent)
		delete [] presp->m_pContent;
	presp->m_nContentLength = content.length();
	presp->m_pContent = new int8[presp->m_nContentLength];
	memcpy(presp->m_pContent,content.c_str(),presp->m_nContentLength);
}

std::string CHTTPService::GetShipmentSummary(CHTTPThreadInfo *pthread, CHTTPClientInfo *pclient, CHTTPMsg *presp)
{
	std::string str;
	std::string number,ccode;
	const int8 *pv = pclient->m_pRecvingMsg->m_RequestLine.GetURI()->GetHeaderValue("number");
	if(pv)
	{
		number = pv;
	}
	else
	{
		str = "please input shipment number";
		return str;
	}
	pv = pclient->m_pRecvingMsg->m_RequestLine.GetURI()->GetHeaderValue("ccode");
	if(pv)
		ccode = pv;
	else
	{
		str = "please input company code";
		return str;
	}
	
	CAPPParamItem result;
	if(pthread->m_pDB->QueryShipmentSummary(ccode,number,&result) == -1)
	{
		str = "query error.";
		return str;
	}
	
	//g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n items = %d",__FILE__,__LINE__,result.m_listItems.size());

	str = "<table class=\"admintable\" width=\"100%\" >\n";
	CAPPParamItem *pitem;
	
	str += "<tr>\n<td width=\"295\" class=\"admincls0\">单号</td>\n<td width=\"653\" class=\"admincls0\">" + number + "</td>\n</tr>\n";	
	str += "<tr>\n<td class=\"admincls0\">提货日期</td>\n<td class=\"admincls0\">";
	pitem = result.FindItem("pickup_date");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>\n</tr>\n";
	
	str += "<tr>\n<td class=\"admincls0\">当前状态</td>\n<td class=\"admincls0\">";
	pitem = result.FindItem("state");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>\n</tr>\n";	

	
	str += "<tr><td class=\"admincls0\">受理日期</td><td class=\"admincls0\">";
	pitem = result.FindItem("accept_date");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td></tr>";
	

	
	str += "<tr><td class=\"admincls0\">收货地址</td><td class=\"admincls0\">";
	pitem = result.FindItem("consignee_address");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td></tr>";

	
	str += "<tr><td class=\"admincls0\">到货日期</td><td class=\"admincls0\">";
	pitem = result.FindItem("arrived_date");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td></tr>";
	

	
	str += "<tr><td class=\"admincls0\">送达日期</td><td class=\"admincls0\">";
	pitem = result.FindItem("service_date");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td></tr>";
	str += "</table><br />";

	//发货人，收货人信息
	str += "<table class=\"admintable\" width=\"100%\" >";
	str += "<th width=\"332\" class=\"adminth\">发货人</th>";
	str += "<th width=\"332\" class=\"adminth\">收货人</th>";

	//收发货人信息
	str += "<tr>";
	str += "<td width=\"474\" class=\"admincls0\">";
	pitem = result.FindItem("consignor_name");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";

	str += "<td width=\"474\" class=\"admincls0\">";
	pitem = result.FindItem("consignee_name");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";
	str += "</tr>";
	
	//联系人信息
	str += "<tr>";
	str += "<td width=\"474\" class=\"admincls0\">";
	pitem = result.FindItem("consignor_contact");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";

	str += "<td width=\"474\" class=\"admincls0\">";
	pitem = result.FindItem("consignee_contact");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";
	str += "</tr>";

	//联系电话
	str += "<tr>";
	str += "<td width=\"474\" class=\"admincls0\">";
	pitem = result.FindItem("consignor_phone");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";

	str += "<td width=\"474\" class=\"admincls0\">";
	pitem = result.FindItem("consignee_phone");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";
	str += "</tr>";

	//联系地址
	str += "<tr>";
	str += "<td width=\"474\" class=\"admincls0\">";
	pitem = result.FindItem("consignor_address");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";

	str += "<td width=\"474\" class=\"admincls0\">";
	pitem = result.FindItem("consignee_address");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";
	str += "</tr>";
	str += "</table><br />";

	//运单详情
	str += "<h1 class=\"title\">运单详情</h1>";
	str += "<table class=\"admintable\" width=\"100%\" >";
	str += "<tr>";
	str += "<td width=\"295\" class=\"admincls0\">";
	str += "发站:</td>";
	str += "<td width=\"653\" class=\"admincls0\">";
	pitem = result.FindItem("dispach_station_name");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";
	str += "<td width=\"295\" class=\"admincls0\">到站:</td>";
	str += "<td width=\"653\" class=\"admincls0\">";
	pitem = result.FindItem("dest_station_name");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";
	str += "</tr>";

	
	str += "<tr>";
	str += "<td width=\"295\" class=\"admincls0\">";
	str += "货物名称:</td>";
	str += "<td width=\"653\" class=\"admincls0\">";
	pitem = result.FindItem("goods_name");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";
	str += "<td width=\"295\" class=\"admincls0\">包装:</td>";
	str += "<td width=\"653\" class=\"admincls0\">";
	pitem = result.FindItem("package_name");
	if(pitem)
		str += pitem->m_strValue;
	str += "</td>";
	str += "</tr>";

	str += "<tr>";
	str += "<td width=\"295\" class=\"admincls0\">";
	str += "件数:</td>";
	str += "<td width=\"653\" class=\"admincls0\">";
	pitem = result.FindItem("piece");
	if(pitem)
		str += pitem->m_strValue;
	str += "件</td>";
	str += "<td width=\"295\" class=\"admincls0\">重量:</td>";
	str += "<td width=\"653\" class=\"admincls0\">";
	pitem = result.FindItem("weight");
	if(pitem)
		str += pitem->m_strValue;
	str += "kg</td>";
	str += "</tr>";


	str += "<tr>";
	str += "<td width=\"295\" class=\"admincls0\">";
	str += "体积:</td>";
	str += "<td width=\"653\" class=\"admincls0\">";
	pitem = result.FindItem("volume");
	if(pitem)
		str += pitem->m_strValue;
	str += "m³</td>";
	str += "<td width=\"295\" class=\"admincls0\"></td>";
	str += "<td width=\"653\" class=\"admincls0\">";
	
	str += "</td>";
	str += "</tr>";


	str += "</table><br />";
//	g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n %s",__FILE__,__LINE__,str.c_str());
	return str;
}
