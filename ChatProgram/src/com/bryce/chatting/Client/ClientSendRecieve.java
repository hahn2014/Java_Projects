package com.bryce.chatting.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.Executors;

import com.bryce.chatting.io.Logger;

public class ClientSendRecieve {
	
	private static OutputStream dOut;
    private static BufferedReader in;
	private ArrayList<Character> buffer;

	public static void sendToServer(String message) {
		try {
			sendBytes(message.getBytes()); //run the sender method
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readFromServer() {
		try {
			return readBytes(); //returns the string from the private method
		} catch (IOException e) {
			e.printStackTrace();
			return "Did not successfully read from the server.";
		}
	}
	
	public static void sendToClient(String clientName, int port, String message) {
		
	}
	

	/**
	 * 
	 */
	public void beginRead() {
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				try {
					char input;
					while (true) {
						input = (char)in.read();
						buffer.add(input);
						Logger.debug("Received: " + makeString(buffer));
						buffer.clear();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 
	 * @param buffer
	 * @return
	 */
	public String makeString(ArrayList<Character> buffer) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buffer.size(); i++) {
			sb.append(buffer.get(i));
		}
		return sb.toString();
	}
	
	/**
	 * private method to send to the server through a byte array
	 * @param myByteArray Byte array that will be sent to the server
	 * @throws IOException
	 * @since 1.0
	 * @author Bryce Hahn, Mason Cluff
	 */
	private static void sendBytes(byte[] myByteArray) throws IOException {
	    //write to the server with the length of the array
    	dOut.write(myByteArray);           // write the message to the server
        Logger.debug("[Message Send To Server] " + new String(myByteArray)); //debug text
		dOut.flush(); //flush the stream
	}
	
	/**
	 * private method to read form the server
	 * @return return a string that was inputed from the server
	 * @throws IOException
	 * @author Bryce Hahn, Dakota Alton, Mason Cluff
	 * @since 1.0
	 */
	private static String readBytes() throws IOException {
	    //char array with a length of 4000
	    char[] data = new char[4000];
	    in.read(data); //read from the server
	    return String.valueOf(data); //the return to the public method
	}
}