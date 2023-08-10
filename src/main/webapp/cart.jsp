<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.zakaria.model.*"%>
<%@page import="com.zakaria.dao.*"%>
<%@page import="com.zakaria.connection.*"%>
<%@page import="java.util.*"%>

<%
Customer auth = (Customer) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("customer", auth);
}

ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartItem = null;

if (cartList != null) {
	ProductDao pDao = new ProductDao(DBConnection.getConnection());
	cartItem = pDao.getCartItems(cartList);
	request.setAttribute("cartList", cartList);
	int total = pDao.getTotalPrice(cartList);
	request.setAttribute("total", total);
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
<%@include file="includes/header.jsp"%>

<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	font-size: 20px;
}
</style>

</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Total price: ৳ ${ (total > 0) ? total : 0 }</h3>
			<a class="mx-3 btn btn-primary" href="check-out">Check out</a>
		</div>

		<table class="table table-light text-center">
			<thead>
				<tr>
					<th>Product name</th>
					<th>Category</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			if (cartList != null) {
				for (Cart pro : cartItem) {
			%>
					<tr>
					<td><%=pro.getName() %></td>
					<td><%=pro.getCategory() %></td>
					
					<td>
						<form method="post" action="" class="form-inline">
							<input type="hidden" name="id" value="<%=pro.getId() %>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-decre" href="quan-inc-dec?action=dec&id=<%=pro.getId()%>"><i class="fas fa-minus-square"></i></a>
								<input type="number" name="quantity" class="form-control text-center" value="<%=pro.getQuantity()%>" readonly>
								<a class="btn btn-sm btn-incre" href="quan-inc-dec?action=inc&id=<%=pro.getId()%>"><i class="fas fa-plus-square"></i></a>
							</div>
						</form>
					</td>

					<td>৳<%=pro.getPrice() %></td>
					<td>
					<form method="post" action="order-now" class="form-inline">
						<input type="hidden" name="id" value="<%=pro.getId() %>" class="form-input">
						<input type="hidden" name="quantity" value="<%=pro.getQuantity() %>" class="form-input">
						<button type="submit" class="btn btn-sm btn-success">Buy</button>
						<a class="btn btn-sm btn-danger" href="remove-from-cart?id=<%= pro.getId() %>">Remove</a>
					</form>
					</td>
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
