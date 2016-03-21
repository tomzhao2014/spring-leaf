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
          <a href="/constant">公司管理</a>
        </li>
        <li class="active">公司列表</li>
      </ul><!-- /.breadcrumb -->

      <!-- #section:basics/content.searchbox -->
      <div class="nav-search" id="nav-search">
          <button class="btn btn-minier btn-primary" id="addConstant">添加新公司</button>
      </div><!-- /.nav-search -->

      <!-- /section:basics/content.searchbox -->
    </div>
    <div class="page-content">
      <div class="row">
          <!-- PAGE CONTENT BEGINS -->
          <div class="col-xs-12">
            <table id="simple-table" class="table table-striped table-bordered table-hover" width="100%">
              <thead>
              <tr>
                <th class="center">
                  <label class="pos-rel">
                    <input type="checkbox" class="ace">
                    <span class="lbl"></span>
                  </label>
                </th>
                <th>序号</th>
                <th>全称简称</th>
                <th class="hidden-480">代码</th>
                <th>类型</th>
                <th class="hidden-480">状态</th>
                <th>合同编号</th>
                <th>起始日期</th>
                <th>终止日期</th>
                <th >最多账号数</th>
                <th>联系人</th>
                <th>公司电话</th>
                <th>传真</th>
                <th>电子邮箱</th>
                <th>公司地址</th>
                <th>服务费</th>
                <th></th>
              </tr>
              </thead>

              <tbody>
              <c:forEach items="${page.result}" var="company">
              <tr>
                <td class="center">
                  <label class="pos-rel">
                    <input type="checkbox" class="ace">
                    <span class="lbl"></span>
                  </label>
                </td>

                <td>
                  <a href="#">${company.id}</a>
                </td>
                <td>${company.shortName}</td>
                <td class="hidden-480">${company.code}</td>
                <td>${company.type}</td>
                <td class="hidden-480">
                  ${company.state}
                <td>${company.contractNumber}</td>
                <td>${company.startDate}</td>
                <td>${company.availableDate}</td>
                <td>${company.maxAccounts}</td>
                <td>${company.contract}</td>
                <td>${company.phone}</td>
                <td>${company.fax}</td>
                <td>${company.email}</td>
                <td>${company.address}</td>
                <td>${company.serviceFee}</td>
                <td>
                  <div class="hidden-sm hidden-xs btn-group">


                    <button class="btn btn-xs btn-info j_edit" conId="${company.id}">
                      <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-danger j_del"  conId="${company.id}">
                      <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>

                  </div>

                  <div class="hidden-md hidden-lg">
                    <div class="inline pos-rel">
                      <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                        <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                      </button>

                      <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                        <li>
                          <a href="#" class="tooltip-info" data-rel="tooltip" title="" data-original-title="View">
																			<span class="blue">
																				<i class="ace-icon fa fa-search-plus bigger-120"></i>
																			</span>
                          </a>
                        </li>

                        <li>
                          <a href="#" class="tooltip-success" data-rel="tooltip" title="" data-original-title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                          </a>
                        </li>

                        <li>
                          <a href="#" class="tooltip-error" data-rel="tooltip" title="" data-original-title="Delete">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                          </a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </td>
              </tr>
              </c:forEach>
              </tbody>
            </table>

           <c:import url="../common/pager.jsp"></c:import>
          </div>
          <!-- PAGE CONTENT ENDS -->
      </div><!-- /.row -->
    </div><!-- /.page-content -->
  </div><!-- /.main-content -->
</div><!-- /.main-container -->
    <c:import url="../common/footer.jsp"></c:import>
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
    paging: false,
    "ordering": false,
    "searching": false,
    "scrollX": true

  });

  $(function () {
    $("#addConstant").click(function(){
      window.location.href ="/constant/add";
    });

    $(".j_edit").click(function(){
      var conId = $(this).attr("conId");
      window.location.href ="/constant/edit/"+conId;
    });

    $(".j_del").click(function(){
      var conId = $(this).attr("conId");
      window.location.href ="/constant/del/"+conId;
    });


  })
</script>
</body>
</html>