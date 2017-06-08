<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=contextPath%>/js/jquery/jquery-1.7.js" type="text/javascript"></script>
<script src="<%=contextPath%>/js/login.js" type="text/javascript"></script>

<title>Registration Page</title>
</head>
<body>

<%--<form:form method="POST" commandName="parts" action="addParts">  --%>
<form:form method="POST" commandName="parts" action="javascript:onSubmit()">
	<table>
		<tr>
			<td>Part Name :</td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td>Part Code :</td>
			<td><form:input path="code" /></td>
		</tr>
		<tr>
			<td>Category :</td>
			<td><form:select path="categoryId">
				<form:option value="0" label="Select" />
				<form:option value="1" label="Handles" />
				<form:option value="2" label="Bib Cock Body" />
				<form:option value="3" label="Bib Handle Body" />
			</form:select></td>
		</tr>
		<tr>
			<td>Quantity :</td>
			<td><form:input path="quantity" /></td>
		</tr>
		<tr>
			<td>Threshold Quantity :</td>
			<td><form:input path="threshold" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"></td>
		</tr>
	</table>
</form:form>

</body>
</html>