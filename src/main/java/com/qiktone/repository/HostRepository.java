package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Host;

import java.util.List;

/**
 * HostRepository
 *
 * @author Tom Zhao
 * @date 2016/3/22 0022
 */
public interface HostRepository extends EntityRepository<Host> {
    List<Host> findAll();
}
