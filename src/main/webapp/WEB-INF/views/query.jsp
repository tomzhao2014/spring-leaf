<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>chaxunjieguo</title>
  <link rel="stylesheet" type="text/css" href="chaxunjieguo.css">
  <script src="/js/jquery.min.js"></script>
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

<div>
  <h2>查询结果</h2>
</div>

<div class="container">
  <div class="yd">
    <h4>运单基本信息</h4>
  </div>
  <div class="xq">
    <li><h3>单号：${result.number}</h3></li>
    <li><h3>提货日期：<c:if test="${result.pickupDate!=null}"> <fmt:formatDate value="${result.availableDate}" pattern="yyyy-MM-dd"/> </c:if></h3></li>
    <li><h3>当前状态：${result.descr}</h3></li>
    <li><h3>受理日期：<c:if test="${result.acceptDate!=null}"> <fmt:formatDate value="${result.acceptDate}" pattern="yyyy-MM-dd"/> </c:if></h3></li>
    <li><h3>收货地址：${result.consigneeAddress}</h3></li>
    <li><h3>发货日期：<c:if test="${result.workingTime!=null}"> <fmt:formatDate value="${result.workingTime}" pattern="yyyy-MM-dd"/> </c:if></h3></li>
    <li><h3>到货日期：<c:if test="${result.arrivedDate!=null}"> <fmt:formatDate value="${result.arrivedDate}" pattern="yyyy-MM-dd "/> </c:if></h3></li>
    <li><h3>到达日期：<c:if test="${result.arrivedDate!=null}"> <fmt:formatDate value="${result.arrivedDate}" pattern="yyyy-MM-dd"/> </c:if></h3></li>
  </div>

  <div class="container" id="container">
    <div class="yd">
      <h4>发货人</h4>
    </div>
    <div class="xq">
      <li><h3>发货单位：${result.consignorName}</h3></li>
      <li><h3>联系人：${result.consignorContact}</h3></li>
      <li><h3>电话：${result.consignorPhone}</h3></li>
      <li><h3>提货地址：${result.consignorAddress}</h3></li>
    </div>
  </div>

  <div class="container" id="container1">
    <div class="yd">
      <h4>收货人</h4>
    </div>
    <div class="xq">
      <li><h3>收货单位：${result.consigneeName}</h3></li>
      <li><h3>联系人：${result.consigneeContact}</h3></li>
      <li><h3>电话：${result.consigneePhone}</h3></li>
      <li><h3>收货地址：${result.consigneeAddress}</h3></li>
    </div>
  </div>

  <div class="container" id="container">
    <div class="yd">
      <h4>运单详情</h4>
    </div>
    <div class="xq">
      <li><h3>发站：${result.dispachStationName}</h3></li>
      <li><h3>货物名称：${result.goodsName}</h3></li>
      <pre><li><h3>件数：${result.piece} 件</h3></li></pre>
      <pre><li><h3>体积：${result.volume} <ins>m</ins>3</h3></li></pre>
    </div>
  </div>

  <div class="container" id="container1">
    <div class="yd">
      <h4></h4>
    </div>
    <div class="xq">
      <li><h3>到站：${result.destStationName}</h3></li>
      <li><h3>包装：${result.packageName}</h3></li>
      <pre><li><h3>重量： ${result.weight}kg </h3></li></pre>
      <li><h3></h3></li>
    </div>
  </div>


</div>
<script>
  $(function(){
     var result = "${result}";
    if(!result){
      alert("本次查询没有符合的结果，请输入正确的单号和部门");
    }
  })
</script>
</body>
</html>