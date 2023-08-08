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

@WebServlet("/remove-from-cart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try (PrintWriter out = response.getWriter()){
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			if (cartList != null) {
				for (Cart cart : cartList) {
					if (cart.getId() == id) {
						cartList.remove(cart);
						break;
					}
				}
				response.sendRedirect("cart.jsp");
			} else {
				response.sendRedirect("cart.jsp");
			}
			
		}
	}

}
