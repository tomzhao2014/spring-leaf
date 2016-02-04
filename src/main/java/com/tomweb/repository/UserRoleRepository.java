package com.tomweb.repository;

import com.tomweb.core.repository.EntityRepository;
import com.tomweb.entity.User;
import com.tomweb.entity.UserRole;

import java.util.Set;

/**
 * UserRoleRepository
 *
 * @author Tom Zhao
 * @date 2016/2/4 0004
 */
public interface UserRoleRepository extends EntityRepository<UserRole> {
    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
