<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function goToPage(pageNo){
	var url="${root}/base/BaseQuery/list";
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
	    	        	  doAction('${root}/base/BaseQuery/list');
	    	           }
	    		});
	      }
	    })
}
</script>
<form class="sui-form form-search" style="text-align:right;" id="searchForm">
  <input type="text" value="${searchValue}" id="searchValue" name="searchValue" class="input-medium search-query" style="width:200px;">
  <button type="button" class="sui-btn btn-primary" onclick="doSearch('${root}/base/BaseQuery/list')">查找</button>
  <button type="button" class="sui-btn btn-primary" onclick="doAction('${root}/base/BaseQuery/add')">新增</button>
</form>
<table class="sui-table table-bordered">
	<thead>
		<tr>
			<th>序号</th>
			<th>SQLKEY</th>
			<th>功能名称</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${dataList}" var="row" varStatus="row_status">
			<tr>
				<td style="width: 30px;">${(row_status.index+1)+((pageNo-1)*pageSize)}</td>
				<td style="width: 250px;"><c:if test="${row['QUERY_NAME'] !=null}">${row['QUERY_NAME']}</c:if>
				</td>
				<td><c:if test="${row['QUERY_TITLE'] !=null}">${row['QUERY_TITLE']}</c:if>
				</td>
				<td style="width: 100px;">
					<div class="sui-btn-group">
					    <button class="sui-btn btn-bordered btn-primary" onclick="doAction('${root}/base/BaseQuery/edit?id=${row['ID']}')">修改</button>
					    <button class="sui-btn btn-bordered btn-danger" onclick="doDel('${root}/base/BaseQuery/delete?id=${row['ID']}')">删除</button>
					  </div>
				</td>
			</tr>
		</c:forEach>
		<tr>
		</tr>
	</tbody>
</table>
${pageHtml}