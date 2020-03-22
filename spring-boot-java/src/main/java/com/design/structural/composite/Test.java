package com.design.structural.composite;

public class Test {

	public static void main(String[] args) {
		Shape tri = new Triangle();
		Shape tri2 = new Triangle();
		Shape cir = new Circle();
		
		Drawing drawing = new Drawing();
		drawing.add(tri);
		drawing.add(tri2);
		drawing.add(cir);
		
		drawing.draw("Red");
		
		drawing.clear();
		
		drawing.add(tri2);
		drawing.add(cir);
		drawing.draw("Green");
	}
	
	
}
