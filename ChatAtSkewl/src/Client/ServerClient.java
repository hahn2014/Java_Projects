package Client;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
public class ServerClient implements Runnable, ActionListener {
	private JFrame frame = new JFrame("Chat With Sunset HS Peps - Server Client User");
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JTextArea jta;
	private JScrollPane jscrlp;
	private JTextField jtfInput;
	private JButton send;
	private String serverInt;
	boolean connected = false;
	private JButton connection;
	public ServerClient() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setSize(600, 425);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		Thread myThread = new Thread(this);
		myThread.start();
		jta = new JTextArea(20, 52);
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setEnabled(false);
		jscrlp = new JScrollPane(jta);
		jtfInput = new JTextField(40);
		jtfInput.addActionListener(this);
		jtfInput.setEnabled(false);
		send = new JButton("SEND");
		send.setEnabled(false);
		send.addActionListener(this);
		connection = new JButton("Connect");
		connection.addActionListener(this);
		frame.getContentPane().add(jscrlp);
		frame.getContentPane().add(jtfInput);
		frame.getContentPane().add(send);
		frame.getContentPane().add(connection);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new ServerClient();
			}
		});
	}
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("SEND") || ae.getSource()instanceof JTextField) {
			try {
				oos.writeObject(jtfInput.getText());
				jta.setText(jta.getText() + "you: " + jtfInput.getText() + "\n");
				jtfInput.setText("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (ae.getSource().equals(connection)) {
			if (connected == true) {
				connection.setText("Connect");
				connected = false;
				jta.setEnabled(false);
				send.setEnabled(false);
				jtfInput.setEnabled(false);
				jtfInput.setText("");
				jta.setText(jta.getText() + "You have been disconnected\n");
				try {
					oos.writeObject("Stranger has disconnected.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (connected == false) {
				connection.setText("Disconnect");
				connected = true;
				jta.setEnabled(true);
				send.setEnabled(true);
				jtfInput.setEnabled(true);
				jtfInput.setText("");
				try {
				String ans = JOptionPane.showInputDialog(null,
				        "Please enter the desired Server's IP you wish to connect to",
				        "192.168.0.8");
				serverInt = ans;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void run() {
		try {
			socket = new Socket(serverInt, 666);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			while (true) {
				Object input = ois.readObject();
				jta.setText(jta.getText() + "Stranger: " + (String)input + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}