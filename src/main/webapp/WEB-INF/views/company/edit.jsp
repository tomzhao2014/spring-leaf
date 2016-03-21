<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<c:import url="../common/header.jsp"></c:import>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<c:import url="../common/nav.jsp"></c:import>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
  <!-- #section:basics/sidebar -->
  <c:import url="../common/left.jsp"></c:import>

  <!-- /section:basics/sidebar -->
  <div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
      <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
      </script>

      <ul class="breadcrumb">
        <li>
          <i class="ace-icon fa fa-home home-icon"></i>
          <a href="/">首页</a>
        </li>

        <li>
          <a href="/constant">系统常量管理</a>
        </li>
        <li class="active">添加系统常量</li>
      </ul><!-- /.breadcrumb -->


      <!-- /section:basics/content.searchbox -->
    </div>
    <div class="page-content">
      <div class="page-header">
        <h1>编辑系统常量信息</h1>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <form class="form-horizontal" role="form" action="/constant" method="post">
            <input type="hidden" name="_method" value="put">
         <c:import url="form.jsp"></c:import>
            </form>
        </div>
      </div>
    </div><!-- /.page-content -->
  </div><!-- /.main-content -->
</div><!-- /.main-container -->
    <c:import url="../common/footer.jsp"></c:import>
<script>


</script>
</body>
</html>