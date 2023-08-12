package com.zakaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Order> getAllOrder(int id) {
		List<Order> list = new ArrayList<Order>();
		
		try {
			query = "select * from orders inner join products on orders.p_id = products.p_id where c_id = ? order by o_date desc";
			preStatement = con.prepareStatement(query);
			preStatement.setInt(1, id);
			rs = preStatement.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setoId(rs.getInt("o_id"));
				order.setDate(rs.getString("o_date"));
				order.setName(rs.getString("p_name"));
				order.setCategory(rs.getString("category"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setPrice(rs.getInt("price") * rs.getInt("o_quantity"));
				
				list.add(order);
			}
							
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean cancelOrder(int id) {
		boolean isDeleted = false;
		try {
			query = "delete from orders where o_id = ?";
			preStatement = con.prepareStatement(query);
			preStatement.setInt(1, id);
			
			isDeleted = preStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isDeleted;
	}
}
