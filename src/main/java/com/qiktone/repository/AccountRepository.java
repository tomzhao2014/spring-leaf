package com.qiktone.repository;

import com.qiktone.core.orm.mybatis.Page;
import com.qiktone.core.repository.EntityRepository;
import com.qiktone.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountRepository
 *
 * @author Tom Zhao
 * @date 2016/3/8 0008
 */
@Repository
public interface AccountRepository extends EntityRepository<Account> {
    Account login(@Param("username") String username,@Param("password") String password);
    List<Account> findByCompay(@Param("cid") long cid);
}
