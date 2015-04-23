package com.painter.draw.shapes;
import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	public Line(int x, int y, int x2, int y2, Color c) {
		super(x, y, x2, y2, c);
		
	}
	@Override
	public void render(Graphics g) {
		g.setColor(getC());
		g.drawLine(getX(),  getY(),  getX2(),  getY2());
	}
}