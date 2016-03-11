package com.qiktone.entity;

/**
 * Created by tom on 2016/3/10.
 * 系统常量
 */
public class Constant extends BaseDomainEntity {


    private long companyId;
    private String descr;
    private String type;
    private String code;
    private int seq;
    private int uc;


    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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


}
