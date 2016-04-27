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
    //
    private Date arrivedDate;

  /*  pickup_date 提货日期,
    accept_date 受理日期,
    service_date ?,
    arrived_date 到达日期,
    dispach_station_name 发站 ,
    dest_station_name 到站,
    consignor_name 发货单位 ,
    consignee_name 收货单位,
    consignor_contact 发货方联系人,
    consignee_contact 收货方联系人,
    consignor_phone 发货方电话,
    consignee_phone 收货方电话,
    consignor_address 发货方地址,
    consignee_address 收货方地址,
    goods_name 物品名称,
    package_name 包装名称,
    piece 件数,
    weight 重量,
    volume 体积,
    descr 当前状态*/

}
