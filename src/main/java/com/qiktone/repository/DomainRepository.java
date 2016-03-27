package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Domain;

import java.util.List;

/**
 * DomainRepository
 *
 * @author Tom Zhao
 * @date 2016/3/22 0022
 */
public interface DomainRepository extends EntityRepository<Domain> {
    List<Domain>  findAll();
}
