package com.painter.draw;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.painter.draw.shapes.Shape;
import com.painter.listeners.DrawPanelListener;

public class DrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private DrawPanelListener dpl;
	private List<Shape> shapes = new ArrayList<>();
	
	private Shape tmpShape;
	
	public DrawPanel() {
		//init color
		setOpaque(true);
		setBackground(Color.LIGHT_GRAY);
		
		//listeners
		dpl = new DrawPanelListener(this);
		addMouseListener(dpl);
		addMouseMotionListener(dpl);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Shape s : shapes) {
			if (s != null) {
				s.render(g);
			}
		}
		
		if (tmpShape != null) {
			tmpShape.render(g);
		}
	}
	public List<Shape> getShapes() {
		return shapes;
	}
	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
	public Shape getTmpShape() {
		return tmpShape;
	}
	public void setTmpShape(Shape tmpShape) {
		this.tmpShape = tmpShape;
	}
}