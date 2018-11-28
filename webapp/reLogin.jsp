<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html" />
</head>
<body>

<script type="text/javascript">
function mylogout(){
	alert("您的账号已在其地方登录，您已被迫下线...");
	//top.document.location.href="http://localhost:8080/edu_info//login.jsp";
	top.document.location.href="${root}/login.jsp";
}
mylogout();
</script>
</body>
</html>
