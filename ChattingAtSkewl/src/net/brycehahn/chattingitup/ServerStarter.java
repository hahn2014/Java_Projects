package net.brycehahn.chattingitup;
import javax.swing.JFrame;
public class ServerStarter {

	public static void main(String[] args) {
		Server server = new Server();
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		server.startRunning();
	}
}