package com.qiktone.entity;



/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/8 0008
 * Time: 13:28
 */
public class Account extends BaseDomainEntity {
    private String username;
    private String type;
    private Constant atype;
    private String password;
    private String state;
    private Constant astate;
    private Long roleId;
    private Role role;
    private String appType;
    private Long companyId;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Constant getAstate() {
        return astate;
    }

    public void setAstate(Constant astate) {
        this.astate = astate;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Constant getAtype() {
        return atype;
    }

    public void setAtype(Constant atype) {
        this.atype = atype;
    }
}


