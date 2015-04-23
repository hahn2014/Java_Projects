package com.painter.draw.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape {
	public Square(int x, int y, int x2, int y2, Color c) {
		super(x, y, x2, y2, c);
	}
	@Override
	public void render(Graphics g) {
		g.setColor(getC());
		g.fillRect(getX(), getY(), calcWidth(), calcHeight());
	}
}