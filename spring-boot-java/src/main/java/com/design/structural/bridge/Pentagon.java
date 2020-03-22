package com.design.structural.bridge;

public class Pentagon extends Shape {

	public Pentagon(Color c) {
		super(c);
	}
	
	@Override
	void applyColor() {
		System.out.println("Pentagon filled with color");
		color.applyColor();
	}
	
}
