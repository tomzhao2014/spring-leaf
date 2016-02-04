package com.tomweb.repository;

import com.tomweb.core.repository.EntityRepository;
import com.tomweb.entity.Permission;
import org.springframework.stereotype.Repository;

/**
 * PermissionRepository
 *
 * @author Tom Zhao
 * @date 2016/2/4 0004
 */
@Repository
public interface PermissionRepository extends EntityRepository<Permission> {

}
