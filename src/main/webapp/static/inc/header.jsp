<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="././static/styles/normalize.css"
	type="text/css">
<link rel="stylesheet" href="././static/styles/main.css" type="text/css">
<link rel="stylesheet" href="Products.html">
<link rel="stylesheet" href="Registrate.html">
<title>Document</title>
</head>
<body>

	<div class="header">
		<ul>
			<c:if test = '${sessionScope.user == null }' >
			<li <c:if test = '${requestScope.servletPath.equals("/authorization")}'>class = 'active'</c:if>><a href="./authorization">LogIn</a></li>
			<li <c:if test = '${req.servletPath.equals("/registration")}'>class = 'active'</c:if>><a href="./registration">Registrate</a></li>
			</c:if>
			<li <c:if test = '${request.servletPath.equals("/products")}'>class = 'active'</c:if>><a href="./products">Products</a></li>
			<li <c:if test = '${request.getServletPath().equals("/cart")}'>class = 'active'</c:if>><a href="./cart">Cart: ${sessionScope.cart.size}</a></li>
			<c:if test = '${sessionScope.user != null }' >
			<li class="user"><a href="">User : ${sessionScope.user.name}</a></li>
			</c:if>
		</ul>
		<c:if test = '${sessionScope.user != null }' >
		<div class="button">
			<a href = '?logout=1'>LogOut</a>
		</div>
		</c:if>
	</div>