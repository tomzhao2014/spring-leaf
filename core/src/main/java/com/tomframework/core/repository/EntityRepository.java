package com.tomframework.core.repository;


import com.tomframework.core.entity.BaseEntity;
import com.tomframework.core.orm.mybatis.Page;

import java.util.List;

public interface EntityRepository<Entity extends BaseEntity>{
	

	Entity getById(Long id);
	
	void insert(Entity entity);
	
	void update(Entity entity);

	void delete(Long id);

	List<Entity> list(Page<Entity> page,Entity query);

	List<Entity> findAll();

}
