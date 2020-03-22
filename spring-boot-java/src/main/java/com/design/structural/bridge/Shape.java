package com.design.structural.bridge;

public abstract class Shape {
	
	// composition - implementor
	protected Color color;
	
	// constructor with implementor as input argument
	public Shape(Color c) {
		this.color = c;
	}
	
	abstract void applyColor();
}
