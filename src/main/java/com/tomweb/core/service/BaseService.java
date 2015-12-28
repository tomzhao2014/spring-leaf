package com.tomweb.core.service;

import java.io.Serializable;
import java.util.List;

import com.tomweb.core.orm.mybatis.Page;
import org.springframework.transaction.annotation.Transactional;

import com.tomweb.core.entity.BaseEntity;



/**
 * Service接口
 * 
 * @param <Entity> 实体类型
 * @param <PK> 主键类型
 * @author Ricky
 */
@Transactional
public interface BaseService<Entity extends BaseEntity, PK extends Serializable> {

	@Transactional(readOnly = true)
	public Entity getById(PK id);

	/**
	 * 实体对象无主键时调用 {@link #create(BaseEntity)}方法，有主键时{@link #update(BaseEntity)}方法
	 * 
	 * @param entity
	 */
	public void save(Entity entity);

	/**
	 * 创建 实体对象
	 * 
	 * @param entity
	 */
	public void create(Entity entity);

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 */
	public void update(Entity entity);

	public void delete(PK id);
	

}
