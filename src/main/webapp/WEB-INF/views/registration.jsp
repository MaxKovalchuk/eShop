<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file = "../../static/inc/header.jsp"%>

<c:if test="${form}">
	<center class='content'>
		<form action="" method="post">
			<table>
				<td>
					<table>
						<tr>
							<td>Login</td>
							<td><input type='email' name='login'
								value='${login}' /></td>
						</tr>
						<tr>
							<td>Name</td>
							<td><input type='text' name='name'
								value='${name}' /></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type='password' name='password' /></td>
						</tr>
						<tr>
							<td>Retype password</td>
							<td><input type='password' name='repassword' /></td>
						</tr>
						<tr>
							<td>Age</td>
							<td><input type='number' name='age'
								value='${age}' /></td>
						</tr>
						<tr>
							<td>Gender</td>
							<td>Male <input type='radio' name='gender' value='M' ${genderM}/>
								Female <input type='radio' name='gender' value='F' ${genderF}/>
							</td>
						</tr>
						<tr>
							<td>Address</td>
							<td><select name="address">
									<option value='1' ${address1 }>Luganda]</option>
									<option value='2'${address2 }>Donbass</option>
									<option value='3'${address3 }>Dom</option>
							</select></td>
						</tr>
						<tr>
							<td>Comment</td>
							<td><textarea cols='10' rows='5' name='comment'
									value='${comment}'>
								</textarea></td>
						</tr>
						<tr>
							<td>I agree to install Amigo Broswer</td>
							<td><input name='agree' type='checkbox'
								<c:if test = "${agree != null}">checked</c:if>></td>
						</tr>
						<tr>
							<td><input type='submit' value='send'></td>
						</tr>
					</table>
				</td>
				<td><c:if test="${isError}">${errorText}</c:if></td>
			</table>
		</form>
	</center>
</c:if>
<%@include file = "../../static/inc/footer.jsp"%>