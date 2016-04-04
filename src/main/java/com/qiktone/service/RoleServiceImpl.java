package com.qiktone.service;

import com.qiktone.core.repository.EntityRepository;
import com.qiktone.core.service.BaseEntityService;
import com.qiktone.entity.Account;
import com.qiktone.entity.Role;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/8 0008
 * Time: 14:02
 */
@Service
public class RoleServiceImpl extends BaseEntityService<Role> implements AccountService {
    @Override
    public EntityRepository<Role> getEntityRepository() {
        return null;
    }
}
