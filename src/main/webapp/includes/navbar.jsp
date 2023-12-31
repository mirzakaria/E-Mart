
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
	<div class="container">
		<a class="navbar-brand" href="index.jsp"> <img
			src="product-images/brand.png" width="30"
			height="30" class="d-inline-block align-top" alt=""> E-Mart
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav ms-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart <span class="badge bg-dark px-1">${cartList.size()}</span> </a></li>
				<%
				if (auth != null) {
				%>
				<li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>