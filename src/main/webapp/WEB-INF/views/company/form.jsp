<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="form-group">
  <label class="col-sm-2 control-label" >全称名称：</label>
  <div class="col-sm-4">
    <input type="text" name="name" value="1111" id="name" placeholder="全称名称"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label">简称：</label>
  <div class="col-sm-4">
    <input type="text" name="shortName" value="222" id="shortName" placeholder="简称"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >代码：</label>
  <div class="col-sm-4">
    <input type="text" name="code" value="1111" id="code" placeholder="代码"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label" >应用类型：</label>
  <div class="col-sm-4">
    <select class="form-control" name="type">
      <option value=""></option>
      <c:forEach var="companyType" items="${companyTypes}">
        <option value="${companyType.code}">${companyType.descr}</option>
      </c:forEach>
      </select>
  </div>
</div>


<div class="form-group">
  <label class="col-sm-2 control-label" >状态：</label>
  <div class="col-sm-4">
    <select class="form-control" name="state">
      <option value=""></option>
      <c:forEach var="companyStateType" items="${companyStateTypes}">
        <option value="${companyStateType.code}">${companyStateType.descr}</option>
      </c:forEach>
      </select>
  </div>
  <label class="col-sm-2 control-label" >停用原因：</label>
  <div class="col-sm-4">
    <input type="text" name="disabledReason" value="222" id="disabledReason" placeholder="停用原因"
           class="form-control validate[required]"/>
  </div>
</div>


<div class="form-group">
  <label class="col-sm-2 control-label" >合同编号：</label>
  <div class="col-sm-4">
    <input type="text" name="contractNumber" value="1111" id="contractNumber" placeholder="合同编号"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label" >最多账号数：</label>
  <div class="col-sm-4">
    <input type="text" name="maxAccounts" value="222" id="maxAccounts" placeholder="最多账号数"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >起始日期：</label>
  <div class="col-sm-4">
    <input type="text" name="startDate" value="1111" id="startDate" placeholder="起始日期"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label">终止日期：</label>
  <div class="col-sm-4">
    <input type="text" name="availableDate" value="222" id="availableDate" placeholder="终止日期"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >联系人：</label>
  <div class="col-sm-4">
    <input type="text" name="contact" value="1111" id="contact" placeholder="联系人"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label" >公司电话：</label>
  <div class="col-sm-4">
    <input type="text" name="phone" value="222" id="phone" placeholder="公司电话"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >传真：</label>
  <div class="col-sm-4">
    <input type="text" name="fax" value="1111" id="fax" placeholder="传真"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label" >公司邮箱：</label>
  <div class="col-sm-4">
    <input type="text" name="email" value="222" id="email" placeholder="公司邮箱"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >公司地址：</label>
  <div class="col-sm-10">
    <input type="text" name="address" value="1111" id="address" placeholder="传真"
           class="form-control validate[required,minSize[2]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >所属域：</label>
  <div class="col-sm-4">
    <select class="form-control" name="domain_id">
      <option value=""></option>
      <c:forEach var="domain" items="${domains}">
        <option value="${domain.id}">${domain.name}</option>
      </c:forEach>
    </select>
  </div>
  <label class="col-sm-2 control-label" >服务器：</label>
  <div class="col-sm-4">
    <select class="form-control" name="domain_id">
      <option value=""></option>
      <c:forEach var="host" items="${hosts}">
        <option value="${host.id}">${host.name}</option>
      </c:forEach>
    </select>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >服务费：</label>
  <div class="col-sm-4">
    <input type="text" name="serviceFee" value="1111" id="serviceFee" placeholder="服务费"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label" >销售：</label>
  <div class="col-sm-4">
    <input type="text" name="salesman" value="222" id="salesman" placeholder="销售"
           class="form-control validate[required]"/>
  </div>
</div>

<div class="form-group">
  <label class="col-sm-2 control-label" >服务费已收：</label>
  <div class="col-sm-4">
    <input type="text" name="isCollected" value="1111" id="isCollected" placeholder="服务费"
           class="form-control validate[required,minSize[2]"/>
  </div>
  <label class="col-sm-2 control-label" >收款日期：</label>
  <div class="col-sm-4">
    <input type="text" name="collectDate" value="222" id="collectDate" placeholder="销售"
           class="form-control validate[required]"/>
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