package com.zakaria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.zakaria.connection.DBConnection;
import com.zakaria.dao.CustomerDao;
import com.zakaria.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/customer-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("login-email");
//		out.println(email);
		String password = request.getParameter("login-password");
//		out.println(password);
		try {
			CustomerDao customerDao = new CustomerDao(DBConnection.getConnection());
			
			Customer customer = customerDao.customerLogin(email, password);
			
			if (customer != null) {
				request.getSession().setAttribute("auth", customer);
				response.sendRedirect("index.jsp");
			} else {
				out.print("Wrong credentials.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
