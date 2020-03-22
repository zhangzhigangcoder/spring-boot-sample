package com.design.structural.bridge;

public class Triangle extends Shape {

	public Triangle(Color c) {
		super(c);
	}
	
	@Override
	void applyColor() {
		System.out.println("Triangle filled with color");
		color.applyColor();
	}
	
}
