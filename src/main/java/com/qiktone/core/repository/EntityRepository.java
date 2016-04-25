package com.qiktone.core.repository;


import com.qiktone.core.entity.BaseEntity;
import com.qiktone.core.orm.mybatis.Page;
import com.qiktone.entity.vo.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EntityRepository<Entity extends BaseEntity>{
	

	Entity getById(Long id);
	
	void insert(Entity entity);
	
	void update(Entity entity);

	void delete(Long id);

	List<Entity> list(Page<Entity> page,Entity query);

	List<Entity> findAll();

}
