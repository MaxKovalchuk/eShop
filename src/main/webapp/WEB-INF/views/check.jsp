<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file="../../static/inc/header.jsp"%>

<center class='content'>
	<div class='chek'>
		<p>
			Thanks for purchase
			<c:if test="${sessionScope.user != null}">, ${sessionScope.user.name}</c:if>
			!
		</p>
		<c:forEach items="${cartBuffer.products.keySet()}" var="product">
			<p>${product.name} x ${cartBuffer.products.get(product)} =
				${product.price * cartBuffer.products.get(product)} UAH</p>
		</c:forEach>
		<p>Total cost: ${cartBuffer.totalCost}</p>
		<div class='chek_button'>
			<form action='' method='post'>
				<input name='goToProducts' type='hidden' value='1' /> <input
					name='goBack' type='submit' value='OK' />
			</form>
		</div>
	</div>
</center>

<%@include file="../../static/inc/footer.jsp"%>