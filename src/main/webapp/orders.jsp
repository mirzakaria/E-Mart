<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.zakaria.connection.*"%>
<%@page import="com.zakaria.model.*"%>
<%@page import="com.zakaria.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%
Customer auth = (Customer) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("customer", auth);
}


ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

List<Order> list = null;
if (auth == null) {
	response.sendRedirect("login.jsp");
	
} else {
	request.setAttribute("customer", auth);
	OrderDao oDao = new OrderDao(DBConnection.getConnection());
	 list = oDao.getAllOrder(auth.getId());
}

%>


<!DOCTYPE html>
<html>
<head>
<title>Orders</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header text-center my-2">
			<b>All Orders</b>
		</div>

		<table class="table table-light text-center">
			<thead>
			<tr>
				<th>#</th>
				<th>Placing date</th>
				<th>Product name</th>
				<th>Category</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
			</thead>
			<tbody>
				<%
				if (list != null) {
					int count = 0;
					for (Order order : list) {
						
				%>
				<tr>
					<td><%=++count %></td>
					<td><%=order.getDate() %></td>
					<td><%=order.getName() %></td>
					<td><%=order.getCategory() %></td>
					<td><%=order.getQuantity() %></td>
					<td><%=order.getPrice() %></td>	
					<td><a class="btn btn-danger" href="cancel-order?id=<%=order.getoId() %>">Cancel Order</a></td>
				</tr>
				<%
					}
				}
				%>
				
			</tbody>
		</table>
	</div>



	<%@include file="includes/footer.jsp"%>
</body>
</html>
