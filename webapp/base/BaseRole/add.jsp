<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function doSave(){
	var url="${root}/base/BaseRole/save";
	$("#loading").show();
	$("#content").hide();
	var roleFrmData=$("#roleFrm").serialize();
	$.ajax({
		  type: 'POST',
		  url: url,
		  data:roleFrmData,
		  cache:false,
          success:function(msg){
        	  doAction('${root}/base/BaseRole/list');
           }
	});
}

$(function(){
	$("#roleFrm").validate({
		success: function (result) {
			doSave();
			return false;
		}
	})
});

</script>
<form class="sui-form form-horizontal" name="roleFrm" id="roleFrm">
  <div class="control-group">
    <label for="roleName" class="control-label"><b style="color: #f00;">*</b> 角色名称：</label>
    <div class="controls">
      <input style="width:250px;" type="text" name="roleName" id="roleName" maxlength="100"  placeholder="请填写角色名称！" data-rules="required" >
    </div>
  </div>
  <div class="control-group">
    <label for="roleCode" class="control-label"><b style="color: #f00;">*</b> 角色代码：</label>
    <div class="controls">
      <input style="width:250px;" type="text" name="roleCode" id="roleCode" maxlength="100"  placeholder="请填写角色代码！" data-rules="required" >
    </div>
  </div>
  <div class="control-group">
    <label for="roleDesc" class="control-label v-top">备注：</label>
    <div class="controls">
      <textarea rows="5" cols="50" name="roleDesc" id="roleDesc" placeholder="请输入备注！" ></textarea>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label"></label>
    <div class="controls">
      <button type="submit" class="sui-btn btn-primary" >保存</button>
      <button type="button" class="sui-btn" onclick="doAction('${root}/base/BaseRole/list')">返回</button>
    </div>
  </div>
</form>