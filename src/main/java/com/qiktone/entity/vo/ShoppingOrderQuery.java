package com.qiktone.entity.vo;

import com.qiktone.entity.BaseDomainEntity;

import java.util.Date;

/**
 * Created by tom on 2016/4/27.
 */
public class ShoppingOrderQuery extends BaseDomainEntity {

    //托运单号
    private String number;
    //提货日期
    private Date pickupDate;
    //当前状态
    private String descr;
    //受理日期
    private Date acceptDate;
    //收货地址
    private String consigneeAddress;
    //发货人地址
    private String consignorAddress;
    //  到达日期
    private Date arrivedDate;

    private Date workingTime;

    private Date serviceDate;

    //发站
    private String dispachStationName ;
    //到站
    private String destStationName ;
    //发货单位
    private String consignorName ;
    //收货单位
    private String consigneeName ;
    //发货方联系人
    private String consignorContact ;
    // 收货方联系人
    private String consigneeContact;
    //发货方电话
    private String consignorPhone ;
    //收货方电话
    private String consigneePhone;


    //物品名称
    private String goodsName;
    //包装名称
    private String packageName;
    //件数
    private String  piece;
    //重量
    private String weight;
    //体积
    private String volume;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress;
    }

    public Date getArrivedDate() {
        return arrivedDate;
    }

    public void setArrivedDate(Date arrivedDate) {
        this.arrivedDate = arrivedDate;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getDispachStationName() {
        return dispachStationName;
    }

    public void setDispachStationName(String dispachStationName) {
        this.dispachStationName = dispachStationName;
    }

    public String getDestStationName() {
        return destStationName;
    }

    public void setDestStationName(String destStationName) {
        this.destStationName = destStationName;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsignorContact() {
        return consignorContact;
    }

    public void setConsignorContact(String consignorContact) {
        this.consignorContact = consignorContact;
    }

    public String getConsigneeContact() {
        return consigneeContact;
    }

    public void setConsigneeContact(String consigneeContact) {
        this.consigneeContact = consigneeContact;
    }

    public String getConsignorPhone() {
        return consignorPhone;
    }

    public void setConsignorPhone(String consignorPhone) {
        this.consignorPhone = consignorPhone;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Date getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Date workingTime) {
        this.workingTime = workingTime;
    }
}
