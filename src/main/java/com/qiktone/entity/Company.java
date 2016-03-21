package com.qiktone.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 公司
 * Created by tom on 16/3/5.
 */
public class Company extends BaseDomainEntity {
    //公司简称
    private String shortName;
    //公司代码
    private String code;
    //服务类型
    private String type;
    //公司状态
    private String state;
    //停用原因
    private String disabledReason;
    //合同编号
    private String contractNumber;
    //最多账号数
    private Long maxAccounts;
    //开始日期
    private Date startDate;
    //结束日期
    private Date availableDate;
    //联系人
    private String  contract;
    //联系电话
    private String phone;
    //传真
    private String fax;
    //公司邮箱
    private String email;
    //公司地址
    private String address;
    //所属域
    private Long domainId;
    //所属服务器
    private Long hostId;
    //服务费
    private BigDecimal serviceFee;
    //销售人员
    private String salesman;
    //已收费？
    private boolean isCollected;
    //收费日期
    private Date collectDate;


    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDisabledReason() {
        return disabledReason;
    }

    public void setDisabledReason(String disabledReason) {
        this.disabledReason = disabledReason;
    }



    public Long getMaxAccounts() {
        return maxAccounts;
    }

    public void setMaxAccounts(Long maxAccounts) {
        this.maxAccounts = maxAccounts;
    }



    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
}
