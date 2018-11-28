<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<c:if test="${!empty(wsloginName)}">
<script type="text/javascript">
location.href="${root}/base/index.jsp";
</script>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/> 
<meta http-equiv="cache-control" content="no-cache"/> 
<meta http-equiv="expires" content="0"/>   
<title>${appName}</title>
<script src="<c:url value='/js/jquery/jquery-1.9.1.min.js'/>"></script>
<link href="<c:url value='/js/sui/css/sui.min.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/js/sui/js/sui.min.js'/>"></script>
<script src="<c:url value='/js/jquery/jquery.cookie.js'/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/js/aes/rollups/aes.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/aes/components/mode-ecb.js'/>"></script>

<script type="text/javascript">
function Encrypt(word){  
    var key = CryptoJS.enc.Utf8.parse("love431love43112");   

    var srcs = CryptoJS.enc.Utf8.parse(word);  
    var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});  
    return encrypted.toString();  
}  
function Decrypt(word){  
    var key = CryptoJS.enc.Utf8.parse("love431love43112");   

    var decrypt = CryptoJS.AES.decrypt(word, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});  
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();  
}  
	var i=0;
	var j=0;
	function chkSubmit() {
		//saveUser();
		if ($("#loginName").val().length==0||$("#loginName").val()=="请输入用户名") {
	    	$("#errorType").html("用户名不允许为空!");
	    	$(".loginTip").show();
	    	$("#loginName").focus();
	    	change_authimage();
	    	return false;
	    }
		
		if($("#password").val().length==0||$("#password").val()==""){
			$("#password").focus();
			$("#errorType").html("密码不允许为空!");
			$(".loginTip").show();
			change_authimage();
			return false;
		}
	    if($("#rand").val().length==0||$("#rand").val()=="验证码"){
	        $("#errorType").html("请输入图形验证码!");
	        $(".loginTip").show();
	        $("#rand").focus();
	    	change_authimage();
		    return false;
	    }
	    //document.getElementById('loginBtn').disabled="disabled";
	    //document.getElementById('loginBtn').style.cursor="default";
		isLogining=true;
		$("#inputLogin").val(Encrypt($("#loginName").val()));
		$("#inputPwd").val(Encrypt($("#password").val()));
		var user = {
				inputLogin:$("#inputLogin").val(),
				inputPwd:$("#inputPwd").val(),
				inputRand:$("#rand").val()
	        };
    	$.ajax({
 		   type: "POST",
 		   url: '${root}/login?date='+new Date().getTime(),
 		   data:user,
 		   dataType:'json',
 		   success: function(msgs){
			 
			 var msg=msgs.errorType;
			 var failedNum=msgs.failedNum;
			 
 			   if (msg=="success"){
 				  saveUser();
 				  console.log("${root}");
 				  document.location.href="http://localhost:8081/base/index.jsp"
 			   }else if (msg=="ImageChkFailed"){
 				  $("#errorType").html("验证码错误！");
 				  $(".loginTip").show();
  		    	  document.getElementById('loginBtn').disabled="";
  		    	  document.getElementById('loginBtn').style.cursor="print";
  		    	  //document.getElementById('loginBtn').disabled="";
  		    	  //document.getElementById('loginBtn').style.cursor="print";
  		    	  isLogining=false;
  		    	  change_authimage();
 				  return false;
 			   }else if (msg=="UserInactive"){
 				 $("#errorType").html("用户名或密码错误！");
 				 $(".loginTip").show();
  		    	 document.getElementById('loginBtn').disabled="";
  		    	 document.getElementById('loginBtn').style.cursor="print";
  		    	 //document.getElementById('loginBtn').disabled="";
  		    	 //document.getElementById('loginBtn').style.cursor="print";
  		    	 isLogining=false;
  		   		change_authimage();
 				 return false;
 			   }else if (msg=="UserInactiveStatus"){
 				 $("#errorType").html("帐号已被锁定！");
 				 $(".loginTip").show();
  		    	 document.getElementById('loginBtn').disabled="";
  		    	 document.getElementById('loginBtn').style.cursor="print";
  		    	 //document.getElementById('loginBtn').disabled="";
  		    	 //document.getElementById('loginBtn').style.cursor="print";
  		    	 isLogining=false;
  		   	     change_authimage();
 				 return false;  
 			   }else if (msg=="UserInactiveStatus_failedNum"){
 	 				 $("#errorType").html("已登录失败5次，帐号已被锁定10分钟！");
 	 				 $(".loginTip").show();
 	  		    	 document.getElementById('loginBtn').disabled="";
 	  		    	 document.getElementById('loginBtn').style.cursor="print";
 	  		    	 isLogining=false;
 	  		   	      change_authimage();
 	 				 return false;  
 	 			}else if (msg=="UserInactiveAreaStatus"){
 				  $("#errorType").html("帐号所属地区不存在！");
 				  $(".loginTip").show();
  		    	 document.getElementById('loginBtn').disabled="";
  		    	 document.getElementById('loginBtn').style.cursor="print";
  		    	 //document.getElementById('loginBtn').disabled="";
  		    	 //document.getElementById('loginBtn').style.cursor="print";
  		    	 isLogining=false;
  		   	     change_authimage();
 				 return false;  
 			   }
 		   }
 		}); 
	}
	function enter(frm,evt) {
		var k=window.event?evt.keyCode:evt.which;
		if (k==13) {
			chkSubmit(); 
		}
	}
	function nextEnter(evt,type){
		var k=window.event?evt.keyCode:evt.which;
		if (k==13) {
			if(type=='username'){
				document.getElementById('password').focus();
			}else if(type == 'password'){
				document.getElementById('rand').focus();
			}
		}
	}
	function change_authimage()
	{
		document.getElementById('authimage').src="${root}/authimage.gif?r="+Math.random();
	}
	
	//初始化页面时验证是否记住了不在提醒
	function readercookie(){
	    if ($.cookie("rememberLm") == "true") {
	        $('#rememberLm').attr('checked','checked');
	        $('#rememberLm').prop('checked',true);
	        $('#rememberLm').parent().parent().addClass('ck_model_on');
	        $("#loginName").val($.cookie("loginName"));
	    }
	    if ($.cookie("rememberPwd") == "true") {
	    	
	        $('#rememberPwd').attr('checked','checked');
	        $('#rememberPwd').prop('checked',true);
	        $('#rememberPwd').parent().parent().addClass('ck_model_on');
	        $("#password").val($.cookie("password"));
	    }
	    if ($.cookie("rememberLm") != "true" || $.cookie("rememberPwd") != "true") {
	    	$('#loginName').focus();
	    }
    }
	
	$(document).ready(function(){
		readercookie();
		$('.ck_model label').click(function(){
			$(this).toggleClass('ck_model_on');
			if($(this).attr('class')=="ck_model_on"){
				$(this).children().children().attr('checked','checked');
				$(this).children().children().prop('checked',true);
			}else{
				$(this).children().children().removeAttr('checked');
				$(this).children().children().prop('checked',false);
			}
			return false;
		})
	});
	
	//保存用户信息
	function saveUser() {
		if(document.getElementById("rememberLm").checked){
	        var userName = $("#loginName").val();
	        $.cookie("rememberLm", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
	        $.cookie("loginName", userName, { expires: 7 }); // 存储一个带7天期限的 cookie
	    } else {
	        $.cookie("rememberLm", "false", { expires: -1 });
	        $.cookie("loginName", '', { expires: -1 });
	    }
		if(document.getElementById("rememberPwd").checked){
			var passWord = $("#password").val();
	        $.cookie("rememberPwd", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
	        $.cookie("password", passWord, { expires: 7 }); // 存储一个带7天期限的 cookie
	    } else {
	        $.cookie("rememberPwd", "false", { expires: -1 });
	        $.cookie("password", '', { expires: -1 });
	    }
	}
</script>
</head>
<body>
<form action="login.jspx" method="post" name="frm" id="frm">
<input type="hidden" name="inputLogin"  id="inputLogin" value="" />
<input type="hidden" name="inputPwd" id="inputPwd" value="" />
<input type="hidden" name="inputRand" id="inputRand" value="" />
</form>
<div class='sui-container' style="position: absolute;left: 50%;top: 50%;width:400px;height:200px;margin-left:-200px;margin-top:-120px;">
<div class="sui-form form-horizontal" style="border:1px solid #ccc;padding-top:20px;">

  <div class="control-group">
    <label class="control-label">登录名：</label>
    <div class="controls">
      <input class="input-medium input-fat" type='text'  tabIndex="1" name="loginName" id="loginName" onkeypress="nextEnter(event,'username')"  placeholder="用户名\手机\Email" value="" onfocus="if(this.value==this.defaultValue){this.value='';};" onblur="if(this.value==''){this.value=this.defaultValue;};"/>
    </div>
  </div>

  <div class="control-group">
    <label class="control-label">密码：</label>
    <div class="controls">
      <input class="input-medium input-fat"  type="password" tabIndex="2" placeholder="密码" onkeypress="nextEnter(event,'password')" tabindex="2" name="password" placeholder="请输入密码" id="password" onfocus="if(this.value==this.defaultValue){this.value='';};" onblur="if(this.value==''){this.value=this.defaultValue;};"  />
    </div>
  </div>

  <div class="control-group">
    <label class="control-label">验证码：</label>
    <div class="controls">
      <input class="input-small input-fat"  type="text" tabIndex="3" name="rand" placeholder="验证码" value="" autocomplete="off" disableautocomplete tabindex="3" id="rand" onfocus="if(this.value==this.defaultValue){this.value='';}; " onblur="if(this.value==''){this.value=this.defaultValue;};" onkeypress="enter(this.form,event)" maxlength="4"  />
      <span><img id="authimage" src="${root}/authimage.gif" style="cursor:pointer;width:68px ;height:24px;" onclick="change_authimage();" title="点击换一个" /></span> <a href="javascript:change_authimage();">换一个</a>
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label"></label>
      <label data-toggle="checkbox" class="checkbox-pretty inline">
        <input type="checkbox" id="rememberLm" name="rememberLm"><span>记住用户名</span>
      </label>
      <label data-toggle="checkbox" class="checkbox-pretty inline">
        <input type="checkbox" id="rememberPwd" name="rememberPwd"><span>记住密码</span>
      </label>
  </div>
  
  <div class="control-group">
    <label class="control-label"></label>
    <div class="controls">
      <button type="button" class="sui-btn btn-primary" onclick="chkSubmit()" id="loginBtn">登录</button>
    </div>
  </div>
  
</div>
</div>
</body>
</html>
