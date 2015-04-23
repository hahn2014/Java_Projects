package com.painter.draw.shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
	
	private int x, y, x2, y2;
	private Color c;
	
	public Shape(int x, int y, int x2, int y2, Color c) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.c = c;
	}
	public abstract void render(Graphics g);
	
	public int calcWidth() {
		return x2 - x;
	}
	public int calcHeight() {
		return y2 - y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
}