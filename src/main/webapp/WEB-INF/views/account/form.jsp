<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.qiktone.util.DateUtils" %>


<div class="form-group">
  <label class="col-sm-2 control-label" >云账号：</label>
  <div class="col-sm-4">
    <input type="text" name="username"  id="code"
           class="form-control validate[required,minSize[2]" />
  </div>
  <label class="col-sm-2 control-label" >状态：</label>
  <div class="col-sm-4">
    <select class="form-control" name="state">
      <option value="">请选择</option>
      <c:forEach var="statu" items="${status}">

        <option value="${statu.code}" selected="selected">${statu.descr}</option>
      </c:forEach>
    </select>
  </div>
</div>

<c:if test="">
  <div class="form-group">
    <label class="col-sm-2 control-label" >密码：</label>
    <div class="col-sm-4">
      <input type="password" name="password" placeholder="" class="col-xs-10 col-sm-5" value="">
    </div>

    <label class="col-sm-2 control-label" >确认密码：</label>
    <div class="col-sm-4">
      <input type="password" name="passwordCfg" placeholder="" class="col-xs-10 col-sm-5" value="">
    </div>
  </div>
</c:if>
<div class="form-group">
  <label class="col-sm-2 control-label" >应用类型：</label>
  <div class="col-sm-4">
    <select class="form-control" name="appType">
      <option value="">请选择</option>
      <c:forEach var="appType" items="${appTypes}">
        <option value="${appType.code}" >${appType.descr}</option>
      </c:forEach>
    </select>
  </div>

  <label class="col-sm-2 control-label" >类型：</label>
  <div class="col-sm-4">
    <select class="form-control" name="type">
      <option value="">请选择</option>
      <option value="company" >公司用户</option>
      <option value="user" >普通用户</option>
    </select>
  </div>
</div>


<div class="form-group">
  <label class="col-sm-2 control-label" >角色：</label>
  <div class="col-sm-4">
    <select class="form-control" name="role_id">
      <option value="">请选择</option>
      <c:forEach var="role" items="${roles}">
        <option value="${role.id}" >${role.name}</option>
      </c:forEach>
    </select>
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


<script>
  $(function(){
    $('#startDate,#availableDate,#collectDate').datepicker();
  });
</script>