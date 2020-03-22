package com.design.behavioral.template;

public class Test {
	
	public static void main(String[] args) {
		HouseTmplate houseType = new WoodenHouse();
		
		// using template method
		houseType.buildHouse();
		System.out.println("********");
		
		houseType = new GlassHouse();
		houseType.buildHouse();
	}
}
