package com.zakaria.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String url = "jdbc:mysql://localhost:3306/e-mart";
	private static String username = "root";
	private static String password = "zakaria";
	
	private static Connection con = null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (con == null) {
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
		}
		
		return con;
	}
}
