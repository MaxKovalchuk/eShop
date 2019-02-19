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
			<li><a href="./authorization">LogIn</a></li>
			<li><a href="./registration">Registrate</a></li>
			<li><a href="./products">Products</a></li>
			<li><a href="./cart">Cart: ${sessionScope.cart.size}</a></li>
			<li class="user"><a href="">User : ${session.getUser().name }</a></li>
		</ul>
		<div class="button">
			<form action ='' method='post'>
				<input type='hidden' name='logout' value = '1' /> 
				<input type='submit' name='logoutButton' />
			</form>
			<!--<button>LogOut</button>-->
		</div>
	</div>