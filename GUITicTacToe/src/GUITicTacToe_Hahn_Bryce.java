import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class GUITicTacToe_Hahn_Bryce implements ActionListener {
	JFrame frame = new JFrame("Tic Tac Toe");
	JButton[][] button = new JButton[3][3];
	Container center = new Container();
	int[][] board = new int[3][3];
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	boolean tie = false;
	int turn = X_TURN;
	
	Container north = new Container();
	int xWins = 0;
	int oWins = 0;
	String xName = "X";
	String oName = "O";
	JLabel xLabel = new JLabel(xName + " wins: " + xWins);
	JLabel nothing = new JLabel("");
	JLabel oLabel = new JLabel(oName + " wins: " + oWins);
	JButton xChangeName = new JButton("Update " + xName);
	JButton oChangeName = new JButton("Update" + oName);
	JButton restart = new JButton("Reset");
	JButton restart2 = new JButton("Play Again");
	JTextField xField = new JTextField("X Team");
	JTextField oField = new JTextField("O Team");
	
	Font font = new Font("Verdana", Font.BOLD, 72);
	public GUITicTacToe_Hahn_Bryce() {
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(3,3));
		for (int row = 0; row < button.length; row++) {
			for (int column = 0; column < button[0].length; column++) {
				button[row][column] = new JButton("");
				button[row][column].setFont(font);
				button[row][column].setBackground(Color.BLACK);
				button[row][column].addActionListener(this);
				center.add(button[row][column]);
			}
		}
		//add 'center' to the frame
		frame.add(center, BorderLayout.CENTER);
		
		//top section of the game
		north.setLayout(new GridLayout(3, 2));
		//add stuff to the game
		north.add(xLabel);
		north.add(nothing);
		north.add(oLabel);
		north.add(xChangeName);
		//add buttons
		xChangeName.addActionListener(this);
		north.add(oChangeName);
		oChangeName.addActionListener(this);
		north.add(restart);
		restart.addActionListener(this);
		//add text fields (where you enter desired name to change
		north.add(xField);
		north.add(oField);
		north.add(restart2);
		restart2.addActionListener(this);
		restart2.setEnabled(false);
		//add 'north' to the frame
		frame.add(north, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	/*	board drawn out helper
	 * 		1		2		3
	 * a [0][0]  [1][0]  [2][0]
	 * b [0][1]  [1][1]  [2][1]
	 * c [0][2]  [1][2]  [2][2]
	 */
	
	
	
	public boolean checkWin(int player) {
		if (board[0][0] == player && board[0][1] == player && board[0][2] == player) { //a1 b1 c1
			button[0][0].setBackground(Color.GREEN);
			button[0][1].setBackground(Color.GREEN);
			button[0][2].setBackground(Color.GREEN);
			return true;
		}
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) { //a1 b2 c3
			button[0][0].setBackground(Color.GREEN);
			button[1][1].setBackground(Color.GREEN);
			button[2][2].setBackground(Color.GREEN);
			return true;
		}
		if (board[0][0] == player && board[1][0] == player && board[2][0] == player) { //a1 a2 a3
			button[0][0].setBackground(Color.GREEN);
			button[1][0].setBackground(Color.GREEN);
			button[2][0].setBackground(Color.GREEN);
			return true;
		}
		if (board[0][1] == player && board[1][1] == player && board[2][1] == player) { //b1 b2 b3
			button[0][1].setBackground(Color.GREEN);
			button[1][1].setBackground(Color.GREEN);
			button[2][1].setBackground(Color.GREEN);
			return true;
		}
		if (board[0][2] == player && board[1][2] == player && board[2][2] == player) { //c1 c2 c3
			button[0][2].setBackground(Color.GREEN);
			button[1][2].setBackground(Color.GREEN);
			button[2][2].setBackground(Color.GREEN);
			return true;
		}
		if (board[1][0] == player && board[1][1] == player && board[1][2] == player) { //a2 b2 c2
			button[1][0].setBackground(Color.GREEN);
			button[1][1].setBackground(Color.GREEN);
			button[1][2].setBackground(Color.GREEN);
			return true;
		}
		if (board[2][0] == player && board[2][1] == player && board[2][2] == player) { //a3 b3 c3
			button[2][0].setBackground(Color.GREEN);
			button[2][1].setBackground(Color.GREEN);
			button[2][2].setBackground(Color.GREEN);
			return true;
		}
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) { //c1 b2 a3
			button[0][2].setBackground(Color.GREEN);
			button[1][1].setBackground(Color.GREEN);
			button[2][0].setBackground(Color.GREEN);
			return true;
		}
		
		return false;
	}
	//check if its a tie
	public boolean checkTie(int player) {
		if (board[0][0] == player && board[1][0] == player && board[2][0] == player && board[0][1] == player && board[1][1] == player && board[2][1] == player && board[2][0] == player && board[2][1] == player && board[2][2] == player) { //if all are pressed
			return true;
		}
		return false;
	}
public static void main(String[] args) {
	new GUITicTacToe_Hahn_Bryce(); 
}
@Override
public void actionPerformed(ActionEvent event) {
	if (event.getSource().equals(xChangeName) == true) {
		xName = xField.getText();
		xLabel.setText(xName + " wins: " + xWins);
		System.out.println("X name has been changed to \"" + xName + "\"");
		xChangeName.setText("Update " + xName);
	}
	if (event.getSource().equals(oChangeName) == true) {
		oName = oField.getText();
		oLabel.setText(oName + " wins: " + oWins);
		System.out.println("O name has been changed to \"" + oName + "\"");
		oChangeName.setText("Update " + oName);
	}
	if (event.getSource().equals(restart) == true) {
		//restart game (reset everything)
		resetBoard();
		System.out.println("Game Refreshed!");
	}
	if (event.getSource().equals(restart2) == true) {
		//startup a new game!
		System.out.println("Game Restarted!");
		clearBoard();
	}
	else {
	JButton current;
	for (int row = 0; row < button.length; row++) {
		for (int column = 0; column < button[0].length; column++) {
			if (event.getSource().equals(button[row][column]) == true) {
				current = button[row][column];
					if (board[row][column] == BLANK) {
						if (turn == X_TURN) {
							board[row][column] = X_MOVE;
							current.setEnabled(false);
							current.setText("X");
							turn = O_TURN;
						}
					}
				if (board[row][column] == BLANK) {
					if (turn == O_TURN) {
						board[row][column] = O_MOVE;
						current.setEnabled(false);
						current.setText("O");
						turn = X_TURN;
				}				
			}
				if (checkWin(X_MOVE) == true) {
					System.out.println("X wins!");
					xWins += 1;
					xLabel.setText(xName + " wins: " + xWins);
					JOptionPane.showMessageDialog(null, xName + " has Won! Congrats", xName + " wins!", JOptionPane.INFORMATION_MESSAGE);
					restart2.setEnabled(true);
				}
				if (checkWin(O_MOVE) == true) {
					System.out.println("O wins!");
					oWins += 1;
					oLabel.setText(oName + " wins: " + oWins);
					JOptionPane.showMessageDialog(null, oName + " has Won! Congrats", oName + " wins!", JOptionPane.INFORMATION_MESSAGE);
					restart2.setEnabled(true);
				}
				if (checkTie(X_MOVE) == true) {
					System.out.println("Its a tie!");
					restart2.setEnabled(true);
				}
				if (checkTie(O_MOVE) == true) {
					System.out.println("Its a tie!");
					restart2.setEnabled(true);
				}
	}
}
}
}
}
private void resetBoard() {
	for (int row = 0; row < button.length; row++) {
		for (int column = 0; column < button.length; column++) {
			board[row][column] = BLANK;
			button[row][column].setBackground(Color.BLACK);
			button[row][column].setText("");
			button[row][column].setEnabled(true);
			restart2.setEnabled(false);
			turn = X_TURN;
		}
	}
	xWins = 0;
	xLabel.setText(xName + " wins: " + xWins);
	oWins = 0;
	oLabel.setText(oName + " wins: " + oWins);
}
private void clearBoard() {
	for (int row = 0; row < button.length; row++) {
		for (int column = 0; column < button.length; column++) {
			board[row][column] = BLANK;
			button[row][column].setBackground(Color.BLACK);
			button[row][column].setText("");
			button[row][column].setEnabled(true);
			restart2.setEnabled(false);
			turn = X_TURN;
		}
	}	
}
}