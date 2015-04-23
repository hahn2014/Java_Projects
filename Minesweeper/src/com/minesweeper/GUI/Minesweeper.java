package com.minesweeper.GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Minesweeper implements ActionListener {
	int rows = 20;
	int cols = 20;
	int neighborcount;
	int DIF = 0;
	int MINE = 10;
	Dimension size = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
	JFrame frame = new JFrame("Minesweeper - " + rows + "x" + cols + " - " + MINE + " mines");
	JButton quit = new JButton("Quit Game");
	JButton setMines = new JButton("Set Mines");
	JButton reset = new JButton("reset");
	JButton Easy = new JButton("Easy");
	JButton Medium = new JButton("Intermediate");
	JButton Hard = new JButton("Hard");
	JButton Expert = new JButton("Expert");
	JTextField minesSet = new JTextField(10);
	JButton[][] buttons;
	int difLevel = 1;
	int[][] counts = new int[rows][cols];
	Container Grid = new Container();
	Container north = new Container();
	public Minesweeper() {
		frame.setSize(size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		//add top buttons
		north.setLayout(new GridLayout(2, 4));
		//first add the options buttons and stuff
		north.add(reset);
		reset.addActionListener(this);
		north.add(minesSet);
		north.add(setMines);
		setMines.addActionListener(this);
		north.add(quit);
		quit.addActionListener(this);
		//then add the difficulty buttons
		north.add(Easy);
		Easy.addActionListener(this);
		north.add(Medium);
		Medium.addActionListener(this);
		north.add(Hard);
		Hard.addActionListener(this);
		north.add(Expert);
		Expert.addActionListener(this);
		frame.add(north, BorderLayout.NORTH);
		Grid.setLayout(new GridLayout(rows, cols));
		drawGrid();
		createRandomMines();
		frame.setVisible(true);
	}
	public void drawGrid() {
		//add buttons
		buttons = new JButton[rows][cols];
		for (int y = 0; y < buttons.length; y++) {
			for (int x = 0; x < buttons[0].length; x++) {
				buttons[x][y] = new JButton();
				buttons[x][y].addActionListener(this);
				Grid.add(buttons[x][y]);
			}
		}
		frame.add(Grid, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		new Minesweeper();
	}
	public void createRandomMines() {
		//initialize list of random pairs
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts[0].length; y++) {
				list.add(x * 100 + y);
			}
		}
		//resets counts
		counts = new int[rows][cols];
		for (int i = 0; i < 30; i++) {
			int choice = (int)(Math.random() * list.size());
			counts[list.get(choice) / 100][list.get(choice) % 100] = MINE;
			list.remove(choice);
		}
		//initialize neighbor counts
		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts[0].length; y++) {
				if (counts[x][y] != MINE) {
					neighborcount = 0;
					if (x > 0 && y > 0 && counts[x-1][y-1] == MINE) { //up left
						neighborcount++;
					}
					if (y > 0 && counts[x][y-1] == MINE) { //up
						neighborcount++;
					}
					if (x < counts.length - 1 && y > 0 && counts[x+1][y-1] == MINE) { //up right
						neighborcount++;
					}
					if (x > 0 && counts[x-1][y] == MINE) { //left
						neighborcount++;
					}
					if (x < counts.length - 1 && counts[x+1][y] == MINE) { //right
						neighborcount++;
					}
					if (x > 0 && y < counts[0].length - 1 && counts[x-1][y+1] == MINE) { //down left
						neighborcount++;
					}
					if (y < counts[0].length - 1 && counts[x][y+1] == MINE) { //down 
						neighborcount++;
					}
					if (x < counts.length - 1 && y < counts[0].length - 1 && counts[x+1][y+1] == MINE) { //down right
						neighborcount++;
					}
					counts[x][y] = neighborcount;
				}
			}
		}
		frame.setTitle("Minesweeper - " + rows + "x" + cols + " - " + MINE + " mines");
	}
	public void clearZeros(ArrayList<Integer> toClear) {
		if (toClear.size() == 0) {
			return;
		} else {
			int x = toClear.get(0) / 100;
			int y = toClear.get(0) % 100;
			toClear.remove(0);
			if (counts[x][y] == 0) {
				if (x > 0 && y > 0 && buttons[x-1][y-1].isEnabled() == false) { //up left
					buttons[x-1][y-1].setText(counts[x-1][y-1] + "");
					buttons[x-1][y-1].setEnabled(false);
					if (counts[x-1][y-1] == 0) {
						toClear.add((x - 1) * 100 + (y - 1));
					}
				}
				if (y > 0 && buttons[x][y-1].isEnabled() == false) { //up
					buttons[x][y-1].setText(counts[x][y-1] + "");
					buttons[x][y-1].setEnabled(false);
					if (counts[x][y-1] == 0) {
						toClear.add((x) * 100 + (y - 1));
					}
				}
				if (x < counts.length - 1 && y > 0 && buttons[x+1][y-1].isEnabled() == false) { //up right
					buttons[x+1][y-1].setText(counts[x+1][y-1] + "");
					buttons[x+1][y-1].setEnabled(false);
					if (counts[x+1][y-1] == 0) {
						toClear.add((x + 1) * 100 + (y - 1));
					}
				}
				if (x > 0 && buttons[x-1][y].isEnabled() == false) { //left
					buttons[x-1][y].setText(counts[x-1][y] + "");
					buttons[x-1][y].setEnabled(false);
					if (counts[x-1][y] == 0) {
						toClear.add((x - 1) * 100 + (y));
					}
				}
				if (x < counts.length - 1 && buttons[x+1][y].isEnabled() == false) { //right
					buttons[x+1][y].setText(counts[x+1][y] + "");
					buttons[x+1][y].setEnabled(false);
					if (counts[x+1][y] == 0) {
						toClear.add((x + 1) * 100 + (y));
					}
				}
				
				if (x > 0 && y < counts[0].length - 1 && buttons[x-1][y+1].isEnabled() == false) { //down left
					buttons[x-1][y+1].setText(counts[x-1][y+1] + "");
					buttons[x-1][y+1].setEnabled(false);
					if (counts[x-1][y+1] == 0) {
						toClear.add((x - 1) * 100 + (y + 1));
					}
				}
				if (y < counts[0].length - 1 && buttons[x][y+1].isEnabled() == false) { //down
					buttons[x][y+1].setText(counts[x][y+1] + "");
					buttons[x][y+1].setEnabled(false);
					if (counts[x][y+1] == 0) {
						toClear.add((x) * 100 + (y + 1));
					}
				}
				if (x < counts.length - 1 && y < counts[0].length - 1 && buttons[x+1][y+1].isEnabled() == false) { //down right
					buttons[x+1][y+1].setText(counts[x+1][y+1] + "");
					buttons[x+1][y+1].setEnabled(false);
					if (counts[x+1][y+1] == 0) {
						toClear.add((x + 1) * 100 + (y + 1));
					}
				}
			}
			clearZeros(toClear);
		}
	}
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(reset)) {
			for (int x = 0; x < buttons.length; x++) {
				for (int y = 0; y < buttons[0].length; y++) {
					buttons[x][y].setEnabled(true);
					buttons[x][y].setText("");
					buttons[x][y].setForeground(Color.BLACK);
				}
			}
			createRandomMines();
		}
		if (event.getSource().equals(Easy)) {
			DIF = 0;
			rows = 10;
			cols = 10;
			MINE = 10;
			Grid.removeAll();
			Grid.setLayout(new GridLayout(rows, cols));
			drawGrid();
			createRandomMines();
			frame.setTitle("Minesweeper - " + rows + "x" + cols + " - " + MINE + " mines");
			frame.repaint();
			frame.revalidate();
		}
		if (event.getSource().equals(Medium)) {
			DIF = 1;
			rows = 20;
			cols = 20;
			MINE = 20;
			Grid.removeAll();
			Grid.setLayout(new GridLayout(rows, cols));
			drawGrid();
			createRandomMines();
			frame.setTitle("Minesweeper - " + rows + "x" + cols + " - " + MINE + " mines");
			frame.repaint();
			frame.revalidate();
		}
		if (event.getSource().equals(Hard)) {
			DIF = 2;
			rows = 25;
			cols = 25;
			MINE = 25;
			Grid.removeAll();
			Grid.setLayout(new GridLayout(rows, cols));
			drawGrid();
			createRandomMines();
			frame.setTitle("Minesweeper - " + rows + "x" + cols + " - " + MINE + " mines");
			frame.repaint();
			frame.revalidate();
		}
		if (event.getSource().equals(Expert)) {
			DIF = 3;
			rows = 30;
			cols = 30;
			MINE = 30;
			Grid.removeAll();
			Grid.setLayout(new GridLayout(rows, cols));
			drawGrid();
			createRandomMines();
			frame.setTitle("Minesweeper - " + rows + "x" + cols + " - " + MINE + " mines");
			frame.repaint();
			frame.revalidate();
		}
		if (event.getSource().equals(setMines)) {
			if (!minesSet.getText().equals("") && !minesSet.getText().equals(null)) {//if there's something in it
				for (int i = 0; i < minesSet.getText().length(); i++) {
					if (Character.isDigit(minesSet.getText().charAt(i))) {
						int mines = Integer.parseInt(minesSet.getText());
						//check what difficulty we're currently on
						if (DIF == 0) {
							//easy difficulty
							//now check if it's greater than 2 and less and 20
							if (mines >= 2 && mines <= 20) {
								System.out.println(mines + " is a working value for easy!");
								//actually do the reset with the new amount of mines
								MINE = mines;
								for (int x = 0; x < buttons.length; x++) {
									for (int y = 0; y < buttons[0].length; y++) {
										buttons[x][y].setEnabled(true);
										buttons[x][y].setText("");
										buttons[x][y].setForeground(Color.BLACK);
									}
								}
								createRandomMines();
								break;
							} else {
								//notify it's an invalid value
								JOptionPane.showMessageDialog(null, "The value must be greater than\n2 and less than 20!", "ERROR", JOptionPane.NO_OPTION);
								break;
							}
						} else if (DIF == 1) {
							//medium difficulty
							//now check if it's greater than 10 and less and 40
							if (mines >= 10 && mines <= 40) {
								System.out.println(mines + " is a working value for medium!");
								//actually do the reset with the new amount of mines
								MINE = mines;
								for (int x = 0; x < buttons.length; x++) {
									for (int y = 0; y < buttons[0].length; y++) {
										buttons[x][y].setEnabled(true);
										buttons[x][y].setText("");
										buttons[x][y].setForeground(Color.BLACK);
									}
								}
								createRandomMines();
								break;
							} else {
								//notify it's an invalid value
								JOptionPane.showMessageDialog(null, "The value must be greater than\n10 and less than 40!", "ERROR", JOptionPane.NO_OPTION);
								break;
							}
						} else if (DIF == 2) {
							//hard difficulty
							//now check if it's greater than 40 and less and 400
							if (mines >= 40 && mines <= 400) {
								System.out.println(mines + " is a working value for hard!");
								//actually do the reset with the new amount of mines
								MINE = mines;
								for (int x = 0; x < buttons.length; x++) {
									for (int y = 0; y < buttons[0].length; y++) {
										buttons[x][y].setEnabled(true);
										buttons[x][y].setText("");
										buttons[x][y].setForeground(Color.BLACK);
									}
								}
								createRandomMines();
								break;
							} else {
								//notify it's an invalid value
								JOptionPane.showMessageDialog(null, "The value must be greater than\n40 and less than 400!", "ERROR", JOptionPane.NO_OPTION);
								break;
							}
						} else if (DIF == 3) {
							//expert difficulty
							//now check if it's greater than 400 and less and 800
							if (mines >= 400 && mines <= 800) {
								System.out.println(mines + " is a working value for expert!");
								//actually do the reset with the new amount of mines
								MINE = mines;
								for (int x = 0; x < buttons.length; x++) {
									for (int y = 0; y < buttons[0].length; y++) {
										buttons[x][y].setEnabled(true);
										buttons[x][y].setText("");
										buttons[x][y].setForeground(Color.BLACK);
									}
								}
								createRandomMines();
								break;
							} else {
								//notify it's an invalid value
								JOptionPane.showMessageDialog(null, "The value must be greater than\n400 and less than 800!", "ERROR", JOptionPane.NO_OPTION);
								break;
							}
						}
					} else {
						//notify it's not a number
						JOptionPane.showMessageDialog(null, "Trying to set a non-digit amount of mines!", "ERROR", JOptionPane.NO_OPTION);
						break;
					}
				}
			} else {
				//notify it's blank
				JOptionPane.showMessageDialog(null, "Trying to set a null amount of mines!", "ERROR", JOptionPane.NO_OPTION);
			}
		}
		if (event.getSource().equals(quit)) {		
			System.exit(0);
		} else {
			for (int x = 0; x < buttons.length; x++) {
				for (int y = 0; y < buttons[0].length; y++) {
					if (event.getSource().equals(buttons[x][y])) {
						if (counts[x][y] == MINE) {
							buttons[x][y].setForeground(Color.RED);
							buttons[x][y].setText("X");
							lostGame();
						} else if (counts[x][y] == 0) {
							buttons[x][y].setText(counts[x][y] + "");
							buttons[x][y].setEnabled(false);
							ArrayList<Integer> toClear = new ArrayList<Integer>();
							toClear.add(x * 100 + y);
							clearZeros(toClear);
							checkWin();
						} else {
							buttons[x][y].setText(counts[x][y] + "");
							buttons[x][y].setEnabled(false);
							checkWin();
						}
					}
				}
			}
		}
	}
	public void checkWin() {
		boolean won = false;
		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts[0].length; y++) {
				if (counts[x][y] != MINE && buttons[x][y].isEnabled() == true) {
					won = false;
				}
			}
		}
		if (won == true) {
			JOptionPane.showMessageDialog(null, "Cool good job. bye", "You Won!", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}
	public void lostGame() {
		for (int x = 0; x < buttons.length; x++) {
			for (int y = 0; y < buttons[0].length; y++) {
				if (buttons[x][y].isEnabled()) {
					if (counts[x][y] != MINE) {
						buttons[x][y].setText(counts[x][y] + "");
						buttons[x][y].setEnabled(false);
					}else {
						buttons[x][y].setForeground(Color.RED);
						buttons[x][y].setText("X");
						buttons[x][y].setEnabled(false);
					}
				}
			}
		}
	}
}