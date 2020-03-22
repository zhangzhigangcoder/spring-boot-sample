package com.design.structural.flyweight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.design.structural.flyweight.ShapeFactory.ShapeType;

public class Test {

	
	
	public static void main(String[] args) {
		new DrawingClient(500, 600);
	}
	
	static class DrawingClient extends JFrame {

		private static final long serialVersionUID = -2327950988464025777L;
		
		private final int width;
		
		private final int height;
		
		private static final ShapeType shapes[] = {ShapeType.LINE,ShapeType.OVAL_FILL,ShapeType.OVAL_NOFILL};
		
		private static final Color colors [] = {Color.red, Color.GREEN, Color.yellow};
		
		public DrawingClient(int width, int height) {
			this.width = width;
			this.height = height;
			Container contentPane = getContentPane();
			JButton startButton = new JButton("Draw");
			final JPanel panel = new JPanel();
			
			contentPane.add(panel, BorderLayout.CENTER);
			contentPane.add(startButton, BorderLayout.SOUTH);
			setSize(width, height);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
			startButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Graphics g = panel.getGraphics();
					for (int i=0; i<20; i++) {
						Shape shape = ShapeFactory.newInstance(getRandomShape());
						shape.draw(g, getRandomX(), getRandomY(), getRandomWidth(), getRandomHeight(), getRandomColor());
					}
				}
			});
		}
		
		private ShapeType getRandomShape() {
			return shapes[(int) (Math.random() * shapes.length)];
		}

		private int getRandomX() {
			return (int) (Math.random() * width);
		}

		private int getRandomY() {
			return (int) (Math.random() * height);
		}

		private int getRandomWidth() {
			return (int) (Math.random() * (width / 10));
		}

		private int getRandomHeight() {
			return (int) (Math.random() * (height / 10));
		}
		
		private Color getRandomColor() {
			return colors[(int) (Math.random() * colors.length)];
		}
		
	}
	
	
}
