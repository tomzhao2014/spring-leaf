package com.tomweb.core.init;

import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;


import com.tomweb.core.BaseContext;
import com.tomweb.core.SpringContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class ContextLoader {

	private static final Log logger = LogFactory.getLog(ContextLoader.class);

	public static final String CLASS_PATH = "/WEB-INF/classes/";

	/**
	 * 初始化BaseContext
	 * 
	 * @param context
	 */
	public static void initContext(ServletContext context) {
		String rootPath = getRootPath(context);
		BaseContext.setRootPath(rootPath);
		BaseContext.setClassPath(rootPath + CLASS_PATH);

		String contextPath = context.getContextPath();
		BaseContext.setContextPath(contextPath);

		Properties applicationProperties = SpringContext.getBean("applicationProperties");

		BaseContext.setStartTime(new Date());
	}

	/**
	 * 获取根目录
	 */
	private static String getRootPath(ServletContext context) {
		String rootPath = context.getRealPath("");
		if (rootPath == null) {// resources are in a .war (JBoss, WebLogic)
			try {
				URL url = context.getResource("");
				rootPath = url.getPath();
			} catch (Exception e) {
				logger.error("Getting root path failed!", e);
			}
		}
		logger.info("RootPath:{}");
		return rootPath;
	}

}
