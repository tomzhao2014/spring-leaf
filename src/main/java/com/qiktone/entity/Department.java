package com.qiktone.entity;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/8 0008
 * Time: 13:30
 */
public class Department extends BaseDomainEntity{
    private String title;
    private String name;
    private String code;
    private String type;
    private Long parentId;
    private Department parent;
    private String contact;
    private String phone;
    private String mobile;
    private String fax;
    private String bank;
    private String backAccount;
    private String accountTitle;
    private String address;
    private boolean isTemp;
    private Long companyId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBackAccount() {
        return backAccount;
    }

    public void setBackAccount(String backAccount) {
        this.backAccount = backAccount;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isTemp() {
        return isTemp;
    }

    public void setTemp(boolean isTemp) {
        this.isTemp = isTemp;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
