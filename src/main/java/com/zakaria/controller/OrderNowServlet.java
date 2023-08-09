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

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			Customer auth = (Customer) request.getSession().getAttribute("auth");
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			if(auth != null) {
				int proId = Integer.parseInt(request.getParameter("id"));
				int proQuantity = Integer.parseInt(request.getParameter("quantity"));
				
				if (proQuantity < 1) {
					proQuantity = 1;
				}
				
				
				Order orderModel = new Order();
				orderModel.setId(proId);
				orderModel.setcId(auth.getId());
				orderModel.setQuantity(proQuantity);
				orderModel.setDate(formatter.format(date));
				
				OrderDao oDao = new OrderDao(DBConnection.getConnection());
				boolean result = oDao.insertOrder(orderModel);
				
				if (result) {
					ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					
					if (cartList != null) {
						for (Cart cart : cartList) {
							if (cart.getId() == orderModel.getId()) {
								cartList.remove(cart);
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");
				} else {
					out.println("order failed");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
			
	}

}
