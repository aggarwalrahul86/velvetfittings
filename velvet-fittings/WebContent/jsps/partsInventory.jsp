<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String contextPath = request.getContextPath();
%>
<head>
	<title>Add/Delete Parts</title>
	<jsp:include page="/jsps/commonMetaData.jsp"></jsp:include>
	<jsp:include page="/jsps/commonScript.jsp"></jsp:include>
</head>

<body>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div id="partsInventoryId">
<%--<form:form method="POST" commandName="parts" action="addParts">  --%>
<form:form method="POST" commandName="parts" action="" id="partsFrm">
	<table>
		<tr>
		<td>Category :</td>
			<td>
			<form:select path="categoryId" id="categoryId" onchange=""
			 title="Category List">
				<form:option value="-1" label="Select"></form:option>
				<form:options items="${categoryList}"
					itemValue="code" itemLabel="name" />
			</form:select>
		</td>
		</tr>
		<tr>
		<td>Parts :</td>
			<td>
			<form:select path="partsId" id="partsId" onchange="populateDependentpartsInfo('partsId','showPartsInfo')"
			 title="Part List">
				<form:option value="-1" label="Select"></form:option>
				<form:options items="${partList}"
					itemValue="code" itemLabel="name" />
			</form:select>
		</td>
		</tr>
		<tr>
			<td>Part Name :</td>
			<td><form:input path="name" id="name" /></td>
		</tr>
		<tr>
			<td>Part Code :</td>
			<td><form:input path="code" id="code" /></td>
		</tr>
		<tr>
			<td>Available Quantity :</td>
			<td><form:input path="quantity" id="quantity" /></td>
		</tr>
		<tr>
			<td>Threshold Quantity :</td>
			<td><form:input path="threshold" id="threshold" /></td>
		</tr>
		<tr>
		<td> Quantity to be Added: </td>
		<td><input type="text" id="addedQnty" name="addedQnty"></td>
		</tr>
		<tr>
		<td> Quantity to be Deleted: </td>
		<td><input type="text" id="deleteQnty" name="deleteQnty"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"></td>
		</tr>
	</table>
</form:form>
</div>
</body>
</html>