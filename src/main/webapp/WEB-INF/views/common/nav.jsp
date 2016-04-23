<%--
  Created by IntelliJ IDEA.
  User: tom
  Date: 2016/3/11
  Time: 7:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div id="navbar" class="navbar navbar-default">
  <script type="text/javascript">
    try{ace.settings.check('navbar' , 'fixed')}catch(e){}
  </script>

  <div class="navbar-container" id="navbar-container">
    <!-- #section:basics/sidebar.mobile.toggle -->
    <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
      <span class="sr-only">Toggle sidebar</span>

      <span class="icon-bar"></span>

      <span class="icon-bar"></span>

      <span class="icon-bar"></span>
    </button>

    <!-- /section:basics/sidebar.mobile.toggle -->
    <div class="navbar-header pull-left">
      <!-- #section:basics/navbar.layout.brand -->
      <a href="#" class="navbar-brand">
        <small>
          <i class="fa fa-leaf"></i>
          快景物流云管理平台
        </small>
      </a>


    </div>

    <!-- #section:basics/navbar.dropdown -->
    <div class="navbar-buttons navbar-header pull-right" role="navigation">
      <ul class="nav ace-nav">

        <!-- #section:basics/navbar.user_menu -->
        <li class="light-blue">
          <a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<span class="user-info">
									<small>欢迎你,</small>
									${sessionScope.user.username}
								</span>

          <%--  <i class="ace-icon fa fa-caret-down"></i>--%>
          </a>

          <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
            <li>
              <a href="#">
                <i class="ace-icon fa fa-cog"></i>
                设置
              </a>
            </li>

            <li class="divider"></li>

            <li>
              <a href="/logout">
                <i class="ace-icon fa fa-power-off"></i>
                退出
              </a>
            </li>
          </ul>
        </li>

        <!-- /section:basics/navbar.user_menu -->
      </ul>
    </div>

    <!-- /section:basics/navbar.dropdown -->
  </div><!-- /.navbar-container -->
</div>
