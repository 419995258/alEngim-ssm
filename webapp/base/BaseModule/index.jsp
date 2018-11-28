<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
<!--
var setting = {
		callback: {
			onClick: zTreeOnClick
		},
		view: {
			dblClickExpand: dblClickExpand
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};

var zNodes =${treeJson};
$(function(){
	var treeObj =$.fn.zTree.init($("#treeDiv"), setting, zNodes);
	var node = treeObj.getNodeByParam("id", "-1");
	treeObj.selectNode(node);
	treeObj.setting.callback.onClick(null, treeObj.setting.treeId, node);
});
function dblClickExpand(treeId, treeNode) {
	return treeNode.level > 0;
}
function zTreeOnClick(event, treeId, treeNode) {
	if(treeNode.nodeType=="1"){
		var treeUrl="${root}/base/BaseModule/list";
		$.ajax({
			  type: 'POST',
			  url: treeUrl,
			  data:{queryParentCode:treeNode.id},
			  cache:false,
		      success:function(rHtml){
		      	  $("#treeContent").html(rHtml);
		       }
		});
	}else{
		var treeUrl="${root}/base/BaseUrl/list";
		$.ajax({
			  type: 'POST',
			  url: treeUrl,
			  data:{muId:treeNode.moduleId},
			  cache:false,
	        success:function(rHtml){
	      	  $("#treeContent").html(rHtml);
	         }
		});
	}
	
}
//-->
</script>
<style type="text/css">
.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
.ztree li ul.level0 {padding:0; background:none;}
</style>
<table style="width:100%">
  <tr>
    <td valign="top" style="width:200px;">
	  <div id="treeDiv" class="ztree" style="height:500px;overflow-y: scroll;overflow-x:auto;border:1px solid #ccc;">
	  </div>
    </td>
    <td valign="top">
	  <div id="treeContent" class="treeContent" style="border:1px solid #ccc;height:500px;padding:10px;">
	  </div>
    </td>
  </tr>
</table>