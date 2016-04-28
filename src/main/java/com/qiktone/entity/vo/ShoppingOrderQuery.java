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


}
