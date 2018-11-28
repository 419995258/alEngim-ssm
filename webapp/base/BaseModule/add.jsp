<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function doSave(){
	var url="${root}/base/BaseModule/save";
	$("#loading").show();
	$("#content").hide();
	var moduleFrmData=$("#moduleFrm").serialize();
	$.ajax({
		  type: 'POST',
		  url: url,
		  data:moduleFrmData,
		  cache:false,
          success:function(msg){
        	  doAction('${root}/base/BaseModule/index');
           }
	});
}

$(function(){
	$("#moduleFrm").validate({
		success: function (result) {
			doSave();
			return false;
		}
	})
});

</script>
<form class="sui-form form-horizontal" name="moduleFrm" id="moduleFrm">
<input type="hidden" name="queryParentCode" value="${queryParentCode}" />
<input type="hidden" name="parentCode" value="${queryParentCode}" />
  <div class="control-group">
    <label for="moduleName" class="control-label"><b style="color: #f00;">*</b> 模块名称：</label>
    <div class="controls">
      <input style="width:250px;" type="text" name="moduleName" id="moduleName" maxlength="100"  placeholder="请填写角色名称！" data-rules="required" >
    </div>
  </div>
  <div class="control-group">
    <label for="nodeType" class="control-label"><b style="color: #f00;">*</b> 节点类型：</label>
    <div class="controls">
	<select name="mNodeType" id="mNodeType">
		<option value="1">非叶子节点</option>
		<option value="0">叶子节点</option>
	</select>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label"></label>
    <div class="controls">
      <button type="submit" class="sui-btn btn-primary" >保存</button>
      <button type="button" class="sui-btn" onclick="doAjax('${root}/base/BaseModule/list?queryParentCode=${queryParentCode}','treeContent')">返回</button>
    </div>
  </div>
</form>