<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.shop.entity.Customer"%>
<html>
<body>

	<%@ include file="header.jspf"%>
	<br>

	<!-- if login and password are empty or invalid this message will appear -->
	<br>${cause}
	
	<!-- login form -->
	<form method="post" action="login">
	Login:
		<table cellpadding="1" cellspacing="1" border="1">
			<tr>
				<td>email:</td>
				<td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="login"></td>
			</tr>
			
		</table>
	</form>

	<br>The <a href="register.jsp">registration form</a> for a new customer
	
</body>
</html>