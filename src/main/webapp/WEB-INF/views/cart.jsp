<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file="../../static/inc/header.jsp"%>

<center class='content_flex'>

	<div class="content_left">
		<c:forEach items="${sessionScope.cart.products.keySet()}"
			var="product">
			<div class='content_box' id='box${product.id}'>
				<a href='./products?productSelected=${product.id}'> <img
					src="static/images/${product.id}.jpg">
				</a>
				<p>
					<a href='./products?productSelected=${product.id}'>${product.name}</a>
				</p>
				<p>
					<a id='p${product.id}'>Price: ${product.price} *
						${sessionScope.cart.products.get(product)} = ${product.price * sessionScope.cart.products.get(product)}
						UAH</a>
				</p>
				<p>
					<input type='button' onclick="minus('${product.id}')" value='-'
						id='minus' /><span id='q${product.id}'>${sessionScope.cart.products.get(product)}</span>
					<input type='button' onclick="plus('${product.id}')" value='+'
						id='plus' />
				</p>
				<p>
				<form method='post' action=''>
					<input type='hidden' name='rmv' value='${product.id}' /> 
					<input type='submit' value='Delete' />
				</form>
				</p>
			</div>
		</c:forEach>
	</div>

	<c:if test="${sessionScope.cart.products.size() > 0}">
		<div class='content_right'>
			<p class='content_right_p' id='ttlCost'>
				<b>Totalcost : ${sessionScope.cart.totalCost} UAH</b>
			</p>
			<a href='?bought=1'>Buy</a>
		</div>
	</c:if>

</center>
<%@include file="../../static/inc/footer.jsp"%>
<script src="static/scripts/jquery-3.3.1.min.js"></script>
<script src="static/scripts/checkout.js"></script>