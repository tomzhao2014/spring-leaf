<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.qiktone.util.DateUtils" %>
<div class="form-group">
  <label class="col-sm-2 control-label" >全称名称：</label>
  <div class="col-sm-4">
    <input type="text" name="name" value="${company!=null?company.name:""}" id="name" placeholder="全称名称"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label">简称：</label>
  <div class="col-sm-4">
    <input type="text" name="shortName" value="${company!=null?company.shortName:""}" id="shortName" placeholder="简称"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >代码：</label>
  <div class="col-sm-4">
    <input type="text" name="code" value="${company!=null?company.code:""}" id="code"
           class="form-control validate[required,minSize[2]" disabled="disabled"/>
  </div>
  <label class="col-sm-2 control-label" >应用类型：</label>
  <div class="col-sm-4">
    <select class="form-control" name="type">
      <option value="">请选择</option>
      <c:forEach var="companyType" items="${companyTypes}">
        <c:choose>
          <c:when test="${company!=null and company.type==companyType.code}">
            <option value="${companyType.code}" selected="selected">${companyType.descr}</option>
          </c:when>
          <c:otherwise>
            <option value="${companyType.code}" >${companyType.descr}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
      </select>
  </div>
</div>


<div class="form-group">
  <label class="col-sm-2 control-label" >状态：</label>
  <div class="col-sm-4">
    <select class="form-control" name="state">
      <option value="">请选择</option>
      <c:forEach var="companyStateType" items="${companyStateTypes}">
        <c:choose>
          <c:when test="${company!=null and  company.state==companyStateType.code}">
            <option value="${companyStateType.code}" selected="selected">${companyStateType.descr}</option>
          </c:when>
          <c:otherwise>
            <option value="${companyStateType.code}">${companyStateType.descr}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
      </select>
  </div>
  <label class="col-sm-2 control-label" >停用原因：</label>
  <div class="col-sm-4">
    <input type="text" name="disabledReason" value="${company!=null?company.disabledReason:""}" id="disabledReason" placeholder="停用原因"
           class="form-control validate[required]"/>
  </div>
</div>


<div class="form-group">
  <label class="col-sm-2 control-label" >合同编号：</label>
  <div class="col-sm-4">
    <input type="text" name="contractNumber" value="${company!=null?company.contractNumber:""}" id="contractNumber" placeholder="合同编号"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label" >最多账号数：</label>
  <div class="col-sm-4">
    <input type="text" name="maxAccounts" value="${company!=null?company.maxAccounts:""}" id="maxAccounts" placeholder="最多账号数"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >起始日期：</label>
  <div class="col-sm-4">
    <input type="text" name="startDate" value="${company!=null?DateUtils.getDateString(company.startDate):""}" id="startDate" placeholder="起始日期"
           class="form-control validate[required,minSize[2]" data-date-format="yyyy-mm-dd"/>
  </div>

  <label class="col-sm-2 control-label">终止日期：</label>
  <div class="col-sm-4">
    <input type="text" name="availableDate" value="${company!=null?DateUtils.getDateString(company.availableDate):""}" id="availableDate" placeholder="终止日期"
           class="form-control validate[required]" data-date-format="yyyy-mm-dd"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >联系人：</label>
  <div class="col-sm-4">
    <input type="text" name="contact" value="${company!=null?company.contact:""}" id="contact" placeholder="联系人"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label" >公司电话：</label>
  <div class="col-sm-4">
    <input type="text" name="phone" value="${company!=null?company.phone:""}" id="phone" placeholder="公司电话"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >传真：</label>
  <div class="col-sm-4">
    <input type="text" name="fax" value="${company!=null?company.fax:""}" id="fax" placeholder="传真"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label" >公司邮箱：</label>
  <div class="col-sm-4">
    <input type="text" name="email" value="${company!=null?company.email:""}" id="email" placeholder="公司邮箱"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >公司地址：</label>
  <div class="col-sm-10">
    <input type="text" name="address" value="${company!=null?company.address:""}" id="address" placeholder="公司地址"
           class="form-control validate[required,minSize[2]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >所属域：</label>
  <div class="col-sm-4">
    <select class="form-control" name="domain_id">
      <option value="">请选择</option>
      <c:forEach var="domain" items="${domains}">

        <c:choose>
          <c:when test="${company!=null and company.domainId == domain.id}">
            <option value="${domain.id}" selected="selected">${domain.name}</option>
          </c:when>
          <c:otherwise>
            <option value="${domain.id}">${domain.name}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>
  </div>
  <label class="col-sm-2 control-label" >服务器：</label>
  <div class="col-sm-4">
    <select class="form-control" name="host_id">
      <option value="">请选择${company.hostId}</option>
      <c:forEach var="host" items="${hosts}">
        <c:choose>
          <c:when test="${company!=null and company.hostId == host.id}">
            <option value="${host.id}" selected="selected">${host.name}-${host.id}</option>
          </c:when>
          <c:otherwise>
            <option value="${host.id}">${host.name}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >服务费：</label>
  <div class="col-sm-4">
    <input type="text" name="serviceFee" value="${company!=null?company.serviceFee:""}" id="serviceFee" placeholder="服务费"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label" >销售：</label>
  <div class="col-sm-4">
    <input type="text" name="salesman" value="${company!=null?company.salesman:""}" id="salesman" placeholder="销售"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >服务费已收：</label>
  <div class="col-sm-4">
    <c:choose>
      <c:when test="${company!=null && company.isCollected()}">
        <input type="radio" name="isCollected" value="1" checked>是
        <input type="radio" name="isCollected" value="0">否
      </c:when>
      <c:otherwise>
        <input type="radio" name="isCollected" value="1">是
        <input type="radio" name="isCollected" value="0" checked >否
      </c:otherwise>
    </c:choose>


  </div>
  <label class="col-sm-2 control-label" >收款日期：</label>
  <div class="col-sm-4">
    <input type="text" name="collectDate" value="${company!=null?DateUtils.getDateString(company.collectDate):""}" id="collectDate" placeholder="收款日期"
           class="form-control validate[required]" data-date-format="yyyy-mm-dd"/>
  </div>
</div>


  <div class="clearfix form-actions">
    <div class="col-md-offset-3 col-md-9">
      <button class="btn btn-info" type="submit">
        <i class="ace-icon fa fa-check bigger-110"></i>
        保存
      </button>


      <button class="btn" type="reset">
        <i class="ace-icon fa fa-undo bigger-110"></i>
        重置
      </button>
    </div>
  </div>
</form>

<script>
  $(function(){
    $('#startDate,#availableDate,#collectDate').datepicker();
  });
</script>