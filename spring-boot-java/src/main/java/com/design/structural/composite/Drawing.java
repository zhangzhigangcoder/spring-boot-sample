package com.design.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern Leaf Objects
 * 
 * @author zhangzhigang
 *
 */
public class Drawing implements Shape {
	
	// collection of Shapes
	private List<Shape> shapes = new ArrayList<>();

	@Override
	public void draw(String fillColor) {
		shapes.forEach(s -> s.draw(fillColor));
	}
	
	/**
	 * adding shape to drawing
	 * @param s
	 */
	public void add(Shape s) {
		this.shapes.add(s);
	}
	
	/**
	 * removing shape from drawing
	 * @param s
	 */
	public void remove(Shape s) {
		this.shapes.remove(s);
	}
	
	public void clear() {
		System.out.println("Clearing all the shapes from drawing");
		this.shapes.clear();
	}
	
}
