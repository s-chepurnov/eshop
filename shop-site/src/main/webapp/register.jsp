<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.shop.entity.Customer"%>
<html>
<body>
<%@ include file="header.jspf"%>
<br>

	<!-- if the entered login in 'login form' doesn't exist this message will appear -->
	<br>${cause}
	
	<!-- registration form -->
	<form method="post" action="login">
	Registration form:
		<table cellpadding="1" cellspacing="1" border="1">
			<tr>
				<td>first name:</td>
				<td><input type="text" name="firstName"/></td>
			</tr>
			<tr>
				<td>last name:</td>
				<td><input type="text" name="lastName"/></td>
			</tr>
			<tr>
				<td>phone:</td>
				<td><input type="text" name="phone"></td>
			</tr>
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
				<td><input type="submit" value="register"></td>
			</tr>
		</table>
	</form>
</body>
</html>