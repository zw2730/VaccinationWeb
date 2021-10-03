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
		  		<tr>
		    		<th>ssn</th>
		    		<th>name</th>
		    		<th>birthdate</th>
		    		<th>address</th>
		    		<th>phone</th>
		    		<th>max distance</th>
		    		<th>latitude</th>
		    		<th>longitude</th>
		    		<th>email</th>
		    		<th>gid</th>
		  		</tr>
		  		<tr>
		    		<td>${sessionScope.session_patient.ssn }</td>
		    		<td>${sessionScope.session_patient.name }</td>
		    		<td>${sessionScope.session_patient.birthdate }</td>
		    		<td>${sessionScope.session_patient.address }</td>
		    		<td>${sessionScope.session_patient.phone }</td>
		    		<td>${sessionScope.session_patient.maxDist }</td>
		    		<td>${sessionScope.session_patient.latitude }</td>
		    		<td>${sessionScope.session_patient.longitude }</td>
		    		<td>${sessionScope.session_patient.email }</td>
		    		<td>${sessionScope.session_patient.gid }</td>
		  		</tr>
			</table>
			<table border="1">
				<tr>
				    <th>day</th>
				    <th>preferred slot start time</th>
				    <th>preferred slot end time</th>
				 </tr>
				 
				 <c:forEach var="item" items="${sessionScope.session_patient_preferredTime}">
					 <tr>
					    <td><c:out value="${item.day }"/></td>
					    <td><c:out value="${item.slotStarttime }"/></td>
					    <td><c:out value="${item.slotEndtime }"/></td>
					  </tr>
				 </c:forEach>
			</table>
			<table border="1">
				<tr>
					<th>provider name</th>
					<th>provider phone</th>
					<th>provider address</th>			
					<th>appointment id</th>
					<th>date time</th>
					<th>expire time</th>
					<th>accept</th>
					<th>show up</th>
					<th>cancel</th>
				</tr>
				 <c:forEach var="item" items="${sessionScope.session_patient_allcotes }">
					 <tr>
					    <td><c:out value="${item.provider.name }"/></td>
					    <td><c:out value="${item.provider.phone }"/></td>
					    <td><c:out value="${item.provider.address }"/></td>
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
