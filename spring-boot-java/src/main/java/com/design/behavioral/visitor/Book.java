package com.design.behavioral.visitor;

public class Book implements ItemElement {

	private int price;
	
	private String isbnNumber;
	
	public Book(int price, String isbn) {
		this.price = price;
		this.isbnNumber = isbn;
	}
	
	
	public int getPrice() {
		return price;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}

}
