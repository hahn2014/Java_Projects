package com.bryce.chatting.Client;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientFrame {
	
	private JFrame frame = new JFrame();
	private JTextArea chatarea = new JTextArea(100, 100);
	private JScrollPane scroll = new JScrollPane();
	private Socket socket;
	
	public ClientFrame() {
		socket = new Socket();
		connect("192.168.1.18", 5001);
		frame.setSize(500, 560);
		try {
			frame.setTitle(InetAddress.getLocalHost().getHostAddress() + " - Chatting Program 1.0 [NOT CONNECTED]");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		scroll = new JScrollPane(chatarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scroll, BorderLayout.NORTH);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ClientFrame();
	}
	
	private void connect(String hostname, int port) {
		try {
			socket.connect(new InetSocketAddress(hostname, port));
			frame.setTitle(InetAddress.getLocalHost().getHostAddress() + " - Chatting Program 1.0 [CONNECTED]");
			ClientSendRecieve.sendToServer("FRM@");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}