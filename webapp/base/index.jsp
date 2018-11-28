<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/> 
<meta http-equiv="cache-control" content="no-cache"/> 
<meta http-equiv="expires" content="0"/>   
<title>${appName}</title>
<script src="<c:url value='/js/jquery/jquery-1.9.1.min.js'/>"></script>

<link href="<c:url value='/js/sui/css/sui.min.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/js/sui/js/sui.min.js'/>"></script>

<link href="<c:url value='/js/zTree_v3/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/js/zTree_v3/js/jquery.ztree.core.js'/>"></script>
<script src="<c:url value='/js/zTree_v3/js/jquery.ztree.excheck.min.js'/>"></script>
<style>

</style>
<script type="text/javascript">
function doMenu(menuFlag){
	if(menuFlag=='index'){
		document.location.href="${root}/base/index.jsp";
	}else{
		$("#index").removeClass("active");
		$("#area").removeClass("active");
		$("#role").removeClass("active");
		$("#query").removeClass("active");
		$("#module").removeClass("active");
		$("#log").removeClass("active");
		$("#"+menuFlag).addClass("active");
	}
	if(menuFlag=='area'){
		
	}
	if(menuFlag=='query'){
		var url="${root}/base/BaseQuery/list";
		doAction(url);
	}
	if(menuFlag=='role'){
		var url="${root}/base/BaseRole/list";
		doAction(url);
	}
	if(menuFlag=='properties'){
		var url="${root}/base/BaseModule/index";
		doAction(url);
	}
	if(menuFlag=='log'){
		var url="${root}/base/BaseLog/list";
		doAction(url);
	}
}

function doAction(url){
	$("#loading").show();
	$("#content").hide();
	$.ajax({
		  type: 'POST',
		  url: url,
		  cache:false,
          success:function(rtHtml){
        	  $("#loading").hide();
        	  $("#content").html(rtHtml);
        	  $("#content").show();
           }
	});
}

function doSearch(url){
	$("#loading").show();
	$("#content").hide();
	var searchFormData=$("#searchForm").serialize();
	$.ajax({
		  type: 'POST',
		  url: url,
		  data: searchFormData,
		  cache:false,
          success:function(rtHtml){
        	  $("#loading").hide();
        	  $("#content").html(rtHtml);
        	  $("#content").show();
           }
	});
}

function doAjax(url,divId){
	$("#loading").show();
	$("#content").hide();
	$.ajax({
		  type: 'POST',
		  url: url,
		  cache:false,
          success:function(rtHtml){
        	  $("#loading").hide();
        	  $("#"+divId).html(rtHtml);
        	  $("#content").show();
           }
	});
}

</script>
</head>
<body>
<div class="sui-navbar">
  <div class="navbar-inner">
    <div class="sui-container"><a href="#" class="sui-brand">SZD</a>
      <ul class="sui-nav">
        <li id="index" class="active"><a href="#" onclick="doMenu('index');">首页</a></li>
        <li id="area"><a href="#" onclick="doMenu('area');">地区管理</a></li>
        <li id="module"><a href="#" onclick="doMenu('properties');">属性管理</a></li>
      	<li id="role"><a href="#" onclick="doMenu('role');">角色管理</a></li>
      	<li id="query"><a href="#" onclick="doMenu('query');">SQL管理</a></li>
      	<li id="log"><a href="#" onclick="doMenu('log');">操作日志</a></li>
        <li class="sui-dropdown"><a href="javascript:void(0);" data-toggle="dropdown" class="dropdown-toggle">其他 <i class="caret"></i></a>
          <ul role="menu" class="sui-dropdown-menu">
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">关于</a></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">项目组成员</a></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">版权</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</div>
<div id="loading" style="display:none;" class="sui-loading loading-xlarge"><i class="sui-icon icon-pc-loading"></i></div>
<div id="content" style="display:none;"  class="sui-container">
首页
</div>
</body>
</html>