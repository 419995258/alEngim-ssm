<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html" />
</head>
<body>
<c:if test="${empty(wsUserId)}">
<script type="text/javascript">
	top.document.location.href="${root}/login.jsp";
</script>
</c:if>
<c:if test="${!empty(wsUserId)}">
<script type="text/javascript">
	document.location.href="${root}/base/index.jsp";
</script>
</c:if>
</body>
</html>