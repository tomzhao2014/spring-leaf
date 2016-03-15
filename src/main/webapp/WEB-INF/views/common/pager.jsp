
<div class="row">
  <div class="col-xs-6">
    <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">第${p.pageNo}页 共${p.totalPages}页 总共${p.totalCount}条</div>
  </div>
  <div class="col-xs-6">
    <div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
      <ul class="pagination">
        <li class="paginate_button previous" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_first" curpage="1"><a href="#">首页</a></li>
        <li class="paginate_button previous <%=p.preCss%>" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous"><a href="#">上一页</a></li>
        <li class="paginate_button next <%=p.nextCss%>" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_next"><a href="#">下一页</a></li>
        <li class="paginate_button next" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_last" curpage="<%=p.totalPages%>"><a href="#">尾页</a></li>
      </ul>
    </div>
  </div>

</div>