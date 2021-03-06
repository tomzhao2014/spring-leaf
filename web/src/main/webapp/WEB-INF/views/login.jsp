﻿<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta charset="utf-8" />
  <title>快景物流云平台 - 后台管理系统</title>

  <meta name="description" content="User login page" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

  <!-- bootstrap & fontawesome -->
  <link rel="stylesheet" href="/css/bootstrap.min.css" />
  <link rel="stylesheet" href="/css/font-awesome.min.css" />

  <!-- text fonts -->
  <link rel="stylesheet" href="/css/ace-fonts.min.css" />

  <!-- ace styles -->
  <link rel="stylesheet" href="/css/ace.min.css" />

  <!--[if lte IE 9]>
  <link rel="stylesheet" href="/css/ace-part2.min.css" />
  <![endif]-->
  <link rel="stylesheet" href="/css/ace-rtl.min.css" />

  <!--[if lte IE 9]>
  <link rel="stylesheet" href="/css/ace-ie.min.css" />
  <![endif]-->

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

  <!--[if lt IE 9]>
  <script src="/js/html5shiv.min.js"></script>
  <script src="/js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="login-layout blur-login">
<div class="main-container">
  <div class="main-content">
    <div class="row">
      <div class="col-sm-10 col-sm-offset-1">
        <div class="login-container">
          <div class="center">
            <h1>
              <i class="ace-icon fa fa-leaf green"></i>
              <span class="red">qiktone</span>
              <span class="white" id="id-text2">后台管理系统</span>
            </h1>
            <h4 class="blue" id="id-company-text">&copy; 快景物流云平台</h4>
          </div>

          <div class="space-6"></div>

          <div class="position-relative">
            <div id="login-box" class="login-box visible widget-box no-border">
              <div class="widget-body">
                <div class="widget-main">
                  <h4 class="header blue lighter bigger">
                    <i class="ace-icon fa fa-coffee green"></i>
                    请输入您的账号信息
                  </h4>

                  <div class="space-6"></div>

                  <form action="login" method="post">
                    <fieldset>
                      <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="账号" name="username"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                      </label>

                      <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" name="password"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                      </label>

                      <div class="space"></div>

                      <div class="clearfix">
                        <label class="inline">
                          <input type="checkbox" class="ace" />
                          <span class="lbl"> 记住我</span>
                        </label>

                        <button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                          <i class="ace-icon fa fa-key"></i>
                          <span class="bigger-110">登录</span>
                        </button>
                      </div>

                      <div class="space-4"></div>
                    </fieldset>
                  </form>

                </div><!-- /.widget-main -->
              </div><!-- /.widget-body -->
            </div><!-- /.login-box -->

          </div><!-- /.position-relative -->

        </div>
      </div><!-- /.col -->
    </div><!-- /.row -->
  </div><!-- /.main-content -->
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
  window.jQuery || document.write("<script src='/js/jquery.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
  window.jQuery || document.write("<script src='/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
  if('ontouchstart' in document.documentElement) document.write("<script src='/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
  jQuery(function($) {
    $(document).on('click', '.toolbar a[data-target]', function(e) {
      e.preventDefault();
      var target = $(this).data('target');
      $('.widget-box.visible').removeClass('visible');//hide others
      $(target).addClass('visible');//show target
    });
  });


  jQuery(function($) {


  });
</script>
</body>
</html>
