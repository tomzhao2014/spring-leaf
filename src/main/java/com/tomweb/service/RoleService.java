package com.tomweb.service;

import com.tomweb.core.service.BaseService;
import com.tomweb.entity.Role;

/**
 * RoleService
 *
 * @author Tom Zhao
 * @date 2016/2/4 0004
 */
public interface RoleService extends BaseService<Role,Long> {
    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
