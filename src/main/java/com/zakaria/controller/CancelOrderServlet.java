package com.zakaria.controller;

import java.io.IOException;

import com.zakaria.connection.DBConnection;
import com.zakaria.dao.OrderDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			if (id > 0) {
				OrderDao oDao = new OrderDao(DBConnection.getConnection());
				oDao.cancelOrder(id);
				response.sendRedirect("orders.jsp");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
