<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editPreferedTime.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<form action="<c:url value='/PatientServlet'/>" method="post">
	<input type="hidden" name="method" value="uploadPreferredTime"/>
	${errors}
		day：<input type="text" name="day" value="${form.day }"/>${errors.preferredDay }
		<span style="color: red; font-weight: 900">${errors.preferredDay }</span>
		<br/>
		preferred slot start：<input type="text" name="slotStarttime" value="${form.slotStarttime }"/>${errors.slotStartTime }
		<span style="color: red; font-weight: 900">${errors.slotStartTime }</span>
		<br/>
		preferred slot end：<input type="text" name="slotEndtime" value="${form.slotEndtime }"/>${errors.slotEndTime }
		<span style="color: red; font-weight: 900">${errors.slotEndTime }</span>
		<br/>
	<input type="submit" value="add"/>
</form>
  </body>
</html>
