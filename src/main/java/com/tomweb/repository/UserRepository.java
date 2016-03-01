package com.tomweb.repository;

import com.tomweb.core.repository.EntityRepository;
import com.tomweb.entity.User;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author Tom Zhao
 * @date 2016/2/4 0004
 */
@Repository
public interface UserRepository extends EntityRepository<User> {
    User get(Long userId);
    User findByUsername(String username);
}
