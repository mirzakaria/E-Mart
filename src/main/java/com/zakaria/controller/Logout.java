package com.zakaria.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/logout")
public class Logout extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("auth") != null) {
			request.getSession().removeAttribute("auth");
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}

}
