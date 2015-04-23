package com.painter.listeners;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import com.painter.draw.DrawPanel;
import com.painter.draw.shapes.Fill;
import com.painter.draw.shapes.Line;
import com.painter.draw.shapes.Oval;
import com.painter.draw.shapes.Pencil;
import com.painter.draw.shapes.Shape;
import com.painter.draw.shapes.Square;
import com.painter.staticComponents.StaticVariables;

public class DrawPanelListener implements MouseListener, MouseMotionListener {

	public static DrawPanel panel;
	
	private Shape drawShape;
	
	public DrawPanelListener(DrawPanel dp) {
		panel = dp;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (drawShape instanceof Fill) {
			//fill shit
			System.out.println("fill: (" + e.getX() +  ", " + e.getY() + ")");
		} else {
			drawShape = getTmpShape(e.getX(), e.getY(), 2, 2);
			panel.setTmpShape(drawShape);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		drawShape.setX2(e.getX());
		drawShape.setY2(e.getY());
		
		List<Shape> shapes = panel.getShapes();
		shapes.add(drawShape);
		panel.setTmpShape(null);
		panel.setShapes(shapes);
		drawShape = null;
		
		panel.repaint();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if (drawShape instanceof Pencil) {
			((Pencil) drawShape).addPoint(new Point(e.getX(), e.getY()));
		} else {
			drawShape.setX2(e.getX());
			drawShape.setY2(e.getY());
		}
		panel.setTmpShape(drawShape);
		panel.repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	private Shape getTmpShape(int x, int y, int x2, int y2) {
		switch(StaticVariables.shapeType) {
		case "Square":
			return new Square(x, y, x2, y2, StaticVariables.shapeColor);
		case "Oval":
			return new Oval(x, y, x2, y2, StaticVariables.shapeColor);
		case "Pencil":
			return new Pencil(x, y, x2, y2, StaticVariables.shapeColor);
		case "Line":
			return new Line(x, y, x2, y2, StaticVariables.shapeColor);
		case "Fill":
			return new Fill(x, y, x2, y2, StaticVariables.shapeColor);
		default:
			return new Square(x, y, x2, y2, StaticVariables.shapeColor);
		}
	}
}