<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="widget-body">
  <div class="widget-main">
    <form class="form-inline sort" action="/${module}" method="get">
      公司名称：<input  type="text" name="name" class="input-small">
      联系人：<input  type="text" name="contact" class="input-small">
      公司类型：<select class="form-control" name="type">
      <option value=""></option>
      <c:forEach var="companyType" items="${companyTypes}">
      <option value="${companyType.code}">${companyType.descr}</option>
      </c:forEach>


    </select>
      开始日期从：<input class="form-control date-picker" name="startDate" id="data1"  type="text" data-date-format="yyyy-mm-dd">
      到：<input class="form-control date-picker" name="startDateEnd" type="text" id="data2" data-date-format="yyyy-mm-dd">
      <br/> <br/>
      有效日期从：<input class="form-control date-picker" name="availableDate" id="data3"  type="text" data-date-format="yyyy-mm-dd">
      到：<input class="form-control date-picker" id="data4" name="availableDateEnd"  type="text" data-date-format="yyyy-mm-dd">
      <button type="submit" class="btn btn-info btn-sm pull-right">
        <i class="icon-search bigger-110"></i>
        条件查询
      </button>
    </form>
  </div>
</div>

<script>
  $(function(){
    $("#data1,#data2,#data3,#data4").datepicker();
  });
</script>