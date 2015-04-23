import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
public class Graph implements ActionListener, MouseListener {
	//initialize and setup all the things for the program
	JFrame frame = new JFrame("Graph Creator 1 - Bryce Hahn");
	JButton nodeButton = new JButton();
	JButton edgeButton = new JButton();
	JTextField labelTF = new JTextField("A");
	Container west = new Container();
	GraphPanel panel = new GraphPanel();
	final int NODE_SELECTED = 0;
	final int EDGE_FIRST = 1;
	final int EDGE_SECOND = 2;
	int currentState = EDGE_FIRST;
	Circle first = null;
	
	public Graph() {//set the frame up yo
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		west.setLayout(new GridLayout(3, 1));
		west.add(nodeButton);
		nodeButton.addActionListener(this); //allows it to know when you click a button
		west.add(edgeButton);
		edgeButton.addActionListener(this); //allows it to know when you click a button
		west.add(labelTF);
		frame.add(west, BorderLayout.WEST); //add the container WEST to the frame on the left
		frame.add(panel, BorderLayout.CENTER); //add the panel from GraphPanel class in the center
		panel.addMouseListener(this);
		frame.setVisible(true);
		frame.repaint();
	}
	public static void main(String[] args) {
		new Graph();
	}
	public void actionPerformed(ActionEvent event) { //when you click a button, come here
		if (event.getSource().equals(nodeButton)) {
			nodeButton.setBackground(Color.GREEN);
			edgeButton.setBackground(Color.LIGHT_GRAY);
			currentState = NODE_SELECTED;
		}
		if (event.getSource().equals(edgeButton)) {
			nodeButton.setBackground(Color.LIGHT_GRAY);
			edgeButton.setBackground(Color.GREEN);
			currentState = EDGE_FIRST;			
		}
	}
	public void mouseClicked(MouseEvent event) {
	}
	public void mouseEntered(MouseEvent event) {
	}
	public void mouseExited(MouseEvent event) {
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
				panel.addEdge(first, panel.checkForCircle(event.getX(), event.getY()), labelTF.getText());
				//highlight that circle
				first.setHighlighted(false);
				currentState = EDGE_FIRST;
			}
		}
		frame.repaint();
	}
}