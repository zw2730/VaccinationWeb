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
    
    <title>My JSP 'edit.jsp' starting page</title>
    
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
      <h1>edit</h1>
  <%--
  1. show errors
  2. show exception errors
  3. show back
   --%>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value='/PatientServlet'/>" method="post">
	<input type="hidden" name="method" value="update"/>
	birthdate：<input type="text" name="birthdate" value="${sessionScope.session_patient.birthdate }"/>
	<span style="color: red; font-weight: 900">${errors.birthdate }</span>
	<br/>
	address：<input type="text" name="address" value="${sessionScope.session_patient.address }"/>
	<span style="color: red; font-weight: 900">${errors.address }</span>
	<br/>
	phone：<input type="text" name="phone" value="${sessionScope.session_patient.phone }"/>
	<span style="color: red; font-weight: 900">${errors.phone }</span>
	<br/>
	max distance：<input type="text" name="maxDist" value="${sessionScope.session_patient.maxDist }"/>
	<span style="color: red; font-weight: 900">${errors.maxDist }</span>
	<br/>
	<input type="submit" value="update"/>
</form>
  </body>
</html>
