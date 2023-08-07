package com.zakaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zakaria.model.Cart;
import com.zakaria.model.Product;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement preStatement;
	private ResultSet rs;

	public ProductDao(Connection con) {
		this.con = con;
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();

		try {
			query = "select * from products";
			preStatement = this.con.prepareStatement(query);
			rs = preStatement.executeQuery();

			while (rs.next()) {
				Product dummy = new Product();
				dummy.setId(rs.getInt("p_id"));
				dummy.setName(rs.getString("p_name"));
				dummy.setCategory(rs.getString("category"));
				dummy.setPrice(rs.getInt("price"));
				dummy.setImages(rs.getString("image"));

				products.add(dummy);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	public List<Cart> getCartItems(ArrayList<Cart> sessionCart) {
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if (sessionCart.size() > 0) {
				for (Cart item : sessionCart) {
					query = "select * from products where p_id = ?";
					preStatement = con.prepareStatement(query);
					preStatement.setInt(1, item.getId());
					
					rs = preStatement.executeQuery();
					
					while (rs.next()) {
						Cart dummy = new Cart();
						dummy.setId(rs.getInt("p_id"));
						dummy.setName(rs.getString("p_name"));
						dummy.setCategory(rs.getString("category"));
						dummy.setPrice(rs.getInt("price") * item.getQuantity());
						
						dummy.setQuantity(item.getQuantity());
						products.add(dummy);
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public int getTotalPrice(ArrayList<Cart> cartList) {
		int sum = 0;
		
		try {
			if (cartList != null) {
				for (Cart cart : cartList) {
					query = "select * from products where p_id = ?";
					preStatement = con.prepareStatement(query);
					preStatement.setInt(1, cart.getId());
					rs = preStatement.executeQuery();
					
					while (rs.next()) {
						sum += rs.getInt("price")*cart.getQuantity();
					}
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sum;
	}
}
