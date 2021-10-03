<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		background: #4682B4; 
	}
	a {
		text-transform:none;
		text-decoration:none;
	} 
	a:hover {
		text-decoration:underline;
	}
</style>
  </head>
  
  <body>
<h1 style="text-align: center;">vaccination for provider</h1>
<div style="font-size: 10pt;">
	<c:choose>
		<c:when test="${empty sessionScope.session_provider_id }">
			<a href="<c:url value='/jsps/provider/login.jsp'/>" target="_parent">Login</a> |&nbsp; 	
		</c:when>
		<c:otherwise>
			Hello, ${sessionScope.session_provider.name }&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<c:url value='/jsps/user/editBasic.jsp'/>" target="body">edit basic information</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<c:url value='/jsps/provider/addAppointment.jsp'/>" target="body">add appointment</a>&nbsp;&nbsp;|&nbsp;&nbsp;			
			<a href="<c:url value='/ProviderServlet?method=exit'/>" target="_parent">logout</a>		
		</c:otherwise>
	</c:choose>

</div>
  </body>
</html>
