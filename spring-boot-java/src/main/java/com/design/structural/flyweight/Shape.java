package com.design.structural.flyweight;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Interface
 * @author zhangzhigang
 *
 */
public interface Shape {

	void draw(Graphics g, int x, int y, int width, int height, Color color);
}
