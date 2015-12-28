package com.tomweb.entity;


/**
 * Created by tom on 15/12/12.
 */
public class Member extends BaseDomainEntity{

    private String nikeName;
    private String type;
    private String status;


    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
