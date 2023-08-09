package com.zakaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zakaria.model.Order;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement preStatement;
	private ResultSet rs;
	
	public OrderDao (Connection con) {
		this.con = con;
	}
	
	public boolean insertOrder(Order orderModel) {
		boolean isSuccess = false;
		
		try {
			query = "insert into orders (p_id, c_id, o_quantity, o_date) values (?, ?, ?, ?)";
			preStatement = con.prepareStatement(query);
			preStatement.setInt(1, orderModel.getId());
			preStatement.setInt(2, orderModel.getcId());
			preStatement.setInt(3, orderModel.getQuantity());
			preStatement.setString(4, orderModel.getDate());
			
			isSuccess = preStatement.executeUpdate() > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}
}
