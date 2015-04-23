import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class GraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static ArrayList<Circle> nodeList = new ArrayList<Circle>();
	public static ArrayList<Edge> edgeList = new ArrayList<Edge>();
	public static ArrayList<ArrayList<Boolean>> adjacency = new ArrayList<ArrayList<Boolean>>();
	public static int row = 20;
	public static int col = 20;
	public ArrayList<String> getConnectedLabels(String label) {
		ArrayList<String> toReturn = new ArrayList<String>();
		int b = getIndex(label);
		for (int a = 0; a < adjacency.size(); a++) {
			if (adjacency.get(b).get(a) && (nodeList.get(a).getLabel().equals(label) == false)) {
				toReturn.add(nodeList.get(a).getLabel());
			}
		}
		return toReturn;
	}
	//add values to edgeList
	public void addEdge(Circle newfirst, Circle newsecond, String newlabel) {
		// adds the new edge to the ArrayList
		edgeList.add(new Edge(newfirst, newsecond, newlabel));
		// index used in am updating
		int firstIndex = 0;
		int secondIndex = 0;
		for (int i = 0; i < nodeList.size(); i++) {
			if (newfirst.equals(nodeList.get(i))) {
				firstIndex = i;
			}
			if (newsecond.equals(nodeList.get(i))) {
				secondIndex = i;
			}
		}
		//reset it yo
		adjacency.get(firstIndex).set(secondIndex, true);
		adjacency.get(secondIndex).set(firstIndex, true);
		printDaMatrix();
	}
	public void addNode(int newx, int newy, String newlabel) {
		nodeList.add(new Circle(newx, newy, newlabel));
		adjacency.add(new ArrayList<Boolean>());
		for (int i = 0; i < adjacency.size() - 1; i++) {
			adjacency.get(i).add(false);
		}
		for (int i = 0; i < adjacency.size(); i++) {
			adjacency.get(adjacency.size() - 1).add(false);
		}
		printDaMatrix();
	} //check if where you just clicked is a circle
	public Circle checkForCircle(int x, int y) {
		for (int i = 0; i < nodeList.size(); i++) {
			Circle c = nodeList.get(i);
			int a = c.getX() - x;
			int b = c.getY() - y;
			double d = Math.pow(a, 2) + Math.pow(b, 2);
			if (d <= c.getRadius()) {
				return c;
			}
			Math.pow(a, b);
		}
		return null;
	}
	public Circle getNode(String s) {
		for (int a = 0; a < nodeList.size(); a++) {
			Circle c = nodeList.get(a);
			if (s.equals(c.getLabel())) {
				return c;
			}
		}
		return null;
	}
	public int getIndex(String s) {
		for (int a = 0; a < nodeList.size(); a++) {
			Circle c = nodeList.get(a);
			if (s.equals(c.getLabel())) {
				return a;
			}
		}
		return -1;
	}
	public boolean nodeExists(String s) {
		for (int a = 0; a < nodeList.size(); a++) {
			if (s.equals(nodeList.get(a).getLabel())) {
				return true;
			}
		}
		return false;
	}
	//print everything every click and such
	public void printDaMatrix() {
		for (int i = 0; i < adjacency.size(); i++) {
			for (int j = 0; j < adjacency.get(i).size(); j++) {
				//sysout a table with all the info and stuff
				System.out.print(adjacency.get(i).get(j) + "\t");
			}
		}
		System.out.println("");
	}
	//draw everything to the frame
	public void paintComponent(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < 1000; i += row) {
			g.drawLine(i, 0, i, 1000);
			for (int j = 0; j < 1000; j += col) {
				g.drawLine(0, j, 1000, j);
			}
		}
		g.setColor(Color.BLUE);
		for (int a = 0; a < nodeList.size(); a++) {
			Circle c = nodeList.get(a);
			if (c.getHighlighted()) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.BLUE);
			}
			g.drawOval(c.getX() - c.getRadius(), c.getY() - c.getRadius(), c.getRadius() * 2, c.getRadius() * 2);
			g.drawString(c.getLabel(), c.getX(), c.getY());
		}
		for (int a = 0; a < edgeList.size(); a++) {
			Edge e = edgeList.get(a);
			g.drawLine(e.getFirst().getX(), e.getFirst().getY(), e.getSecond().getX(), e.getSecond().getY());
			g.drawString(e.getLabel(), (e.getFirst().getX() + e.getSecond().getX()) / 2, (e.getFirst().getY() + e.getSecond().getY()) / 2);
		}
	}
}