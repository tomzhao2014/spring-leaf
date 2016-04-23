package com.qiktone.entity.constant;

/**
 * Created by tom on 2016/4/10.
 */
public enum AccountType {

    ADMIN("超级帐号","admin"),
    SERVICE("业务帐号","service"),
    COMPANY("公司帐号","company"),
    USER("普通帐号","user"),
    DEVICE("设备帐号","device");


    private String name;
    private String value;

    AccountType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
