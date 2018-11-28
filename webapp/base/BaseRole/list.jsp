<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function goToPage(pageNo){
	var url="${root}/base/BaseRole/list";
	$("#loading").show();
	$("#content").hide();
	var searchValue=$("#searchValue").val();
	$.ajax({
		  type: 'POST',
		  url: url,
		  data:{pageNo:pageNo,searchValue:searchValue},
		  cache:false,
          success:function(rtHtml){
        	  $("#loading").hide();
        	  $("#content").html(rtHtml);
        	  $("#content").show();
           }
	});
}

function doDel(url){
	 $.confirm({
		  title:'删除确认',
	      body: '确定要删除数据吗？',
	      transition:false,
	      width:300,
	      okHide:   function(e){
	    		$("#loading").show();
	    		$("#content").hide();
	    		$.ajax({
	    			  type: 'POST',
	    			  url: url,
	    			  cache:false,
	    	          success:function(msg){
	    	        	  doAction('${root}/base/BaseRole/list');
	    	           }
	    		});
	      }
	    })
}

</script>
<form class="sui-form form-search" style="text-align:right;" id="searchForm">
  <input type="text" value="${searchValue}" id="searchValue" name="searchValue" class="input-medium search-query" style="width:200px;">
  <button type="button" class="sui-btn btn-primary" onclick="doSearch('${root}/base/BaseRole/list')">查找</button>
  <button type="button" class="sui-btn btn-primary" onclick="doAction('${root}/base/BaseRole/add')">新增</button>
</form>
<table class="sui-table table-bordered">
	<thead>
		<tr>
			<th>序号</th>
			<th>角色名称</th>
			<th>角色代码</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${dataList}" var="row" varStatus="row_status">
			<tr>
				<td style="width: 30px;">${(row_status.index+1)+((pageNo-1)*pageSize)}</td>
				<td style="width: 250px;"><c:if test="${row['ROLE_NAME'] !=null}">${row['ROLE_NAME']}</c:if>
				</td>
				<td><c:if test="${row['ROLE_CODE'] !=null}">${row['ROLE_CODE']}</c:if>
				</td>
				<td style="width: 100px;">
					<div class="sui-btn-group">
					    <button class="sui-btn btn-bordered btn-primary" onclick="doAction('${root}/base/BaseRole/edit?id=${row['ROLE_ID']}')">修改</button>
					    <button class="sui-btn btn-bordered btn-danger" onclick="doDel('${root}/base/BaseRole/delete?id=${row['ROLE_ID']}')">删除</button>
					    <a class="sui-btn btn-primary" data-toggle="modal" data-remote="${root}/base/BaseRole/edit?id=${row['ROLE_ID']}" data-target="#myModal">click me</a>
					    <button class="sui-btn btn-bordered btn-primary"  onclick="openUrlList('${row.id}')">分配权限</button>
					  </div>
				</td>
			</tr>
		</c:forEach>
		<tr>
		</tr>
	</tbody>
</table>
${pageHtml}
<!-- Modal-->
<div id="myModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" data-dismiss="modal" aria-hidden="true" class="sui-close">×</button>
        <h4 id="myModalLabel" class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">我是内容body</div>
      <div class="modal-footer">
        <button type="button" data-ok="modal" class="sui-btn btn-primary btn-large">可自定ok</button>
        <button type="button" data-dismiss="modal" class="sui-btn btn-default btn-large">可自定dismiss</button>
      </div>
    </div>
  </div>
</div>