<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main page</title>
</head>
<body>
<%@ include file="header.jspf"%>
<br>

<a href="index.jsp">index.jsp</a>
</br>
<a href="product?id=3">product?id=3</a>
</br>
<a href="http://localhost:8080/shop-site/products?p=1">products?p=1</a>
</br>
<a href="products?p=2">page 2</a>
</br>
<a href="login">loginController</a>    
</br>
<a href="login.jsp">login form</a>   
</br>
<a href="register.jsp">register form</a>

</body>
</html>