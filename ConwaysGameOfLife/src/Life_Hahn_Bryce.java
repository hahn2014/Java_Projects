import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Life_Hahn_Bryce implements MouseListener, ActionListener, Runnable, MouseMotionListener{
	//Create new frame
	boolean[][] cells = new boolean[100][100];
	public int total = 0;
	public int highest = 0;
	public int timeSec = 0;
	public int timeMin = 0;
	public int timeHR = 0;
	JFrame frame = new JFrame("/// Conway's Game of Life - Bryce Hahn \\\\\\");
	LifePanel panel = new LifePanel(cells);
	Container south = new Container();
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Pause");
	JButton clear = new JButton("Clear Board");
	JButton change = new JButton("Toggle Grid");
	JButton toggle = new JButton("Toggle Timer");
	JButton quit = new JButton("Quit");
	JTextField number = new JTextField("Total Alive: " + total);
	JTextField hightest = new JTextField("Highest: " + highest);
	JTextField time = new JTextField("Time Spent: " + timeHR + ":" + timeMin + ":" + timeSec);
	boolean running = false;
	final JFileChooser fc = new JFileChooser();
	public static boolean grid = true;
	public static boolean code = false;
	public static boolean timeOn = false;
	
	public Life_Hahn_Bryce() {
		//Initialize and add stuff to frame!
		frame.setSize(900, 700);
		panel.setBackground(Color.black);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setEnabled(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		south.setLayout(new GridLayout(2, 3));
		south.add(step);
		step.setEnabled(true);
		step.setToolTipText("Start One Frame Of Simulation!");
		step.addActionListener(this);
		south.add(start);
		start.setEnabled(true);
		start.setToolTipText("Start the Simulation!");
		start.addActionListener(this);
		south.add(stop);
		stop.setEnabled(false);
		stop.addActionListener(this);
		stop.setToolTipText("Pause The Simulation!");
		south.add(clear);
		clear.setEnabled(false);
		clear.setToolTipText("Reset The Simultation Board!");
		clear.addActionListener(this);
		south.add(change);
		change.setEnabled(true);
		change.setToolTipText("Toggle Visiblility Of Grid to On/Off");
		change.addActionListener(this);
		south.add(number);
		number.setEditable(false);
		number.setEnabled(true);
		south.add(hightest);
		hightest.setEnabled(true);
		hightest.setEditable(false);
		south.add(time);
		time.setEnabled(true);
		time.setEditable(false);
		south.add(toggle);
		toggle.setEnabled(true);
		toggle.setToolTipText("Toggle Stop Clock to On/Off");
		toggle.addActionListener(this);
		south.add(quit);
		quit.setEnabled(true);
		quit.addActionListener(this);
		quit.setToolTipText("Exit The Game");
		frame.add(south, BorderLayout.SOUTH);
	}	
	public static void main(String[] args) {
		//Make new game
		new Life_Hahn_Bryce();
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent event) {
		double width = (double)panel.getWidth() / cells[0].length;
		double height = (double)panel.getHeight() / cells[0].length;
		//System.out.println("(" + event.getX() + "," + event.getY() + ")");	Useless, for showing current mouse position
		int column = (int)(event.getX() / width);
		int row = (int)(event.getY() / height);
		System.out.println("(" + column + ", " + row + ")");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int j2 = 0; j2 < 10; j2++) {
					cells[row + i][column + j] = true;
				}
			}
		}
		frame.repaint();
	}
	public void mouseMoved(MouseEvent arg0) {}
	public void mousePressed(MouseEvent event) {
		double width = (double)panel.getWidth() / cells[0].length;
		double height = (double)panel.getHeight() / cells[0].length;
		//System.out.println("(" + event.getX() + "," + event.getY() + ")");	Useless, for showing current mouse position
		int column = (int)(event.getX() / width);
		int row = (int)(event.getY() / height);
		System.out.println("(" + column + ", " + row + ")");
		cells[row][column] = !cells[row][column];
		frame.repaint();
	}
	public void actionPerformed(ActionEvent event) {
		//When you press one of the buttons
		if (event.getSource().equals(step)) {
			step();
			System.out.println("Stepping . . .");
		}
		if (event.getSource().equals(start)) {
			//Start the simulation
			running = true;
			Thread t = new Thread(this);
			t.start();
			System.out.println("Simulation started!");
			start.setEnabled(false);
			stop.setEnabled(true);
			clear.setEnabled(true);
		}
		if (event.getSource().equals(stop)) {
			running = false;
			System.out.println("Simulation stoped!");
			stop.setEnabled(false);
			start.setEnabled(true);
		}
		if (event.getSource().equals(clear)) {
			System.out.println("board cleared...");
			//Clear the board
			for (int a = 0; a < cells[0].length; a++) {
				for (int b = 0; b < cells.length; b++) {
					cells[a][b] = false ;
					frame.repaint();
					running = false;
					stop.setEnabled(false);
					start.setEnabled(true);
					clear.setEnabled(false);
				}
			}
		}
		if (event.getSource().equals(toggle)) {
			if (timeOn == true) {
				System.out.println("true");
				timeOn = false;
				return;
			}
			if (timeOn == false) {
				System.out.println("false");
				timeOn = true;
				return;
			}
		}
		if (event.getSource().equals(quit)) {
			System.exit(1);
		}
		if (event.getSource().equals(change)) {
			if (grid) {
				grid = false;
				System.out.println("Grid has been disabled");
				frame.repaint();
			}
			else if (!grid) {
				grid = true;
				frame.repaint();
				System.out.println("Grid has been enabled");
			}
		}
	}
	/*	This is what the board looks like in 2D drawn out!
	 * 	Row-1, column-1		row-1, column		row-1, column+1
	 * 	Row, column-1 		row, column			row, column+1
	 * 	Row+1, column-1		row+1, column		row+1, column+1
	 */
	private void step() {
		boolean[][] newCells = new boolean[cells[0].length][cells.length];
		for (int row = 0; row < cells[0].length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				int neightborCount = 0;
				//Checks the grid and will determine if it should live or not
				if (row > 0 && column > 0 && cells[row-1][column-1] == true) {
					neightborCount++;
				}
				if (row > 0 && cells[row-1][column] == true) {
					neightborCount ++;
				}
				if (row > 0 && column < cells.length-1 && cells[row-1][column+1] == true) {
					neightborCount ++;
				}
				if (column > 0 && cells[row][column-1] == true) {
					neightborCount ++;
				}
				if (column < cells.length-1 && cells[row][column+1] == true) {
					neightborCount ++;
				}
				if (row < cells[0].length-1 && column > 0 && cells[row+1][column-1] == true) {
					neightborCount ++;
				}
				if (row < cells[0].length-1 && cells[row+1][column] == true) {
					neightborCount ++;
				}
				if (row < cells[0].length-1 && column < cells.length-1 && cells[row+1][column+1] == true) {
					neightborCount++;
				}
				if (cells[row][column] == true) { //i'm Alive
					if (neightborCount == 2 || neightborCount == 3) {
						newCells[row][column] = true;
						total ++;
					}
					else {
						//you're Dead!
						newCells[row][column] = false;
						total --;
					}
				}
				else {//Dead cell...
					if (neightborCount == 3) {
						newCells[row][column] = true; //Player will live
					}
					else {
						newCells[row][column] = false;// Player is still dead
					}
				}
			}
		}
		cells = newCells;
		panel.setCells(newCells);
		frame.repaint();
		//Current alive counter... count the amount of squares that are currently alive
		total = 0; //This is the default counter
		for (int row = 0; row < cells[0].length; row++) {
			for (int column = 0; column < cells.length; column++) {
				if (cells[row][column]==true) {
					total++;
					number.setText("Total Alive: " + total); //Draw amount alive on textField!
					if (total > highest) {
						highest = total;
						hightest.setText("Highest: " + highest);
					}
				}
			}
		}
	}
	@Override
	public void run() {
		while (running == true) {
			step();
			if (timeOn) {
				timeCounter();
			}
			try {
			Thread.sleep(150); //This is the time (in milliseconds) the game will wait until it repaints the screen.
			} catch (Exception error) {
				error.printStackTrace();
			}
		}
	}
	private void timeCounter() { //This is a pointless but cool timer! :D
		try {
		Thread.sleep(1000); //Wait one second before redoing the code!
		if (timeMin == 60) { // If its been 1 hour ...
			timeMin = 0; //Reset the minutes
			timeHR++; //Add one hour to the clock
		}
		if (timeSec != 60) { //If it has not been 1 minute ...
			timeSec++; //Add one second
			time.setText("Time Spent: " + timeHR + ":" + timeMin + ":" + timeSec); //Redraw the clock
		}
		else { //Otherwise ...
			timeSec = 0; //Reset the seconds
			timeMin++; //Add one minute to the clock
			time.setText("Time Spent: " + timeHR + ":" + timeMin + ":" + timeSec); //Redraw the clock
		}
		if (timeHR >= 23 & timeMin >= 60 & timeSec >= 60) { //If an entire day has gone by...
			running = false; //Stop running
			System.out.println("You Take Too long! It's been an entire day! GO OUTSIDE!"); //Say this message
			System.exit(0); //Quit game! LOL :P
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}