package com.tomweb.core.repository;


import com.tomweb.core.entity.BaseEntity;
public interface EntityRepository<Entity extends BaseEntity>{
	

	Entity getById(Long id);
	
	void insert(Entity entity);
	
	void update(Entity entity);

	void delete(Long id);
	
}
