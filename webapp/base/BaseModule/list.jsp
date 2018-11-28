<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function goToPage(pageNo){
	var url="${root}/base/BaseModule/list";
	$("#loading").show();
	$("#content").hide();
	var searchValue=$("#searchValue").val();
	$.ajax({
		  type: 'POST',
		  url: url,
		  data:{pageNo:pageNo,searchValue:searchValue,queryParentCode:"${queryParentCode}"},
		  cache:false,
          success:function(rtHtml){
        	  $("#loading").hide();
        	  $("#treeContent").html(rtHtml);
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
	    	        	  goToPage(1);
	    	           }
	    		});
	      }
	    })
}
</script>
<form class="sui-form form-search" style="text-align:right;" id="searchForm">
  <input type="text" value="${searchValue}" id="searchValue" name="searchValue" class="input-medium search-query" style="width:200px;">
  <button type="button" class="sui-btn btn-primary" onclick="doSearch('${root}/base/BaseModule/list')">查找</button>
  <button type="button" class="sui-btn btn-primary" onclick="doAjax('${root}/base/BaseModule/add?queryParentCode=${queryParentCode}','treeContent')">新增</button>
</form>
<table class="sui-table table-bordered">
	<thead>
		<tr>
			<th>序号</th>
			<th>名称</th>
			<th>节点类型</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${dataList}" var="row" varStatus="row_status">
			<tr>
				<td style="width: 30px;">${(row_status.index+1)+((pageNo-1)*pageSize)}</td>
				<td style="width: 250px;"><c:if test="${row['MODULE_NAME'] !=null}">${row['MODULE_NAME']}</c:if>
				</td>
				<td>
						<c:if test="${row['NODE_TYPE']=='0'}" >叶子节点</c:if>
						<c:if test="${row['NODE_TYPE']=='1'}" >非叶子节点</c:if>	
				</td>
				<td style="width: 100px;">
					<div class="sui-btn-group">
					    <button class="sui-btn btn-bordered btn-primary" onclick="doAction('${root}/base/BaseModule/edit?id=${row['MODULE_ID']}')">修改</button>
					    <button class="sui-btn btn-bordered btn-danger" onclick="doDel('${root}/base/BaseModule/delete?id=${row['MODULE_ID']}')">删除</button>
					  </div>
				</td>
			</tr>
		</c:forEach>
		<tr>
		</tr>
	</tbody>
</table>
${pageHtml}