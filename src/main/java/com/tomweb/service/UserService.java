package com.tomweb.service;

import com.tomweb.core.service.BaseService;
import com.tomweb.entity.User;

import java.util.Set;

/**
 * UserService
 *
 * @author Tom Zhao
 * @date 2016/2/4 0004
 */
public interface UserService extends BaseService<User,Long> {
    public void changePassword(Long userId, String newPassword);//修改密码
    public void correlationRoles(Long userId, Long... roleIds); //添加用户-角色关系
    public void uncorrelationRoles(Long userId, Long... roleIds);// 移除用户-角色关系
    public User findByUsername(String username);// 根据用户名查找用户
    public Set<String> findRoles(String username);// 根据用户名查找其角色
    public Set<String> findPermissions(String username); //根据用户名查找其权限
}
