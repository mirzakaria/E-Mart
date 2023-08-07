<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="com.zakaria.model.Customer" %>

<%
Customer auth = (Customer) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("customer", auth);
}
%>
    
    
<!DOCTYPE html>
<html>
<head>
  <title>Orders</title>
  <%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<h1>Hello, world!</h1>



<%@include file="includes/footer.jsp" %>
</body>
</html>