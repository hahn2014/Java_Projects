package net.brycehahn.chattingitup;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame {
	private static final long serialVersionUID = 2L;
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message;
	private String serverIP;
	private Socket connection;
	
	public Client(String host) {
		super("Chatting At Skewl! USER");
		serverIP = host;
		userText = new JTextField();
		userText.setEditable(false);
		chatWindow.setEditable(false);
		userText.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					sendData(event.getActionCommand());
					userText.setText("");
				}
			}
		);
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		setSize(300, 300);
		setVisible(true);
	}
	//connect to the server hosting the chat
	public void startRunning() {
		try {
			connectToServer();
			setupStreams();
			whileChatting();
		} catch(EOFException e) {
			showMessage("\nClient terminated the connection.");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			closeCrap();
		}
	}
	//connect to the server
	private void connectToServer() throws IOException {
		showMessage("\nAttempting to connect to host...\n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789);
		showMessage("Successfully connected to " + connection.getInetAddress().getHostName());
	}
	//set up the streams to send and recieve messages
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}
	//while chatting with the host
	private void whileChatting() throws IOException {
		ableToType(true);
		do {
			try {
				message = (String)(input.readObject());
				showMessage("\n" + message);
			} catch(ClassNotFoundException e) {
				showMessage("\nCould not find the ObjectType.\n" + e);
			}
		} while (!message.equals("stranger: end"));
	}
	//close the streams and sockets
	private void closeCrap() {
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	//send message to host
	private void sendData(String message) {
		try {
			output.writeObject("\nstranger: " + message);
			output.flush();
			showMessage("\nYou: " + message);
		} catch(IOException e) {
			chatWindow.append("\nFailed to send message.");
		}
	}
	//update the chat window
	private void showMessage(final String text) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					chatWindow.append(text);
				}
			}
		);
	}
	//toggles textbox
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