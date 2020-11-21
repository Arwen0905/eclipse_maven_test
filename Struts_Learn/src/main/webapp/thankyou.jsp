<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome Register!</h1>
	<p>your information</p>
	<p><s:property value="person"/></p>
	<h1><s:property value="checkMe"/></h1>
	<h1><s:property value="comeDB"/></h1>
	<p><a type="buttom" href="<s:url action='index'/>" />return to HOME page</p>
</body>
</html>