<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file = "../../static/inc/header.jsp"%>

<center class='content'>
	<table>
		<tr><td><p><img src = 'static/images/${product.id}.jpg'/></p></td></tr>
		<table>
			<tr>
				<td><h1>${product.name}</h1><hr></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td>${product.price}UAH<hr></td>
			</tr>
			<tr>
				<td>Category:</td>
				<td>
					<c:choose>
						<c:when test="${product.category == 1}">
						Phones
						</c:when>
						<c:when test="${product.category == 2}">
						Laptops
						</c:when>
						<c:when test="${product.category == 3}">
						Watches
						</c:when>
					</c:choose>
				<hr>
				</td>
			</tr>
			<tr>
				<td>Describtion:</td>
				<td>${product.desc}<hr></td>
			</tr>
			<tr>
				<td></td>
				<td><a href = './cart?productID=${product.id}'>Buy</a></td>
			</tr>
		</table>
	</table>
</center>
<%@include file = "../../static/inc/footer.jsp"%>