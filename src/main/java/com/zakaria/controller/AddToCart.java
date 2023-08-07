package com.zakaria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.zakaria.model.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ArrayList<Cart> cartList = new ArrayList<>();
		
		int id = Integer.parseInt(request.getParameter("id"));
		Cart cartItem =  new Cart();
		cartItem.setId(id);
		cartItem.setQuantity(1);
		
		HttpSession session = request.getSession();
		ArrayList<Cart> sessionCartList = (ArrayList<Cart>) session.getAttribute("cart-list");
		
		if (sessionCartList == null) {
			cartList.add(cartItem);
			session.setAttribute("cart-list", cartList);
			response.sendRedirect("index.jsp");
		} else {
			cartList = sessionCartList;
			boolean exist = false;
			for(Cart c : cartList) {
				if (c.getId() == id) {
					exist = true;
					out.println("<h3 style='color:crimson; text-align:center'>This product already exist in the cart.");
					out.println("<a href='cart.jsp'>Go to Cart</a>");
					out.print("</h3>");
				}
			}
			
			if (!exist) {
				cartList.add(cartItem);
				response.sendRedirect("index.jsp");
			}
		}
		
	}
}
