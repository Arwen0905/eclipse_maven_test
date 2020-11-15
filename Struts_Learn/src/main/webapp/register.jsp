<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<s:head></s:head>
</head>
<body>
	<s:form action="register">
		<s:select 
			id="customs"
			name="qcustCd"
			list="bfCustCdList"
			headerKey=""
			headerValue="請選擇"
			cssStyle="width:180px"
			/>
		<s:textfield name="person.account" label="account" value=""/>
		<s:textfield name="person.password" label="password" value=""/>
		<s:textfield name="person.email" label="email" value=""/>
		<s:submit label="Click"/>
	</s:form>
</body>
</html>
<s:head style="color:red" />