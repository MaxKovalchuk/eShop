<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file="../../static/inc/header.jsp"%>

<c:set var="i" value="0" />
<center class='content'>
	<form action='' method='post'>
		<table>
			<tr>
				<td>Phones </td>
				<td>Laptops</td>
				<td>Watches</td>
			</tr>
			<tr>
				<td><input name='phones' type='checkbox' value='1' ${phones}></td>
				<td><input name='laptops' type='checkbox' value='2' ${laptops}></td>
				<td><input name='watches' type='checkbox' value='3' ${watches}></td>
			</tr>
			<tr>
				<td></td>
				<td><input name='search' type='submit' value='Choose'></td>
				<td></td>
			</tr>
		</table>
	</form>
	
		<c:forEach items="${products}" var="str">
		
			<div class="products_bloc">
				<form action='' method='post'>
					<input type='hidden' name='productSelected' value='${str.id}'>
					<a>
						<img src="static/images/${str.id}.jpg">
					</a>
					<p>
						<a>${str.name}</a>
					</p>
					<p>
						<a>${str.price} UAH</a>
					</p>
					<p>
						<input name='lookCloser' type='submit' value='Look closer'>
					</p>
					<p class='.products_bloc_p'>
						<a href = './cart?productID=${str.id}'>Buy</a>
					</p>
				</form>
			</div>
			
		</c:forEach>
</center>
<%@include file="../../static/inc/footer.jsp"%>
	<!-- <td>
				<form action='' method='post'>
					<input type='hidden' name='productSelected' value='${str.id}'>
					<p>
						<img src='static/images/${str.id}.jpg' height='300px' width='175px' />
					</p>
					<p>${str.name}</p>
					<p>${str.price}</p>
					<p>
						<input name='lookCloser' type='submit' value='Look closer'>
					</p>
				</form>
			</td>
			<c:set var="i" value="${i + 1}" />
			<c:if test="${i == 2}">
				</tr>
				<c:set var="i" value="0" />
			</c:if> -->
