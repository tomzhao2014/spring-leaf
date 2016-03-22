<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<div class="form-group">
  <label class="col-sm-2 control-label" for="form-field-1">名称</label>
  <div class="col-sm-4">
    <input type="text" name="name" value="${catalog?.name}" id="form-field-1" placeholder="名称"
           class="form-control validate[required,minSize[2]${catalog ? '' : ',ajax[ajaxCatalog]'}]"/>
  </div>
  <label class="col-sm-2 control-label" for="form-field-2">别名</label>
  <div class="col-sm-4">
    <input type="text" name="aliasName" value="${catalog?.aliasName}" id="form-field-2" placeholder="别名"
           class="form-control validate[required]"/>
  </div>
</div>

  <div class="form-group">
    <label class="col-sm-2 control-label no-padding-right">描述</label>

    <div class="col-sm-4">
      <input type="text" name="descr" placeholder="" class="form-control " value="${constant!=null?constant.descr:""}">
    </div>

    <label class="col-sm-2 control-label no-padding-right">类型</label>

    <div class="col-sm-4">
      <input type="text" name="type" placeholder="" class="form-control" value="${constant!=null?constant.type:""}">
    </div>
  </div>

  <div class="form-group">
    <label class="col-sm-3 control-label no-padding-right">类型</label>

    <div class="col-sm-9">
      <input type="text" name="type" placeholder="" class="col-xs-10 col-sm-5" value="${constant!=null?constant.type:""}">
    </div>
  </div>

  <div class="form-group">
    <label class="col-sm-3 control-label no-padding-right">代码</label>

    <div class="col-sm-9">
      <input type="text" name="code" placeholder="" class="col-xs-10 col-sm-5" value="${constant!=null?constant.code:""}">
    </div>
  </div>

  <div class="form-group">
    <label class="col-sm-3 control-label no-padding-right">排序</label>

    <div class="col-sm-9">
      <input type="text" name="seq" placeholder="" class="col-xs-10 col-sm-5" value="${constant!=null?constant.seq:""}">
    </div>
  </div>

  <div class="form-group">
    <label class="col-sm-3 control-label no-padding-right">所属公司</label>

    <div class="col-sm-9">
      <input type="text" name="companyId" placeholder="" class="col-xs-10 col-sm-5" value="${constant!=null?(constant.company==null?"":constant.company.name):""}">
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