package com.tomweb.service;

import com.tomweb.core.repository.EntityRepository;
import com.tomweb.core.service.BaseEntityService;
import com.tomweb.entity.Role;
import com.tomweb.repository.RolePermissionRepository;
import com.tomweb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/4 0004
 * Time: 17:10
 */
@Service
public class RoleServiceImpl extends BaseEntityService<Role> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public EntityRepository<Role> getEntityRepository() {
        return roleRepository;
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {

    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {

    }
}
