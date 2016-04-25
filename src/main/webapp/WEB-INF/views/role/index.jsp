<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
          <a href="/company">角色管理</a>
        </li>
        <li class="active">角色明细</li>
      </ul><!-- /.breadcrumb -->

      <!-- #section:basics/content.searchbox -->
      <div class="nav-search" id="nav-search">
          <button class="btn btn-minier btn-primary" id="addRole">新建</button>
        <button class="btn btn-minier btn-primary" id="department">删除</button>
      </div><!-- /.nav-search -->

      <!-- /section:basics/content.searchbox -->
    </div>
    <div class="page-content"><!-- PAGE CONTENT BEGINS -->
      <div class="row">

        <div class="col-xs-12 col-sm-10">
          <label for="changeCompany">公司选择:</label>
          <select class="chosen-select form-control" id="changeCompany" data-placeholder="Choose a State...">
            <option value=""> 请选择公司 </option>
            <c:forEach var="company" items="${companys}">
              <c:choose>
                <c:when test="${company!=null and company.id ==sessionScope.currentCompanyId}">
                  <option value="${company.id}" selected="selected">${company.shortName}</option>
                </c:when>
                <c:otherwise>
                  <option value="${company.id}">${company.shortName}</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
        </div>
        <!-- 角色列表开始-->
        <div class="col-xs-12 col-sm-3 widget-container-col ui-sortable">
        <div class="widget-box">
          <!-- #section:custom/widget-box.options -->
          <div class="widget-header">
            <h5 class="widget-title bigger lighter">
              角色列表
            </h5>
          </div>

          <!-- /section:custom/widget-box.options -->
          <div class="widget-body">
            <div class="widget-main no-padding">
              <table class="table table-striped table-bordered table-hover">
                <thead class="thin-border-bottom">
                <tr>
                  <th>类型 </th>
                  <th>名称</th>
                </tr>
                </thead>

                <tbody id="roles">
                <c:forEach items="${roles}" var="role">
                <tr rid="${role.id}">
                    <td>role.type</td>
                    <td>role.descr</td>
                </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
        <!-- 角色列表结束-->
        <!-- 模块列表开始-->
        <div class="col-xs-12 col-sm-3 widget-container-col ui-sortable">
          <div class="widget-box">
            <!-- #section:custom/widget-box.options -->
            <div class="widget-header">
              <h5 class="widget-title bigger lighter">
                模块列表
              </h5>
            </div>

            <!-- /section:custom/widget-box.options -->
            <div class="widget-body">
              <div class="widget-main no-padding">
                <table class="table table-striped table-bordered table-hover">
                  <thead class="thin-border-bottom">
                  <tr>
                    <th>名称</th>
                    <th>权限选择</th>
                  </tr>
                  </thead>

                  <tbody id="modules">
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <!-- 模块列表结束-->
        <!-- 功能列表开始-->
        <div class="col-xs-12 col-sm-3 widget-container-col ui-sortable">
          <div class="widget-box">
            <!-- #section:custom/widget-box.options -->
            <div class="widget-header">
              <h5 class="widget-title bigger lighter">
                功能列表
              </h5>
            </div>

            <!-- /section:custom/widget-box.options -->
            <div class="widget-body">
              <div class="widget-main no-padding">
                <table class="table table-striped table-bordered table-hover">
                  <thead class="thin-border-bottom">
                  <tr>
                    <th>
                      名称
                    </th>

                    <th>
                      权限选择
                    </th>
                  </tr>
                  </thead>

                  <tbody id="functions">
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <!-- 功能列表结束-->
        <!-- 操作列表开始-->
        <div class="col-xs-12 col-sm-3 widget-container-col ui-sortable">
          <div class="widget-box">
            <!-- #section:custom/widget-box.options -->
            <div class="widget-header">
              <h5 class="widget-title bigger lighter">
                操作列表
              </h5>
            </div>

            <!-- /section:custom/widget-box.options -->
            <div class="widget-body">
              <div class="widget-main no-padding">
                <table class="table table-striped table-bordered table-hover">
                  <thead class="thin-border-bottom">
                  <tr>
                    <th>
                      名称
                    </th>
                    <th>
                      权限选择
                    </th>
                  </tr>
                  </thead>

                  <tbody id="ops">
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <!-- 操作列表结束-->
        </div><!-- /.row -->
    </div><!-- /.page-content -->
  </div><!-- /.main-content -->
</div><!-- /.main-container -->
    <c:import url="../common/footer.jsp"></c:import>

  <div class="hidden">
    <table>
      <tr id="item">
        <td class="j_first"></td>
        <td class="j_second"><input type="checkbox"  class="j_check"></td>
      </tr>
    </table>
  </div>
<script>
  //And for the first simple table, which doesn't have TableTools or dataTables
  //select/deselect all rows according to table header checkbox
  var active_class = 'active';
  $('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
    var th_checked = this.checked;//checkbox inside "TH" table header

    $(this).closest('table').find('tbody > tr').each(function(){
      var row = this;
      if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
      else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
    });
  });

  //select/deselect a row when the checkbox is checked/unchecked
  $('#simple-table').on('click', 'td input[type=checkbox]' , function(){
    var $row = $(this).closest('tr');
    if(this.checked) $row.addClass(active_class);
    else $row.removeClass(active_class);
  });

  var myTable = $('#simple-table').dataTable({
    "bPaginate": false,
    "bInfo":false,
    "bFilter":false,
    "paging":false,
    "ordering":false,
    "searching":false,
    "sScrollY": "60%", "sScrollX": "2000px"

  });

  jQuery(function($) {
    $('#id-disable-check').on('click', function() {
      var inp = $('#form-input-readonly').get(0);
      if(inp.hasAttribute('disabled')) {
        inp.setAttribute('readonly' , 'true');
        inp.removeAttribute('disabled');
        inp.value="This text field is readonly!";
      }
      else {
        inp.setAttribute('disabled' , 'disabled');
        inp.removeAttribute('readonly');
        inp.value="This text field is disabled!";
      }
    });


    if(!ace.vars['touch']) {
      $('.chosen-select').chosen({allow_single_deselect:true});
      //resize the chosen on window resize

      $(window)
              .off('resize.chosen')
              .on('resize.chosen', function() {
                $('.chosen-select').each(function() {
                  var $this = $(this);
                  $this.next().css({'width': $this.parent().width()});
                })
              }).trigger('resize.chosen');
      //resize chosen on sidebar collapse/expand
      $(document).on('settings.ace.chosen', function(e, event_name, event_val) {
        if(event_name != 'sidebar_collapsed') return;
        $('.chosen-select').each(function() {
          var $this = $(this);
          $this.next().css({'width': $this.parent().width()});
        })
      });

    }

  });

  $(function(){
    var companyId = "${sessionScope.currentCompanyId}";



    $("#changeCompany").on("change",function(){
      var cid = $(this).val()?$(this).val():companyId;
      var type = {"company":"公司管理员","user":"一般用户"}
      $.ajax({
        type: "POST",
        url: "/role/list/"+cid,
        success: function(data){
          $("#roles").html("");
          console.log(data);
          $(data).each(function(){
            var role = this;

            var item = $("#item").clone();
            item.find(".j_first").html(type[role.type]);
            item.find(".j_second").html(role.name);
            item.attr("rid",role.id);
            $("#roles").append(item);
          });
          $("#roles tr:eq(0)").
        }
      });
    });


    //默认打开
    $("#roles").on("click","tr:eq(0)",function(){
        var rid = $(this).attr("rid");
      $.ajax({
        type: "GET",
        url: "/privilege/modules/"+rid,
        success: function(rs){
          $("#modules").html("");
          var  modules = rs['modules'];

          $(modules).each(function(k1,module){
            var module = this;
            var item = $("#item").clone();
            item.find(".j_first").html(module.descr);

            if(module.hasAuthority){
              item.find(".j_check")[0].checked=true;
            }else{
              item.find(".j_check")[0].checked=false;
            }
            item.attr("mname",module.name);
            $("#module").append(item);

          });
        }
      });

    });

    //




    $("#addCompany").click(function(){
      window.location.href ="/company/add";
    });

    $(".j_edit").click(function(){
      var conId = $(this).attr("conId");
      window.location.href ="/company/edit/"+conId;
    });
  });


</script>
</body>
</html>