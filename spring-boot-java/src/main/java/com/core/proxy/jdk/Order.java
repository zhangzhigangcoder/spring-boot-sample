package com.core.proxy.jdk;

public class Order {
	
	private String orderNo;
	
	private String orderName;
	
	public Order(String orderNo, String orderName) {
		super();
		this.orderNo = orderNo;
		this.orderName = orderName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[orderNo=");
		builder.append(orderNo);
		builder.append(", orderName=");
		builder.append(orderName);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
