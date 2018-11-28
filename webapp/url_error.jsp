<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%request.setAttribute("path", request.getParameter("path")) ;%>
<script>
	alert("角色【${my431fn:getRoleByCode(wsDefaultRoleCode)}】没有地址【${path}】访问权限,请联系管理员");
</script>