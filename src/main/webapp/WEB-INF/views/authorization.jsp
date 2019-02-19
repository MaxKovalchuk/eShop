<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file = "../../static/inc/header.jsp"%>

<body>
	<center class='content'>
		<form action='' method = 'post'>
			<table>
				<tr>
					<td>Login</td>
					<td><input type='text' name='login' value = '${login}'/></td>
					<td></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type='submit' value='send' /></td>
				</tr>
				<tr>
					<td></td>
					<td>${errorText }</td>
				</tr>
			</table>
		</form>
	</center>
</body>
<%@include file = "../../static/inc/footer.jsp"%>
