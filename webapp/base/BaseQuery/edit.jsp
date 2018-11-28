<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function doSave(){
	var url="${root}/base/BaseQuery/save";
	$("#loading").show();
	$("#content").hide();
	var queryFrmData=$("#queryFrm").serialize();
	$.ajax({
		  type: 'POST',
		  url: url,
		  data:queryFrmData,
		  cache:false,
          success:function(msg){
        	  doAction('${root}/base/BaseQuery/list');
           }
	});
}

$(function(){
	$("#queryFrm").validate({
		success: function (result) {
			doSave();
			return false;
		}
	})
});

</script>
<form class="sui-form form-horizontal" name="queryFrm" id="queryFrm">
<input type="hidden" name="id" id="id" value="${baseQuery.id}"/>
  <div class="control-group">
    <label for="queryName" class="control-label"><b style="color: #f00;">*</b> SQLKEY：</label>
    <div class="controls">
      <input style="width:250px;" value="${baseQuery.queryName}" type="text" name="queryName" id="queryName" placeholder="请填写SQLKEY！" data-rules="required" maxlength="90">
    </div>
  </div>
  <div class="control-group">
    <label for="queryTitle" class="control-label"><b style="color: #f00;">*</b> 功能名称：</label>
    <div class="controls">
      <input style="width:250px;" value="${baseQuery.queryTitle}" type="text" name="queryTitle" id="queryTitle" maxlength="90" placeholder="请填写功能名称！" data-rules="required" >
    </div>
  </div>
  <div class="control-group">
    <label for="queryType" class="control-label"><b style="color: #f00;">*</b> SQL类型：</label>
    <div class="controls">
    <select id="queryType" name="queryType">
    	<option <c:if test="${baseQuery.queryType eq 'sql'}">selected</c:if> value="sql">sql</option>
    	<option <c:if test="${baseQuery.queryType eq 'hql'}">selected</c:if> value="hql">hql</option>
    </select>
    </div>
  </div>
  <div class="control-group">
    <label for="queryString" class="control-label v-top"><b style="color: #f00;">*</b> SQL语句：</label>
    <div class="controls">
      <textarea rows="5" cols="50" name="queryString" id="queryString" placeholder="请输入SQL语句！" data-rules="required">${baseQuery.queryString}</textarea>
    </div>
  </div>
  <div class="control-group">
    <label for="note" class="control-label v-top">功能描述：</label>
    <div class="controls">
      <textarea rows="5" cols="50" name="note" id="note" placeholder="请输入功能描述！" >${baseQuery.note}</textarea>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label"></label>
    <div class="controls">
      <button type="submit" class="sui-btn btn-primary" >保存</button>
      <button type="button" class="sui-btn" onclick="doAction('${root}/base/BaseQuery/list')">返回</button>
    </div>
  </div>
</form>