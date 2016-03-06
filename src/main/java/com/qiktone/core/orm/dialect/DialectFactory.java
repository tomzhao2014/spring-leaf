package com.qiktone.core.orm.dialect;

import org.apache.ibatis.session.Configuration;

public class DialectFactory {

	public static Dialect buildDialect(Configuration configuration){
		Dialect dialect = null;
		Dialect.Type databaseType  = null;
		try{
			databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		} catch(Exception e){
			//ignore
		}
		if(databaseType == null){
			throw new RuntimeException("the value of the dialect property in mybatis-config.xml is not defined : " + configuration.getVariables().getProperty("dialect"));
		}
		switch(databaseType){
			case MYSQL:
				return new MySql5Dialect();
		case ORACLE:
			return new OracleDialect();
		}
		return dialect;
	}
}
