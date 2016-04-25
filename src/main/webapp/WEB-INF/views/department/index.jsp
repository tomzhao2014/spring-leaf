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
          <a href="/department">部门管理</a>
        </li>
        <li class="active">部门列表</li>
      </ul><!-- /.breadcrumb -->

      <!-- #section:basics/content.searchbox -->
      <div class="nav-search" id="nav-search">
          <button class="btn btn-minier btn-primary" id="addDepartment">新建</button>
      </div><!-- /.nav-search -->

      <!-- /section:basics/content.searchbox -->
    </div>
    <div class="page-content"><!-- PAGE CONTENT BEGINS -->
      <div class="row">
          <div class="col-xs-12 col-sm-10">
            <label >公司选择:</label>
            <select class="chosen-select form-control" id="changeCompany" data-placeholder="" >
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
        <div class="col-xs-12">
          <table id="simple-table" class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
              <th>序号</th>
              <th>部门全称</th>
              <th>部门简称</th>
              <th>部门代码</th>
              <th>部门类型</th>
              <th>上级部门</th>
              <th>临时网点</th>
              <th>负责人</th>
              <th>联系电话</th>
              <th>手机</th>
              <th>传真</th>
              <th>开户银行</th>
              <th>银行帐号</th>
              <th>持卡人</th>
              <th>地址</th>
              <th>重量单位</th>

            </tr>
            </thead>

            <tbody id="accountData">
            <c:forEach items="${departments}" var="department">
              <tr>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td >${department.id}</td>
                <td>
                  <div class="hidden-sm hidden-xs btn-group">
                    <button class="btn btn-xs btn-info j_edit" conId="${constant.id}">
                      <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>
                  </div>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>

        </div>
        </div><!-- /.row -->
    </div><!-- /.page-content -->
  </div><!-- /.main-content -->
</div><!-- /.main-container -->
    <c:import url="../common/footer.jsp"></c:import>



<div class="hidden"  >
  <table>
  <tr id="accountItem">
    <td class="center j_id"></td>
    <td class="j_username"></td>
    <td class="j_status"></td>
    <td class="j_appType"></td>
    <td clsss="j_type"></td>
    <td clsss="j_role"></td>
    <td>
      <div class="hidden-sm hidden-xs btn-group">
        <button class="btn btn-xs btn-info j_edit" >
          <i class="ace-icon fa fa-pencil bigger-120"></i>
        </button>
      </div>
    </td>
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

  $(function () {

    var companyId = "${sessionScope.currentCompanyId}";

    $("#addRole").click(function(){
      var cid = $("#changeCompany").val();
      if(cid){
        document.location.href="/account/add";
      }else{
        notify("新建帐号失败","请先选择公司","error");
      }

    });

    $("#accountData").on("click",".j_edit",function(){
      var cid = $("#changeCompany").val();
      if(cid){
        var aid = $(this).attr("aid");
        document.location.href="/account/edit/"+aid;
      }else{
        notify("编辑帐号失败","请先选择公司","error");
      }
    });

    $("#changeCompany").on("change",function(){
      var cid = $(this).val()?$(this).val():companyId;
      $("#companyId").val(cid);
      $.ajax({
        type: "POST",
        url: "/account/list/"+cid,
        success: function(data){
          $("#accountData").html("");
            $(data).each(function(){
              var account = this;

            var item = $("#accountItem").clone();


              item.find(".j_id").html(account.id);
              item.find(".j_username").html(account.username);
              item.find(".j_status").html(account.astate.descr);
              item.find(".j_appType").html(account.appType);
              item.find(".j_type").html(account.type);
              item.find(".j_role").html(account.role);
              item.find(".j_edit").attr("aid",account.id);
              item.find(".j_del").attr("aid",account.id);


              $("#accountData").append(item);

            });
        }
      });
    });

    if(companyId){
      $("#changeCompany").trigger("change");
    }
    $(".j_save").on("click",function(){
        var data = $("#accountForm").serialize();
      $.ajax({
        type: "POST",
        url: "/account",
        data:data,
        success: function(account){

            if(account){
              notify("添加账号成功！","添加账号成功","success");
            }else{
              notify("添加账号失败！","添加账号失败","error");
            }

        }
      });

    });


  });

  function notify(title,message,type,image){
    $.gritter.add({
      title:title,
      text: message,
      image:image, //in Ace demo ../assets will be replaced by correct assets path
      sticky: false,
      before_open: function(){
        if($('.gritter-item-wrapper').length >= 1)
        {
          return false;
        }
      },
      position: 'bottom-right',
      class_name: 'gritter-'+type
    });
  }





</script>
</body>
</html>