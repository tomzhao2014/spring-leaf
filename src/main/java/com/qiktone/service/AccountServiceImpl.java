package com.qiktone.service;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.core.service.BaseEntityService;
import com.qiktone.entity.Account;
import com.qiktone.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/8 0008
 * Time: 14:02
 */
@Service
public class AccountServiceImpl extends BaseEntityService<Account> implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public EntityRepository<Account> getEntityRepository() {
        return accountRepository;
    }

    @Override
    public void create(Account account) {
        super.create(account);
    }
}
