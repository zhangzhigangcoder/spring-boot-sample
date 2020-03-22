package com.design.behavioral.strategy;

public class CreditCardStrategy implements PaymentStrategy {
	
	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;
	
	
	
	public String getName() {
		return name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public String getDateOfExpiry() {
		return dateOfExpiry;
	}

	public CreditCardStrategy(String name, String cardNumber, String cvv, String dateOfExpiry) {
		this.name = name;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.dateOfExpiry = dateOfExpiry;
	}

	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid with credit/debit card");
	}
	
	
}
