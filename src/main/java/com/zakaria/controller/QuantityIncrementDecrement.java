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

@WebServlet("/quan-inc-dec")
public class QuantityIncrementDecrement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try(PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			if (action != null && id > 0) {
				if (action.equals("inc")) {
					for (Cart c : cartList) {
						if (c.getId() == id) {
							c.setQuantity(c.getQuantity() + 1);
							break;
						}
					}
					response.sendRedirect("cart.jsp");
				}
				
				if (action.equals("dec")) {
					for (Cart c : cartList) {
						if (c.getId() == id && c.getQuantity() > 1) {
							c.setQuantity(c.getQuantity() - 1);
							break;
						}
					}
					response.sendRedirect("cart.jsp");
				}
			} else {
				response.sendRedirect("cart.jsp");
			}
		}
	}

}
