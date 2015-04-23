import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Graph implements ActionListener, MouseListener, MouseMotionListener {
	//initialize and setup all the things for the program
	JFrame frame = new JFrame("Graph Creator 3 - Bryce Hahn");
	JButton nodeButton = new JButton("Create");
	JButton edgeButton = new JButton("Select");
	JButton checkConnecting = new JButton("Check Connection");
	JButton  salesman= new JButton("Shortest Path Finder");
	JTextField labelTF = new JTextField("A");
	JTextField aTF = new JTextField("");
	JTextField bTF = new JTextField("");
	JTextField salesmanStartTF = new JTextField("A");
	Container west = new Container();
	Container south = new Container();
	Container east = new Container();
	GraphPanel panel = new GraphPanel();
	final int NODE_SELECTED = 0;
	final int EDGE_FIRST = 1;
	final int EDGE_SECOND = 2;
	final int NULL = 3;
	int currentState = NULL;
	int selected = 0;
	boolean running = false;
	Circle first = null;
	ArrayList<ArrayList<Circle>> completed = new ArrayList<ArrayList<Circle>>();
	
	public Graph() {//set the frame up yo
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		west.setLayout(new GridLayout(3, 1));
		west.add(nodeButton);
		nodeButton.addActionListener(this); //allows it to know when you click a button
		nodeButton.setBackground(Color.LIGHT_GRAY);
		west.add(edgeButton);
		edgeButton.addActionListener(this); //allows it to know when you click a button
		edgeButton.setBackground(Color.LIGHT_GRAY);
		west.add(labelTF);
		frame.add(west, BorderLayout.WEST); //add the container WEST to the frame on the left
		east.setLayout(new GridLayout(3, 1));
		east.add(aTF);
		east.add(bTF);
		east.add(checkConnecting);
		checkConnecting.addActionListener(this);
		frame.add(east, BorderLayout.EAST);
		south.setLayout(new GridLayout(1, 2));
		south.add(salesmanStartTF);
		south.add(salesman);
		salesman.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		frame.add(panel, BorderLayout.CENTER); //add the panel from GraphPanel class in the center
		panel.addMouseListener(this);
		frame.setVisible(true);
		frame.repaint();
		running = true;
	}
	public static void main(String[] args) {
		new Graph();
	}
	public void actionPerformed(ActionEvent event) { //when you click a button, come here
		if (event.getSource().equals(nodeButton)) {
			if (selected != 1) {
				nodeButton.setBackground(Color.GREEN);
				nodeButton.setForeground(Color.RED);
				edgeButton.setBackground(Color.LIGHT_GRAY);
				edgeButton.setForeground(Color.BLACK);
				currentState = NODE_SELECTED;
				selected = 1;
			} else {
				nodeButton.setBackground(Color.LIGHT_GRAY);
				nodeButton.setForeground(Color.BLACK);
				edgeButton.setBackground(Color.LIGHT_GRAY);
				edgeButton.setForeground(Color.BLACK);
				currentState = NULL;
				selected = 0;
			}
		}
		if (event.getSource().equals(edgeButton)) {
			if (selected != 2) {
				nodeButton.setBackground(Color.LIGHT_GRAY);
				nodeButton.setForeground(Color.BLACK);
				edgeButton.setBackground(Color.GREEN);
				edgeButton.setForeground(Color.RED);
				currentState = EDGE_FIRST;
				selected = 2;
			} else {
				nodeButton.setBackground(Color.LIGHT_GRAY);
				nodeButton.setForeground(Color.BLACK);
				edgeButton.setBackground(Color.LIGHT_GRAY);
				edgeButton.setForeground(Color.BLACK);
				currentState = NULL;
				selected = 0;
			}
		}
		if (event.getSource().equals(checkConnecting)) {
			if (panel.nodeExists(aTF.getText()) == false) {
				JOptionPane.showMessageDialog(null, aTF.getText() + " does not seem to exist", "Whoops", JOptionPane.NO_OPTION);
			} else if (panel.nodeExists(bTF.getText()) == false) {
				JOptionPane.showMessageDialog(null, bTF.getText() + " does not seem to exist", "Whoops", JOptionPane.NO_OPTION);
			} else { //if they both exist yo
				Queue queue = new Queue();
				ArrayList<String> connectedList = new ArrayList<String>();
				connectedList.add(panel.getNode(aTF.getText()).getLabel());
				ArrayList<String> edges = panel.getConnectedLabels(aTF.getText());
				for (int a = 0; a < edges.size(); a++) {
					//check each edge for if its connected
					queue.enqueue(edges.get(a));
				}
				while (queue.isEmpty() == false) {
					String currentNode = queue.dequeue();
					if (!connectedList.contains(currentNode)) {
						connectedList.add(currentNode);
					}
					edges = panel.getConnectedLabels(currentNode);
					for (int a = 0; a < edges.size(); a++) {
						//check each edge for if its connected
						if (!connectedList.contains(edges.get(a))) {
							queue.enqueue(edges.get(a));
						}
					}
				}
				if (connectedList.contains(bTF.getText())) {
					JOptionPane.showMessageDialog(null, "They are connected", "YAY", JOptionPane.NO_OPTION);
				} else {
					JOptionPane.showMessageDialog(null, "They are not connected", "Awwwww :(", JOptionPane.NO_OPTION);
				}
			}
		}
		if (event.getSource().equals(salesman)) {
			//we need to check for the shortest path!
			if (panel.getNode(salesmanStartTF.getText()) != null) {
				travelling(panel.getNode(salesmanStartTF.getText()), new ArrayList<Circle>(), 0);
				//make sure completed has a path
				//find the shortest/cheapest path, print out it's value, and the path
			} else {
				JOptionPane.showMessageDialog(null, "Not a valid starting path!", "ERROR!", JOptionPane.NO_OPTION);
			}
		}
	}
	public void travelling(Circle c, ArrayList<Circle> path, int total) {
		/**if the # of circles in the path is = to the # of circles
		add this path to the completed ArrayList. 
		remove last thing from the path
		retrun;
		else
		for each edge...
		see if they're connected
		add the circle to the path
		travelling(node, path, total + edge cost);
		remove last thing in the path**/
		if (path.size() == GraphPanel.edgeList.size()) {
			completed.add(path);
			path.remove(c);
			return;
		} else {
			for (int i = 0; i < GraphPanel.edgeList.size(); i++) {
				Edge e = GraphPanel.edgeList.get(i);
				if (e.getOtherEnd(c) != null) {
					if (!path.contains(e.getOtherEnd(c))) {
						path.add(e.getOtherEnd(c));
						travelling(e.getOtherEnd(c), path, total + Integer.parseInt(e.getLabel()));
					}
				}
			}
			path.remove(c);
		}
		System.out.println(total);
	}
	public void mouseClicked(MouseEvent event) {
	}
	public void mouseEntered(MouseEvent event) {
		if (selected == 0 || currentState == NULL) {
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (selected == 1 || currentState == NODE_SELECTED) {
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		} else if (selected == 1 || currentState == EDGE_FIRST) {
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
	}
	public void mouseExited(MouseEvent event) {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	public void mousePressed(MouseEvent event) {
	}
	@Override
	public void mouseReleased(MouseEvent event) {
		if (currentState == NODE_SELECTED) {
			panel.addNode(event.getX(), event.getY(), labelTF.getText());
		}
		else if (currentState == EDGE_FIRST) {
			if (panel.checkForCircle(event.getX(), event.getY()) != null) {
				first = panel.checkForCircle(event.getX(), event.getY());
				first.setHighlighted(true);
				//highlight that circle
				currentState = EDGE_SECOND;
			}
		}
		else if (currentState == EDGE_SECOND) {
			if (panel.checkForCircle(event.getX(), event.getY()) != null) {
				//lets check if the edge we're trying to add is infact a digit
				String s = labelTF.getText();
				boolean valid = true;
				for (int a = 0; a < s.length(); a++) {
					if (!Character.isDigit(s.charAt(a))) {
						//this edge that has been entered is not a digit
						//so it will not work
						valid = false;
					}
				}
				if (valid == true) {
					panel.addEdge(first, panel.checkForCircle(event.getX(), event.getY()), labelTF.getText());
					//highlight that circle
					first.setHighlighted(false);
					currentState = EDGE_FIRST;
				} else {
					JOptionPane.showMessageDialog(null, "The path you're trying to connect two circle with a non-digit character!\nPlease try again with a valid number", "ERROR!", JOptionPane.NO_OPTION);
				}
			}
		} else if (currentState == NULL) {
			//all pulling the screen like an iPhone
		}
		frame.repaint();
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (selected == 0 || currentState == NULL) {
			//screen moving code here
			System.out.println("HI");
		}
	}
	public void mouseMoved(MouseEvent arg0) {
	}
}