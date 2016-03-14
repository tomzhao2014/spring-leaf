package com.qiktone.core.repository;


import com.qiktone.core.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

public interface EntityRepository<Entity extends BaseEntity>{
	

	Entity getById(Long id);
	
	void insert(Entity entity);
	
	void update(Entity entity);

	void delete(Long id);
	
}
