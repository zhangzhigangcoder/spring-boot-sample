package com.design.structural.composite;

/**
 * Composite Pattern Leaf Objects
 * 
 * @author zhangzhigang
 *
 */
public class Circle implements Shape {

	@Override
	public void draw(String fillColor) {
		System.out.println("Drawing Circle with color: " + fillColor);
	}

}
