package com.qiktone.entity;

/**
 * Created by tom on 2016/3/10.
 * 系统常量
 */
public class Constant extends BaseDomainEntity {

    //描述
    private String descr;
    //类别
    private String type;
    //代码
    private String code;
    //排序
    private int seq;
    //
    private int uc;
    //所属公司
    private Long companyId;
    private Company company;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getUc() {
        return uc;
    }

    public void setUc(int uc) {
        this.uc = uc;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
