package com.design.structural.flyweight;

import java.util.HashMap;

/**
 * Factory
 * 
 * @author zhangzhigang
 *
 */
public class ShapeFactory {
	
	private static final HashMap<ShapeType, Shape> shapes = new HashMap<>();
	
	public static Shape newInstance(ShapeType type) {
		Shape shape = shapes.get(type);
		if (null == shape) {
			if (ShapeType.OVAL_FILL.equals(type)) {
				shape = new Oval(true);
			} else if (ShapeType.OVAL_NOFILL.equals(type)) {
				shape = new Oval(false);
			} else if (ShapeType.LINE.equals(type)) {
				shape = new Line();
			}
			shapes.put(type, shape);
		}
		return shape;
	}
	
	public static enum ShapeType {
		OVAL_FILL,OVAL_NOFILL,LINE;
	}
}
