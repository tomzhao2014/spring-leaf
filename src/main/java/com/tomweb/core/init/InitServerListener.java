package com.tomweb.core.init;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tomweb.core.SpringContext;

public class InitServerListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		ContextLoader.initContext(context);
		//加载Trigger
		//TriggerLoader triggerLoader = SpringContext.getBean(TriggerLoader.class);
		//triggerLoader.loaderTrigger();
		
		//InitServerService service
		Map<String, InitServerService> services = SpringContext.getBeans(InitServerService.class);
		for(InitServerService service:services.values()){
			service.initServer();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//TODO
	}

}
