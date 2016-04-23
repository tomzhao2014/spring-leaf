package com.qiktone.entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/8 0008
 * Time: 13:37
 */
public class Role extends BaseDomainEntity {
    private String type;
    private String descr;

    private Long compnay_id;

    private Company company;

    private Long version;

    List<RolePrivilege> modules;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Long getCompnay_id() {
        return compnay_id;
    }

    public void setCompnay_id(Long compnay_id) {
        this.compnay_id = compnay_id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<RolePrivilege> getModules() {
        return modules;
    }

    public void setModules(List<RolePrivilege> modules) {
        this.modules = modules;
    }
}
