package com.test.models;

public class OrderModel {
	private int id;
	private Double total_price;
	private String status;
	private String date;
	private String payment_type;
	private int table_no;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPaymentType() {
		return payment_type;
	}
	public void setPaymentType(String payment_type) {
		this.payment_type = payment_type;
	}
	public int getTable_no() {
		return table_no;
	}
	public void setTable_no(int table_no) {
		this.table_no = table_no;
	}
	
	
			
}
