package com.qiktone.repository;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * AccountRepository
 *
 * @author Tom Zhao
 * @date 2016/3/8 0008
 */
@Repository
public interface AccountRepository extends EntityRepository<Account> {
}
