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
<title>Register Now</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>


	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center"><b>Be A Customer!</b></div>
			<div class="card-body">

				<form action="add-new-customer" method="post">
					<div class="form-group mb-3">
						<label class="form-label">Full Name</label>
						<input type="text" class="form-control"
							name="full-name" placeholder="Full name" required>
					</div>
					
					<div class="form-group mb-3">
						<label class="form-label">Email address</label> 
						<input type="email" class="form-control" name="email" 
						placeholder="Enter your email" aria-describedby="emailHelp" required>
						<div id="emailHelp" class="form-text">We'll never share your
							email with anyone else.</div>
					</div>
					
					<div class="form-group mb-3">
						<label class="form-label">Password</label>
						<input type="password" class="form-control" name="password" 
						placeholder="*************" aria-describedby="passwordHelpBlock" required>
						<div id="passwordHelpBlock" class="form-text">Your password
							must be 6-10 characters long.</div>
					</div>
					
					<div class="mb-3 form-check">
						<input type="checkbox" class="form-check-input" id="exampleCheck1" required>
						<label class="form-check-label" for="exampleCheck1">I agree</label>
					</div>
					<button type="submit" class="btn btn-primary">Register</button>
				</form>
			</div>
		</div>
	</div>



	<%@include file="includes/footer.jsp"%>
</body>
</html>
