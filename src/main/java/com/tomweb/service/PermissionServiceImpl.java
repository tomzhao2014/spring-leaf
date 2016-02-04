package com.tomweb.service;

import com.tomweb.core.repository.EntityRepository;
import com.tomweb.core.service.BaseEntityService;
import com.tomweb.entity.Permission;
import com.tomweb.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/4 0004
 * Time: 17:03
 */
@Service
public class PermissionServiceImpl extends BaseEntityService<Permission> implements PermissionService{

    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public EntityRepository<Permission> getEntityRepository() {
        return permissionRepository;
    }
}
