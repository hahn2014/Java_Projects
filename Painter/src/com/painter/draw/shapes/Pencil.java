package com.painter.draw.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Pencil extends Shape {

	private List<Point> points = new ArrayList<>();
	
	public Pencil(int x, int y, int x2, int y2, Color c) {
		super(x, y, x2, y2, c);
		
		points.add(new Point(x, y));
		
	}
	@Override
	public void render(Graphics g) {
		g.setColor(getC());
		for(Point p : points) {
			g.fillOval((int)(p.getX()), (int)(p.getY()), 1, 1);
		}
	}
	public void addPoint(Point p) {
		points.add(p);
	}
}