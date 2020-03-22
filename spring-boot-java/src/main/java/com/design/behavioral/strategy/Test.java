package com.design.behavioral.strategy;

public class Test {
	
	public static void main(String[] args) {
		
		ShoppingCart cart = new ShoppingCart();
		
		Item item1 = new Item("1234", 10);
		Item item2 = new Item("5678", 40);
		
		 cart.addItem(item1);
		 cart.addItem(item2);
		
		 // pay by paypal
		 cart.pay(new PaypalStrategy("myemail@163.com", "mypwd"));
		 // pay by credit card
		 cart.pay(new CreditCardStrategy("Pankaj Kumar", "123456789","789","12/15"));
		
	}
}
