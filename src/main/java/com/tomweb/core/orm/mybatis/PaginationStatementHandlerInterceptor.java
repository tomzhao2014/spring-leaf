package com.tomweb.core.orm.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.tomweb.core.orm.dialect.Dialect;
import com.tomweb.core.orm.dialect.DialectFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;



@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationStatementHandlerInterceptor implements Interceptor {

	private static final Log logger = LogFactory.getLog(PaginationStatementHandlerInterceptor.class);

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final ReflectorFactory DEFAULT_OBJECT_REFELECTOR_FACTORY = new DefaultReflectorFactory();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_OBJECT_REFELECTOR_FACTORY);
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		// 没有分页参数
		if ((rowBounds == null) || (rowBounds == RowBounds.DEFAULT)) {
			return invocation.proceed();
		}

		ParameterHandler parameterHandler = statementHandler.getParameterHandler();
		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		Dialect dialect = DialectFactory.buildDialect(configuration);
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		// 获取总记录数
		Page<?> page = (Page<?>) rowBounds;
		if (page.autoCount) {
			String countSql = dialect.getCountString(originalSql);
			Connection connection = (Connection) invocation.getArgs()[0];
			long total = getTotal(parameterHandler, connection, countSql);
			page.setTotalCount(total);
		}

		// 设置物理分页语句
		metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, page.getOffset(), page.getLimit()));
		// 屏蔽mybatis原有分页
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		if (logger.isDebugEnabled()) {
			BoundSql boundSql = statementHandler.getBoundSql();
			logger.debug("分页SQL : " + boundSql.getSql());
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
	}

	/**
	 * 获取总计录
	 * 
	 * @param parameterHandler
	 * @param connection
	 * @param countSql
	 * @return
	 * @throws Exception
	 */
	private long getTotal(ParameterHandler parameterHandler, Connection connection, String countSql) throws Exception {
		PreparedStatement prepareStatement = connection.prepareStatement(countSql);
		parameterHandler.setParameters(prepareStatement);
		ResultSet rs = prepareStatement.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		prepareStatement.close();
		return count;
	}
}
