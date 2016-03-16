<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="row">
  <div class="col-xs-6">
    <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">第${page.pageNo}页 共${page.totalPages}页 总共${page.totalCount}条</div>
  </div>
  <div class="col-xs-6">
    <div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
      <ul class="pagination">
        <li class="paginate_button previous" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_first" curpage="1"><a href="/constant?pageNo=1">首页</a></li>
        <li class="paginate_button previous ${page.preCss}" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous"><a href="/constant?pageNo=${page.prePage}">上一页</a></li>
        <li class="paginate_button next ${page.nextCss}" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_next" ><a href="/constant?pageNo=${page.nextPage}">下一页</a></li>
        <li class="paginate_button next" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_last"><a href="/constant?pageNo=${page.totalPages}">尾页</a></li>
      </ul>
    </div>
  </div>
</div>
