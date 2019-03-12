<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file="../../static/inc/header.jsp"%>
<center class='content'>
	<div class='Concten_divider'>
	<h1>${sessionScope.user.name}</h1>
	 <form action='' method='post'>  
		<ul>
			<li><input class='input' type='text' name='changeName'/><input
				class='input' type='submit' value='Change name' />${result1}</li>
			<li><input class='input'  type='text' name='changeAddress'/><input
				class='input' type='submit' value='Change address' />${result2}</li>
		</ul>
	  </form>
	</div>
</center>
<%@include file="../../static/inc/footer.jsp"%>