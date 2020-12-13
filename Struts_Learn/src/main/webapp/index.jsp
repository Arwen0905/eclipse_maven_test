<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Basic Struts 2 Application - Welcome</title>
    </head>
    <body>
        <h1>Welcome To Struts 2!</h1>
		<p><a href="<s:url action='hello'/>">Hello World</a></p>
        <p><a href="<s:url action='welcome_register'/>">Welcome Register</a></p>
        <p><a href="<s:url action='welcome_lotto'/>">Welcome Lotto</a></p>
       	<h2 class="other">prefix</h2>
        <s:action name="welcome_selector" executeResult="true"/>
        
<%--         <s:action name="welcome_register" executeResult="true"/> --%>

		<script type="text/javascript">
// 		$(document).ready(function(){
// 			$.ajax({
// 				type:"post",
// 				url:"register",
// 				data:"",
// 				dataType:"json",
// 				success:function(message){
// 					$('.other').text("message:"+message)
// 					jsonObj = JSON.parse(message)
// 					$('.other').text("jsonObj:"+jsonObj)
// 				},
// 				error:function(){
// 					alert("Ajax出現異常")
// 				}
// 			})
// 		})
		</script>
    </body>
</html>
