<%--
  Created by IntelliJ IDEA.
  User: tom
  Date: 2016/3/11
  Time: 7:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar" class="sidebar                  responsive">
  <script type="text/javascript">
    try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
  </script>


  <ul class="nav nav-list">
    <c:choose>
      <c:when test="${module=='home'}">
      <li class="active">
      </c:when>
      <c:otherwise>
      <li class="">
      </c:otherwise>
    </c:choose>
      <a href="/home">
        <i class="menu-icon fa fa-tachometer"></i>
        <span class="menu-text"> 首页 </span>
      </a>
      <b class="arrow"></b>
    </li>
      <c:choose>
      <c:when test="${module=='company'}">
      <li class="active">
        </c:when>
        <c:otherwise>
      <li class="">
        </c:otherwise>
        </c:choose>
      <a href="/company">
        <i class="menu-icon fa fa-home"></i>
        <span class="menu-text"> 公司管理 </span>
      </a>

      <b class="arrow"></b>
    </li>

      <c:choose>
      <c:when test="${module=='role'}">
      <li class="active">
        </c:when>
        <c:otherwise>
      <li class="">
        </c:otherwise>
        </c:choose>
      <a href="/role">
        <i class="menu-icon fa fa-users"></i>
        <span class="menu-text"> 角色管理 </span>
      </a>

      <b class="arrow"></b>
    </li>

      <c:choose>
      <c:when test="${module=='account'}">
      <li class="active">
        </c:when>
        <c:otherwise>
      <li class="">
        </c:otherwise>
        </c:choose>
      <a href="/account">
        <i class="menu-icon fa fa-key"></i>
        <span class="menu-text"> 账号管理 </span>
      </a>

      <b class="arrow"></b>
    </li>

      <c:choose>
      <c:when test="${module=='department'}">
      <li class="active">
        </c:when>
        <c:otherwise>
      <li class="">
        </c:otherwise>
        </c:choose>
      <a href="/department">
        <i class="menu-icon fa fa-navicon"></i>
        <span class="menu-text"> 部门管理 </span>
      </a>

      <b class="arrow"></b>
    </li>

      <c:choose>
      <c:when test="${module=='employee'}">
      <li class="active">
        </c:when>
        <c:otherwise>
      <li class="">
        </c:otherwise>
        </c:choose>
      <a href="/employee">
        <i class="menu-icon fa fa-user"></i>
        <span class="menu-text"> 雇员管理 </span>
      </a>

      <b class="arrow"></b>
    </li>


      <c:choose>
      <c:when test="${module=='constant'}">
      <li class="active">
        </c:when>
        <c:otherwise>
      <li class="">
        </c:otherwise>
        </c:choose>
      <a href="/constant">
        <i class="menu-icon fa fa-users"></i>
        <span class="menu-text"> 系统常量管理 </span>
      </a>

      <b class="arrow"></b>
    </li>

  </ul><!-- /.nav-list -->

  <!-- #section:basics/sidebar.layout.minimize -->
  <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
    <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
  </div>

  <!-- /section:basics/sidebar.layout.minimize -->
  <script type="text/javascript">
    try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}

  </script>
</div>
