<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.zakaria.model.*"%>
<%@page import="com.zakaria.dao.*"%>
<%@page import="com.zakaria.connection.DBConnection"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
Customer auth = (Customer) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("customer", auth);
}


ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

if (cartList != null) {
	request.setAttribute("cartList", cartList);
}

ProductDao pDao = new ProductDao(DBConnection.getConnection());
List<Product> products = pDao.getAllProducts();
%>




<!DOCTYPE html>
<html>
<head>
<title>Welcome to E-Shopping</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header text-center my-2"><b>All Products</b></div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card h-100 w-100">
					<img class="card-img-top" src="product-images/<%=p.getImages() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Price: ৳<%=p.getPrice() %></h6>
						<h6 class="category">Category: <%=p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add to Cart</a> 
							<a class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no proucts");
			}
			%>

		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>
