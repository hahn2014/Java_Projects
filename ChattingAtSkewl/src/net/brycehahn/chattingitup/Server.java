package net.brycehahn.chattingitup;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	public Server() {
		super("Chatting At Skewl! HOST");
		userText = new JTextField();
		userText.setEditable(false);
		chatWindow.setEditable(false);
		userText.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					//send the message over the web
					sendMessage(event.getActionCommand());
					userText.setText("");
				}
			}
		);
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(300, 300);
		setVisible(true);
	}
	public void startRunning() {
		try {
			server = new ServerSocket(6789, 100);
			while (true) {
				try {
					//connect to the sever
					waitForConnection();
					setupStreams();
					whileChatting();
				} catch(EOFException ex) {
					showMessage("\nYou've been disconnected!");
				} finally {
					closeCrap();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	//wait for the connection over the network
	private void waitForConnection() throws IOException {
		showMessage("Waiting for someone to connect...");
		connection = server.accept();
		showMessage("\nNow connected to " + connection.getInetAddress().getHostName());
	}
	//get stream to send and receive data
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}
	//durring the chat conversation
	private void whileChatting() throws IOException{
		String message = "";
		ableToType(true);
		do {
			try {
				message = (String)(input.readObject());
				showMessage("\n" + message);
			} catch(ClassNotFoundException e) {
				showMessage("\nok so this isn't supposed to happen.......");
			}
		} while (!message.equals("CLIENT - END"));
	}
	//close streams and sockets after you are done chatting
	private void closeCrap() {
		showMessage("\nClosing Connections...\n");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		showMessage("\nGood Day.");
	}
	//send a message to the client
	private void sendMessage(String message) {
		try {
			output.writeObject("\nstranger: " + message);
			output.flush();
			showMessage("\nYou: " + message);
		} catch(IOException e) {
			chatWindow.append("\nFailed to send message!");
		}
	}
	//update chat window
	private void showMessage(final String text) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					chatWindow.append(text);
				}
			}
		);
	}
	//allow the user to be able to type
	private void ableToType(final boolean canType) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					userText.setEditable(canType);
				}
			}
		);
	}
}