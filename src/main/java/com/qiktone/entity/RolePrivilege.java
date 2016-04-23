package com.qiktone.entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/8 0008
 * Time: 13:37
 */
public class RolePrivilege extends BaseDomainEntity {
    private Long roleId;

    private String descr;

    private String type;

    private String parent;

    private boolean hasAuthority;

    private Role role;

    private RolePrivilege fathor;

    private List<RolePrivilege> functions;

    private List<RolePrivilege> ops;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public boolean isHasAuthority() {
        return hasAuthority;
    }

    public void setHasAuthority(boolean hasAuthority) {
        this.hasAuthority = hasAuthority;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public RolePrivilege getFathor() {
        return fathor;
    }

    public void setFathor(RolePrivilege fathor) {
        this.fathor = fathor;
    }

    public List<RolePrivilege> getFunctions() {
        return functions;
    }

    public void setFunctions(List<RolePrivilege> functions) {
        this.functions = functions;
    }

    public List<RolePrivilege> getOps() {
        return ops;
    }

    public void setOps(List<RolePrivilege> ops) {
        this.ops = ops;
    }
}
