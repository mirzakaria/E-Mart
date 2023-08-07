<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@page import="com.zakaria.model.*"%>
<%@page import="java.util.*"%>
<%
Customer auth = (Customer) request.getSession().getAttribute("auth");
if (auth != null) {
	response.sendRedirect("index.jsp");
}

ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cartList != null) {
	request.setAttribute("cartList", cartList);
}

%>


<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
	<div class="card w-50 mx-auto my-5">
		<div class="card-header text-center">User Login</div>
			<div class="card-body">

				<form action="customer-login" method="post">

					<div class="form-group">
						<label>E-mail Address</label> <input type="email"
							class="form-control" name="login-email"
							placeholder="Enter your email" required>
					</div>
					<br>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="login-password" placeholder="********"
							required>
					</div>
					<br>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>

				</form>

			</div>
	</div>
</div>



<%@include file="includes/footer.jsp" %>
</body>
</html>
