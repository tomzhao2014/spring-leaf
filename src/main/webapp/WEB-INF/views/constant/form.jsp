
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
  <div class="form-group">
    <label class="col-sm-3 control-label no-padding-right">描述</label>

    <div class="col-sm-9">
      <input type="text" name="descr" placeholder="" class="col-xs-10 col-sm-5" value="${constant?constant.descr:""}">
    </div>
  </div>

  <div class="form-group">
    <label class="col-sm-3 control-label no-padding-right">类型</label>

    <div class="col-sm-9">
      <input type="text" name="type" placeholder="" class="col-xs-10 col-sm-5" value="${constant?constant.type:""}">
    </div>
  </div>

  <div class="form-group">
    <label class="col-sm-3 control-label no-padding-right">代码</label>

    <div class="col-sm-9">
      <input type="text" name="code" placeholder="" class="col-xs-10 col-sm-5" value="${constant?constant.code:""}">
    </div>
  </div>

  <div class="form-group">
    <label class="col-sm-3 control-label no-padding-right">排序</label>

    <div class="col-sm-9">
      <input type="text" name="seq" placeholder="" class="col-xs-10 col-sm-5" value="${constant?constant.seq:""}">
    </div>
  </div>

  <div class="form-group">
    <label class="col-sm-3 control-label no-padding-right">所属公司</label>

    <div class="col-sm-9">
      <input type="text" name="companyId" placeholder="" class="col-xs-10 col-sm-5" value="${constant?constant.company.name:""}">
    </div>
  </div>

  <div class="clearfix form-actions">
    <div class="col-md-offset-3 col-md-9">
      <button class="btn btn-info" type="submit">
        <i class="ace-icon fa fa-check bigger-110"></i>
        保存
      </button>


      <button class="btn" type="reset">
        <i class="ace-icon fa fa-undo bigger-110"></i>
        重置
      </button>
    </div>
  </div>
</form>