<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h1>register</h1>
  <%--
  1. show errors
  2. show exception errors
  3. show back
   --%>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value='/PatientServlet'/>" method="post">
    <input type="hidden" name="method" value="regist"/>
		ssn：<input type="text" name="ssn" value="${form.ssn }"/>
		<span style="color: red; font-weight: 900">${errors.ssn }</span>
		<br/>
		name：<input type="text" name="name" value="${form.name }"/>
		<span style="color: red; font-weight: 900">${errors.name }</span>
		<br/>
		email：<input type="text" name="email" value="${form.email }"/>
		<span style="color: red; font-weight: 900">${errors.email }</span>
		<br/>
		birthdate：<input type="text" name="birthdate" value="${form.birthdate }"/>
		<span style="color: red; font-weight: 900">${errors.birthdate }</span>
		<br/>
		address：<input type="text" name="address" value="${form.address }"/>
		<span style="color: red; font-weight: 900">${errors.address }</span>
		<br/>
		phone：<input type="text" name="phone" value="${form.phone }"/>
		<span style="color: red; font-weight: 900">${errors.phone }</span>
		<br/>
		max distance：<input type="text" name="maxDist" value="${form.maxDist }"/>
		<span style="color: red; font-weight: 900">${errors.maxDist }</span>
		<br/>
	<input type="submit" value="register"/>
</form>
  </body>
</html>
