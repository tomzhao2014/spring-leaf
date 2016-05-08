package com.tomframework.core;

import java.util.Date;

public class BaseContext {
	private static String rootPath;
	private static String classPath;
	private static String contextPath;
	private static String host;// http://www.eteams.cn
	private static String passportHost;
	private static Date startTime;

	/**
	 * jms 目的地
	 */
	private static String jmsDestination;

	/**
	 * web根目录，不包含结尾的"/"
	 * 
	 * @return
	 */
	public static String getRootPath() {
		return rootPath;
	}

	public static void setRootPath(String rootPath) {
		BaseContext.rootPath = rootPath;
	}

	public static String getClassPath() {
		return classPath;
	}

	public static void setClassPath(String classPath) {
		BaseContext.classPath = classPath;
	}

	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextPath) {
		BaseContext.contextPath = contextPath;
	}

	public static Date getStartTime() {
		return startTime;
	}

	public static void setStartTime(Date startTime) {
		BaseContext.startTime = startTime;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		BaseContext.host = host;
	}

	public static String getPassportHost() {
		return passportHost;
	}

	public static void setPassportHost(String passportHost) {
		BaseContext.passportHost = passportHost;
	}

	public static String getJmsDestination() {
		return jmsDestination;
	}

	public static void setJmsDestination(String jmsDestination) {
		BaseContext.jmsDestination = jmsDestination;
	}

}
