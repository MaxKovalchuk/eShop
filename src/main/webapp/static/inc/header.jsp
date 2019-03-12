<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="<c:url value="/static/styles/normalize.css" />" type="text/css"
	media="screen" />
<link rel="stylesheet" href="<c:url value="/static/styles/main.css" />"
	type="text/css" media="screen" />
<title>Document</title>
</head>
<body>

	<div class="header">
		<ul>
			<c:if test='${sessionScope.user == null }'>
				<li class='hover'><a href="./authorization">LogIn</a></li>
				<li class='hover'><a href="./registration">Registrate</a></li>
			</c:if>
			<li class='hover'><a href="./products">Products</a></li>
			<li class='hover'><a href="./cart" id='cart'> Cart: <c:choose>
						<c:when test="${sessionScope.cart.size != null}">
							${sessionScope.cart.size}
						</c:when>
						<c:otherwise>0</c:otherwise>
					</c:choose>
			</a></li>
			<li class='divider'></li>
			<li class='NoRadius'><form action='' method='get'>
					<input name='search' type='text' placeholder='Search' size='50'>
					<input class = 'input' type = 'submit' value = 'Go'/>
				</form></li>
			<li
				class=<c:choose><c:when test='${sessionScope.user == null }'>'divider2'</c:when><c:otherwise> 'divider'</c:otherwise></c:choose>></li>
			<c:if test='${sessionScope.user != null }'>
				<li class="user hover"><a href="./profile">
						User:${sessionScope.user.name} </a></li>
				<li class='black_li'><a href='?logout=1' class='logOut'>
						&#9032 </a></li>
			</c:if>
		</ul>

		<!-- <c:if test='${sessionScope.user != null }'>
				<div class="button">
					<a href='?logout=1'>LogOut</a>
				</div>
			</c:if>
			 -->
	</div>