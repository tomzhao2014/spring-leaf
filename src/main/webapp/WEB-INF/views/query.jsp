<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>chaxunjieguo</title>
  <link rel="stylesheet" type="text/css" href="chaxunjieguo.css">
  <style>

    *{margin: 0px;
      padding: 0px;}
    body{
      width: 100%;
      font-family: "微软雅黑"
    }

    .container{
      width: 1180px;
      margin: 0px auto;}

    h2{
      color: #fff;
      background-color: #ff9102;
      font-size: 30px;
      text-align: center;
      line-height: 60px;
      height: 60px;}

    li{list-style: none;}

    .yd h4{
      line-height: 40px;
      height: 40px;
      background-color: #d1d0d1;
      font-size: 20px;
      font-weight: bold;
      color: #333;
    }
    .xq li h3{
      border: 1px solid #dbdadb;
      line-height: 30px;
      color: #666;
      font-size: 16px;
    }
    #container{
      margin-top: 25px;
      width: 50%;
      float: left;
    }
    #container1{
      margin-top: 25px;
      width: 50%;
      float: left;
    }


  </style>
</head>
<body>
<!--
int32 CIDBMysql::QueryShipmentSummary(const std::string &ccode,const std::string &number,CAPPParamItem *presult)
{
	std::string sql;
	sql = "select s.pickup_date,s.accept_date,s.service_date,s.arrived_date,s.dispach_station_name,s.dest_station_name,s.consignor_name,s.consignee_name,s.consignor_contact,s.consignee_contact,s.consignor_phone,s.consignee_phone,s.consignor_address,s.consignee_address,s.goods_name,s.package_name,s.piece,s.weight,s.volume,n.descr from SHIPPING_ORDER s,LOCAL_SHIPPING_ORDER t,COMPANY c, CONSTANT n where t.shipping_order_id=s.id and s.number='";
	sql += number;
	sql += "' and n.type='shipping_order_state_type' and n.code=t.state and s.company_id=c.id and c.code='";
	sql += ccode;
	sql += "' limit 1";
	g_Log.printf(LOG_LEVEL_MSG,"%s,%d\n %s",__FILE__,__LINE__,sql.c_str());
	if(-1 == myquery(sql.c_str()))
	{
		g_Log.printf(LOG_LEVEL_ERROR,"%s,%d\n %s",__FILE__,__LINE__,GetLastError());
		return -1;
	}



-->
<div>
  <h2>查询结果</h2>
</div>

<div class="container">
  <div class="yd">
    <h4>运单基本信息</h4>
  </div>
  <div class="xq">
    <li><h3>单号：</h3></li>
    <li><h3>提货日期：</h3></li>
    <li><h3>当前状态：</h3></li>
    <li><h3>受理日期：</h3></li>
    <li><h3>收货地址：</h3></li>
    <li><h3>发货日期：</h3></li>
    <li><h3>到货日期：</h3></li>
    <li><h3>到达日期：</h3></li>
  </div>

  <div class="container" id="container">
    <div class="yd">
      <h4>发货人</h4>
    </div>
    <div class="xq">
      <li><h3>发货单位：</h3></li>
      <li><h3>联系人：</h3></li>
      <li><h3>电话：</h3></li>
      <li><h3>提货地址：</h3></li>
    </div>
  </div>

  <div class="container" id="container1">
    <div class="yd">
      <h4>收货人</h4>
    </div>
    <div class="xq">
      <li><h3>收货单位：</h3></li>
      <li><h3>联系人：</h3></li>
      <li><h3>电话：</h3></li>
      <li><h3>收货地址：</h3></li>
    </div>
  </div>

  <div class="container" id="container">
    <div class="yd">
      <h4>运单详情</h4>
    </div>
    <div class="xq">
      <li><h3>发站：</h3></li>
      <li><h3>货物名称：</h3></li>
      <pre><li><h3>件数：               件</h3></li></pre>
      <pre><li><h3>体积：               <ins>m</ins>3</h3></li></pre>
    </div>
  </div>

  <div class="container" id="container1">
    <div class="yd">
      <h4></h4>
    </div>
    <div class="xq">
      <li><h3>到站：</h3></li>
      <li><h3>包装：</h3></li>
      <pre><li><h3>重量：                 kg</h3></li></pre>
      <li><h3>：</h3></li>
    </div>
  </div>


</div>
</body>
</html>