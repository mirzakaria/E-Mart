package com.zakaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zakaria.model.Customer;

public class CustomerDao {
	private Connection con;
	private String query;
	
	private PreparedStatement preStatement;
	private ResultSet rs;
	
	public CustomerDao(Connection con) {
		this.con = con;
	}
	
	public Customer customerLogin (String email, String password) {
		Customer customer = null;
		
		try {
			query = "select * from customers where email = ? and password = ?";
			preStatement = this.con.prepareStatement(query);
			preStatement.setString(1, email);
			preStatement.setString(2, password);
			
			rs = preStatement.executeQuery();
			
			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt("c_id"));
				customer.setName(rs.getString("c_name"));
				customer.setEmail(rs.getString("email"));
				
			} 

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		
		return customer;
	}
	
	
}
