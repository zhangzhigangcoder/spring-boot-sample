package com.design.structural.composite;

/**
 * Composite Pattern Leaf Objects
 * 
 * @author zhangzhigang
 *
 */
public class Triangle implements Shape {

	@Override
	public void draw(String fillColor) {
		System.out.println("Drawing Triangle with color: " + fillColor);
	}

}
