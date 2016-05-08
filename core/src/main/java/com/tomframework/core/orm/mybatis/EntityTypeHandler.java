package com.tomframework.core.orm.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;


@MappedTypes(value = { MybatisType.class })
@MappedJdbcTypes(value = { JdbcType.BIGINT })
public class EntityTypeHandler<Entity extends MybatisType> extends BaseTypeHandler<Entity> {

	protected Log logger = LogFactory.getLog(getClass());

	private Class<Entity> type;
	
	public EntityTypeHandler(Class<Entity> type) {
		if (type == null)
			throw new IllegalArgumentException("Type argument cannot be null");
		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Entity parameter, JdbcType jdbcType) throws SQLException {
		ps.setLong(i, parameter.getId());
	}

	@Override
	public Entity getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Long id = rs.getLong(columnName);
		return getEntityInstance(id);
	}

	@Override
	public Entity getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Long id = rs.getLong(columnIndex);
		return getEntityInstance(id);
	}

	@Override
	public Entity getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Long id = cs.getLong(columnIndex);
		return getEntityInstance(id);
	}

	protected Entity getEntityInstance(Long id) {
		try {
			Entity entity = type.newInstance();
			entity.setId(id);
			return entity;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
