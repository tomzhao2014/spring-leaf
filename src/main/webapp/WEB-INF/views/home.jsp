<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<c:import url="common/header.jsp"></c:import>
<body class="no-skin">
<!-- #section:basics/navbar.layout -->

  <c:import url="common/nav.jsp"></c:import>
  <!-- /section:basics/navbar.layout -->
  <div class="main-container" id="main-container">
  <!-- #section:basics/sidebar -->

    <c:import url="common/left.jsp"></c:import>
  <!-- /section:basics/sidebar -->
  <div class="main-content">
  <div class="page-content">
  <div class="row">
    <div class="col-xs-12">
      <!-- PAGE CONTENT BEGINS -->
      <h4 class="lighter">
        <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
        <a href="#modal-wizard" data-toggle="modal" class="pink"> 使用向导 </a>
      </h4>

      <div class="hr hr-18 hr-double dotted"></div>

      <div class="widget-box">
        <div class="widget-header widget-header-blue widget-header-flat">
          <h4 class="widget-title lighter">步骤</h4>

        </div>

        <div class="widget-body">
          <div class="widget-main">
            <!-- #section:plugins/fuelux.wizard -->
            <div id="fuelux-wizard-container" class="no-steps-container">
              <div>
                <!-- #section:plugins/fuelux.wizard.steps -->
                <ul class="steps">
                  <li data-step="1" class="active">
                    <span class="step">1</span>
                    <span class="title">添加公司</span>
                  </li>

                  <li data-step="2" class="">
                    <span class="step">2</span>
                    <span class="title">新建角色</span>
                  </li>

                  <li data-step="3" class="">
                    <span class="step">3</span>
                    <span class="title">开设账号</span>
                  </li>

                  <li data-step="4" class="">
                    <span class="step">4</span>
                    <span class="title">添加部门</span>
                  </li>

                  <li data-step="5" class="">
                    <span class="step">5</span>
                    <span class="title">关联雇员</span>
                  </li>
                </ul>

                <!-- /section:plugins/fuelux.wizard.steps -->
              </div>

              <hr>

              <!-- #section:plugins/fuelux.wizard.container -->
              <div class="step-content pos-rel">
                <div class="step-pane" data-step="1">
                  <div class="center">
                    <h3 class="blue lighter">添加新公司</h3>
                  </div>
                </div>

                <div class="step-pane" data-step="2">
                  <div class="center">
                    <h3 class="blue lighter">为新公司的人员建立对应的角色，比如总经理</h3>
                  </div>
                </div>

                <div class="step-pane" data-step="3">
                  <div class="center">
                    <h3 class="blue lighter">为新公司增加账号</h3>
                  </div>
                </div>

                <div class="step-pane" data-step="4">
                  <div class="center">
                    <h3 class="blue lighter">建立新公司的部门</h3>
                  </div>
                </div>

                <div class="step-pane" data-step="5">
                  <div class="center">
                    <h3 class="green lighter">新建员工，将员工与账号绑定</h3>
                  </div>
                </div>
              </div>

              <!-- /section:plugins/fuelux.wizard.container -->
            </div>

            <hr>
            <div class="wizard-actions">
              <!-- #section:plugins/fuelux.wizard.buttons -->
              <button class="btn btn-prev" disabled="disabled">
                <i class="ace-icon fa fa-arrow-left"></i>
                上一步
              </button>

              <button class="btn btn-success btn-next" data-last="Finish">
                下一步

                <i class="ace-icon fa fa-arrow-right icon-on-right"></i></button>

              <!-- /section:plugins/fuelux.wizard.buttons -->
            </div>

            <!-- /section:plugins/fuelux.wizard -->
          </div><!-- /.widget-main -->
        </div><!-- /.widget-body -->
      </div>

      <div id="modal-wizard" class="modal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div id="modal-wizard-container" class="no-steps-container">
              <div class="modal-header">
                <ul class="steps">
                  <li data-step="1" class="active">
                    <span class="step">1</span>
                    <span class="title">Validation states</span>
                  </li>

                  <li data-step="2">
                    <span class="step">2</span>
                    <span class="title">Alerts</span>
                  </li>

                  <li data-step="3">
                    <span class="step">3</span>
                    <span class="title">Payment Info</span>
                  </li>

                  <li data-step="4">
                    <span class="step">4</span>
                    <span class="title">Other Info</span>
                  </li>
                </ul>
              </div>

              <div class="modal-body step-content">
                <div class="step-pane active" data-step="1">
                  <div class="center">
                    <h4 class="blue">Step 1</h4>
                  </div>
                </div>

                <div class="step-pane" data-step="2">
                  <div class="center">
                    <h4 class="blue">Step 2</h4>
                  </div>
                </div>

                <div class="step-pane" data-step="3">
                  <div class="center">
                    <h4 class="blue">Step 3</h4>
                  </div>
                </div>

                <div class="step-pane" data-step="4">
                  <div class="center">
                    <h4 class="blue">Step 4</h4>
                  </div>
                </div>
              </div>
            </div>

            <div class="modal-footer wizard-actions">
              <button class="btn btn-sm btn-prev" disabled="disabled">
                <i class="ace-icon fa fa-arrow-left"></i>
                Prev
              </button>

              <button class="btn btn-success btn-sm btn-next" data-last="Finish">
                Next
                <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
              </button>

              <button class="btn btn-danger btn-sm pull-left" data-dismiss="modal">
                <i class="ace-icon fa fa-times"></i>
                Cancel
              </button>
            </div>
          </div>
        </div>
      </div><!-- PAGE CONTENT ENDS -->
    </div>
  </div><!-- /.row -->
  </div><!-- /.page-content -->
  </div><!-- /.main-content -->
  </div><!-- /.main-container -->

<c:import url="common/footer.jsp"></c:import>


<script>
  jQuery(function($) {

    $('[data-rel=tooltip]').tooltip();

/*    $(".select2").css('width','200px').select2({allowClear:true})
            .on('change', function(){
              $(this).closest('form').validate().element($(this));
            });*/


    var $validation = false;
    $('#fuelux-wizard-container')
            .ace_wizard({
              //step: 2 //optional argument. wizard will jump to step "2" at first
              //buttons: '.wizard-actions:eq(0)'
            })
            .on('actionclicked.fu.wizard' , function(e, info){
              if(info.step == 1 && $validation) {
                if(!$('#validation-form').valid()) e.preventDefault();
              }
            })
            .on('finished.fu.wizard', function(e) {
              bootbox.dialog({
                message: "Thank you! Your information was successfully saved!",
                buttons: {
                  "success" : {
                    "label" : "OK",
                    "className" : "btn-sm btn-primary"
                  }
                }
              });
            }).on('stepclick.fu.wizard', function(e){
              //e.preventDefault();//this will prevent clicking and selecting steps
            });


    //jump to a step
    /**
     var wizard = $('#fuelux-wizard-container').data('fu.wizard')
     wizard.currentStep = 3;
     wizard.setState();
     */

      //determine selected step
      //wizard.selectedItem().step



      //hide or show the other form which requires validation
      //this is for demo only, you usullay want just one form in your application
    $('#skip-validation').removeAttr('checked').on('click', function(){
      $validation = this.checked;
      if(this.checked) {
        $('#sample-form').hide();
        $('#validation-form').removeClass('hide');
      }
      else {
        $('#validation-form').addClass('hide');
        $('#sample-form').show();
      }
    })



    //documentation : http://docs.jquery.com/Plugins/Validation/validate


    $.mask.definitions['~']='[+-]';
    $('#phone').mask('(999) 999-9999');

    jQuery.validator.addMethod("phone", function (value, element) {
      return this.optional(element) || /^\(\d{3}\) \d{3}\-\d{4}( x\d{1,6})?$/.test(value);
    }, "Enter a valid phone number.");

    $('#validation-form').validate({
      errorElement: 'div',
      errorClass: 'help-block',
      focusInvalid: false,
      ignore: "",
      rules: {
        email: {
          required: true,
          email:true
        },
        password: {
          required: true,
          minlength: 5
        },
        password2: {
          required: true,
          minlength: 5,
          equalTo: "#password"
        },
        name: {
          required: true
        },
        phone: {
          required: true,
          phone: 'required'
        },
        url: {
          required: true,
          url: true
        },
        comment: {
          required: true
        },
        state: {
          required: true
        },
        platform: {
          required: true
        },
        subscription: {
          required: true
        },
        gender: {
          required: true
        },
        agree: {
          required: true
        }
      },

      messages: {
        email: {
          required: "Please provide a valid email.",
          email: "Please provide a valid email."
        },
        password: {
          required: "Please specify a password.",
          minlength: "Please specify a secure password."
        },
        state: "Please choose state",
        subscription: "Please choose at least one option",
        gender: "Please choose gender",
        agree: "Please accept our policy"
      },


      highlight: function (e) {
        $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
      },

      success: function (e) {
        $(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
        $(e).remove();
      },

      errorPlacement: function (error, element) {
        if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
          var controls = element.closest('div[class*="col-"]');
          if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
          else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
        }
        else if(element.is('.select2')) {
          error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
        }
        else if(element.is('.chosen-select')) {
          error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
        }
        else error.insertAfter(element.parent());
      },

      submitHandler: function (form) {
      },
      invalidHandler: function (form) {
      }
    });




    $('#modal-wizard-container').ace_wizard();
    $('#modal-wizard .wizard-actions .btn[data-dismiss=modal]').removeAttr('disabled');


    /**
     $('#date').datepicker({autoclose:true}).on('changeDate', function(ev) {
					$(this).closest('form').validate().element($(this));
				});

     $('#mychosen').chosen().on('change', function(ev) {
					$(this).closest('form').validate().element($(this));
				});
     */


    $(document).one('ajaxloadstart.page', function(e) {
      //in ajax mode, remove remaining elements before leaving page
      $('[class*=select2]').remove();
    });
  })


</script>

  </body>
  </html>
