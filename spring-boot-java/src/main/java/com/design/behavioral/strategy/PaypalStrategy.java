package com.design.behavioral.strategy;

public class PaypalStrategy implements PaymentStrategy {
	
	private String emailId;
	private String password;
	
	public String getEmailId() {
		return emailId;
	}

	public String getPassword() {
		return password;
	}

	public PaypalStrategy(String emailId, String password) {
		this.emailId = emailId;
		this.password = password;
	}

	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid using Paypal");
	}
	
	
}
