<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file="../../static/inc/header.jsp"%>

<c:set var="i" value="0" />
<center class='content'>
 <form action = '' method = 'get'><input type = 'text' name = 'search'/><input type = 'submit' value = 'Go'/></form>
	<form action='' method='post'>
		<table>
			<tr>
				<td>Phones</td>
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
			<a href='?productSelected=${str.id}'> <img
				src="static/images/${str.id}.jpg">
			</a>
			<p>
				<a href='?productSelected=${str.id}'>${str.name}</a>
			</p>
			<p>
				<a>${str.price} UAH</a>
			</p>
			<p class='.products_bloc_p'>
				<input type='button' onclick="minus('${str.id}')" value='-' /><span
					id='q${str.id}'>1</span> <input type='button'
					onclick="plus('${str.id}')" value='+' /> <input type='button'
					onclick="buy('${str.id}')"
					value='Buy' />
			</p>
		</div>

	</c:forEach>
</center>
<%@include file="../../static/inc/footer.jsp"%>
<script src="static/scripts/jquery-3.3.1.min.js"></script>
<script src="static/scripts/toCart.js"></script>