<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function doSave(){
	var url="${root}/base/BaseUrl/save";
	$("#loading").show();
	$("#content").hide();
	var myFrmData=$("#myFrm").serialize();
	$.ajax({
		  type: 'POST',
		  url: url,
		  data:myFrmData,
		  cache:false,
          success:function(msg){
        	  doAjax('${root}/base/BaseUrl/list','treeContent');
           }
	});
}

$(function(){
	$("#myFrm").validate({
		success: function (result) {
			doSave();
			return false;
		}
	})
});

</script>
<form class="sui-form form-horizontal" name="myFrm" id="myFrm">
<input type="hidden" name="moduleId" value="${moduleId}"></input>
  <div class="control-group">
    <label for="urlName" class="control-label"><b style="color: #f00;">*</b> URL名称：</label>
    <div class="controls">
      <input style="width:250px;" type="text" name="urlName" id="urlName" maxlength="100"  placeholder="请填写URL名称！" data-rules="required" >
    </div>
  </div>
  <div class="control-group">
    <label for="url" class="control-label"><b style="color: #f00;">*</b> 节点类型：</label>
    <div class="controls">
	<input style="width:250px;" type="text" name="url" id="url" maxlength="100"  placeholder="请填写URL！" data-rules="required" >
    </div>
  </div>
  <div class="control-group">
    <label class="control-label"></label>
    <div class="controls">
      <button type="submit" class="sui-btn btn-primary" >保存</button>
      <button type="button" class="sui-btn" onclick="doAjax('${root}/base/BaseUrl/list?queryParentCode=${queryParentCode}','treeContent')">返回</button>
    </div>
  </div>
</form>