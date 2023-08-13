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
	
	public boolean addNewCustomer(Customer c) {
		boolean isAdded = false;
		
		try {
			query = "insert into customers (c_name, email, password) values (?, ?, ?)";
			preStatement = con.prepareStatement(query);
			preStatement.setString(1, c.getName());
			preStatement.setString(2, c.getEmail());
			preStatement.setString(3, c.getPassword());
			
			isAdded = preStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return false;
	}
	
	
}
