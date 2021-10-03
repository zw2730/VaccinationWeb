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
    
    <title>My JSP 'editAllocateStatus.jsp' starting page</title>
    
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
	<input type="hidden" name="method" value="updateAllocateStatus"/>
	appointment id：<input type="text" name="aid" value="${form.aid }"/>
	<span style="color: red; font-weight: 900">${errors.aid }</span>
	<br/>
	isAccept：Yes <input type="checkbox" name="accept" value="true"/>    NO  <input type="checkbox" name="accept" value="false"/>
	<span style="color: red; font-weight: 900">${errors.isAccept }</span>
	<br/>
	isCancel：Yes <input type="checkbox" name="cancel" value="true"/>    NO  <input type="checkbox" name="cancel" value="false"/>
	<span style="color: red; font-weight: 900">${errors.isCancel }</span>
	<br/>
	isShowup：Yes <input type="checkbox" name="showup" value="true"/>    NO  <input type="checkbox" name="showup" value="false"/>
	<span style="color: red; font-weight: 900">${errors.isAccept }</span>
	<br/>
	<input type="submit" value="update"/>
</form>
  </body>
</html>
