<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function goToPage(pageNo){
	var url="${root}/base/BaseLog/list";
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
	    	        	  doAction('${root}/base/BaseLog/list');
	    	           }
	    		});
	      }
	    })
}
</script>
<form class="sui-form form-search" style="text-align:right;" id="searchForm">
  <input type="text" value="${searchValue}" id="searchValue" name="searchValue" class="input-medium search-query" style="width:200px;">
  <button type="button" class="sui-btn btn-primary" onclick="doSearch('${root}/base/BaseLog/list')">查找</button>
</form>
<table class="sui-table table-bordered">
	<thead>
		<tr>
			<th>序号</th>
			<th>类名称</th>
			<th>方法名称</th>
			<th>时间</th>
			<th>操作人</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${dataList}" var="row" varStatus="row_status">
			<tr>
				<td style="width: 30px;">${(row_status.index+1)+((pageNo-1)*pageSize)}</td>
				<td id="${row.id}" index="${row.index}">${row.sourceAsMap['className']}</td>
				<td>${row.sourceAsMap['doMethod']}</td>
				<td>${my431fn:UTC(row.sourceAsMap['@timestamp'])}</td>
				<td>${row.sourceAsMap['doRealName']}</td>
			</tr>
		</c:forEach>
		<tr>
		</tr>
	</tbody>
</table>
${pageHtml}