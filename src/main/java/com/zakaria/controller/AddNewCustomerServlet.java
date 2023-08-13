package com.zakaria.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.zakaria.connection.DBConnection;
import com.zakaria.dao.CustomerDao;
import com.zakaria.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add-new-customer")
public class AddNewCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String name = request.getParameter("full-name");
//		out.println(name);
		String email = request.getParameter("email");
//		out.println(email);
		String password = request.getParameter("password");
//		out.println(password);
		
		try {
			Customer customer = new Customer();
			customer.setName(name);
			customer.setEmail(email);
			customer.setPassword(password);
			
			CustomerDao cDao = new CustomerDao(DBConnection.getConnection());
			cDao.addNewCustomer(customer);
			
			response.sendRedirect("login.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
