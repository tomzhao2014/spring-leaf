package com.qiktone.core.service;

import com.qiktone.core.orm.mybatis.Page;
import com.qiktone.core.repository.EntityRepository;
import com.qiktone.core.entity.BaseEntity;
import com.qiktone.core.entity.IdGenerator;
import com.qiktone.entity.vo.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service父类， 子类使用@Transactional注解控制事务<br>
 * 子类直接使用{@link EntityRepository}
 * 
 * @param <Entity> 实体类型
 * @author tom
 */
public abstract class BaseEntityService<Entity extends BaseEntity> implements BaseService<Entity, Long> {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	protected IdGenerator<Long> idGenerator;

	/**
	 * @return EntityRepository
	 */
	public abstract EntityRepository<Entity> getEntityRepository();

	@Override
	public Entity getById(Long id) {
		return getEntityRepository().getById(id);
	}

	@Override
	public final void save(Entity entity) {
		if (entity.getId() != null) {
			this.update(entity);
		} else {
			this.create(entity);
		}
	}

	@Override
	public void create(Entity entity) {
		// 创建对象前，生成主键
		if (entity.getId() == null) {
			entity.setId(idGenerator.generate());
		}
		getEntityRepository().insert(entity);
	}

	@Override
	public void update(Entity entity) {
		getEntityRepository().update(entity);
	}

	@Override
	public void delete(Long id) {
		getEntityRepository().delete(id);
	}

	@Override
	public void list(Page<Entity> page,Entity query) {
		getEntityRepository().list(page,query);
	}


}
