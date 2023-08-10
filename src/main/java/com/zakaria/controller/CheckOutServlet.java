package com.zakaria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.zakaria.connection.DBConnection;
import com.zakaria.dao.OrderDao;
import com.zakaria.model.Cart;
import com.zakaria.model.Customer;
import com.zakaria.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			Customer auth = (Customer) request.getSession().getAttribute("auth");
			
			if (auth != null && cartList != null) {
				for(Cart cart : cartList) {
					Order order = new Order();
					order.setId(cart.getId());
					order.setcId(auth.getId());
					order.setQuantity(cart.getQuantity());
					order.setDate(formatter.format(date));
					
					OrderDao oDao = new OrderDao(DBConnection.getConnection());
					boolean isSuccess = oDao.insertOrder(order);
					
					if (!isSuccess) break;
				}
				
				cartList.clear();
				response.sendRedirect("orders.jsp");
			} else {
				if (auth == null) response.sendRedirect("login.jsp");
				else response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
