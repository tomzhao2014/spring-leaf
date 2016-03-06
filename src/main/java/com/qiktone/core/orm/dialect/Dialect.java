package com.qiktone.core.orm.dialect;

public abstract class Dialect {

	public static enum Type{
		MYSQL,
		ORACLE
	}
	
	public abstract String getLimitString(String sql, int offset, int limit);
	
	public abstract String getCountString(String sql);
	
}
