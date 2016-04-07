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

     ddd
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
    "bPaginate": false,
    "bInfo":false,
    "bFilter":false,
    "paging":false,
    "ordering":false,
    "searching":false,
    "sScrollY": "60%", "sScrollX": "2000px"

  });

  $(function () {
    $("#addCompany").click(function(){
      window.location.href ="/company/add";
    });

    $(".j_edit").click(function(){
      var conId = $(this).attr("conId");
      window.location.href ="/company/edit/"+conId;
    });

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





</script>
</body>
</html>