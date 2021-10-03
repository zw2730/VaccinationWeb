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
    
    <title>My JSP 'personal.jsp' starting page</title>
    
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
    		<table border="1">
		    <table border="1">
		  		<tr>
		    		<th>pid</th>
		    		<th>name</th>
		    		<th>address</th>
		    		<th>phone</th>
		    		<th>provider type</th>
		    		<th>latitude</th>
		    		<th>longitude</th>
		  		</tr>
		  		<tr>
		    		<td>${sessionScope.session_provider.pid }</td>
		    		<td>${sessionScope.session_provider.name }</td>
		    		<td>${sessionScope.session_provider.address }</td>
		    		<td>${sessionScope.session_provider.phone }</td>
		    		<td>${sessionScope.session_provider.providerType }</td>
		    		<td>${sessionScope.session_provider.latitude }</td>
		    		<td>${sessionScope.session_provider.longitude }</td>
		  		</tr>
			</table>
			<table border="1">
				<tr>
				    <th>appointment time</th>
				    <th>appointment id</th>
				</tr>
				 
				 <c:forEach var="item" items="${sessionScope.session_provider_appointments}">
					 <tr>
					    <td><c:out value="${item.datetime }"/></td>
					    <td><c:out value="${item.aid }"/></td>
					  </tr>
				 </c:forEach>
			</table>
			</table>
				<table border="1">
				<tr>
					<th>patient ssn</th>
					<th>patient name</th>
					<th>patient phone</th>
					<th>patient email</th>
					<th>patient address</th>
					<th>appointment id</th>
					<th>date time</th>
					<th>expire time</th>
					<th>accept</th>
					<th>show up</th>
					<th>cancel</th>
				</tr>
				 <c:forEach var="item" items="${sessionScope.session_provider_allocates }">
					 <tr>
					    <td><c:out value="${item.patient.ssn }"/></td>
					    <td><c:out value="${item.patient.name }"/></td>
					    <td><c:out value="${item.patient.phone }"/></td>
					    <td><c:out value="${item.patient.email }"/></td>
					    <td><c:out value="${item.patient.address }"/></td>
					    <td><c:out value="${item.appointment.aid }"/></td>
					    <td><c:out value="${item.appointment.datetime }"/></td>
					    <td><c:out value="${item.expireTime }"/></td>
					    <td><c:out value="${item.accept }"/></td>
					    <td><c:out value="${item.showUp }"/></td>
					    <td><c:out value="${item.cancel }"/></td>
					  </tr>
				 </c:forEach>
			</table>
  </body>
</html>
