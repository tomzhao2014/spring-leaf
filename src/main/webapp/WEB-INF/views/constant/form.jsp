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

      <!-- #section:basics/content.searchbox -->
      <div class="nav-search" id="nav-search">
        <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off">
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
          <button class="btn btn-xs btn-info">
            <a href="/constant/add"><i class="ace-icon fa fa-plus bigger-100"></i></a>
          </button>
        </form>
      </div><!-- /.nav-search -->

      <!-- /section:basics/content.searchbox -->
    </div>
    <div class="page-content">
      <div class="row">
        <div class="col-xs-12">
          <form class="form-horizontal" role="form">
            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right">描述</label>

              <div class="col-sm-9">
                <input type="text" name="descr" placeholder="" class="col-xs-10 col-sm-5">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right">类型</label>

              <div class="col-sm-9">
                <input type="text" name="type" placeholder="" class="col-xs-10 col-sm-5">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right">代码</label>

              <div class="col-sm-9">
                <input type="text" name="code" placeholder="" class="col-xs-10 col-sm-5">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right">排序</label>

              <div class="col-sm-9">
                <input type="text" name="seq" placeholder="" class="col-xs-10 col-sm-5">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right">所属公司</label>

              <div class="col-sm-9">
                <input type="text" name="companyId" placeholder="" class="col-xs-10 col-sm-5">
              </div>
            </div>
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