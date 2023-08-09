package com.zakaria.model;

public class Order extends Product{
	private int oId;
	private int cId;
	private int quantity;
	private String date;
	
	public Order () {}
	
	public Order(int oId, int cId, int quantity, String date) {
		super();
		this.oId = oId;
		this.cId = cId;
		this.quantity = quantity;
		this.date = date;
	}

	public Order(int cId, int quantity, String date) {
		super();
		this.cId = cId;
		this.quantity = quantity;
		this.date = date;
	}

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [oId=" + oId + ", cId=" + cId + ", quantity=" + quantity + ", date=" + date + "]";
	}
	
	
}
