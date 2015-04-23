import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import javax.swing.*;
public class Traffic implements ActionListener, Runnable{
	JFrame frame = new JFrame("Traffic Simu - Bryce Hahn");
	Road road = new Road();
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	Container south = new Container();
	JButton semi = new JButton("Add Semi");
	JButton suv = new JButton("Add SUV");
	JButton sports = new JButton("Add Ferari");
	JButton truck = new JButton("Add Moster Truck");
	JTextField put = new JTextField("ThroughPut = " + "0");
	Container west = new Container();
	public static boolean running = false;
	public static long starttime;
	public static long time = 0;
	public static long timerun = 0;
	public static String throughPut = "";
	public Traffic() { //made by Bryce Hahn. This game might be low but hey, it's "fuuuuunnnnnnn"!
		frame.setSize(1100, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		//add the road
		frame.add(road, BorderLayout.CENTER);
		//add the south buttons
		south.setLayout(new GridLayout(1, 2));
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		stop.setEnabled(false);
		south.add(put);
		frame.add(south, BorderLayout.SOUTH);
		//add the west buttons
		west.setLayout(new GridLayout(4, 1));
		west.add(semi);
		semi.addActionListener(this);
		west.add(suv);
		suv.addActionListener(this);
		west.add(sports);
		sports.addActionListener(this);
		west.add(truck);
		truck.addActionListener(this);
		frame.add(west, BorderLayout.WEST);
		frame.repaint();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new Traffic();
	}
	public void run() {
		while (running) {
			road.step();
			frame.repaint();
			put.setText(throughPut);
			try {
				Thread.sleep(50);
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(stop)) {
			if (running) {
				running = false;
				start.setEnabled(true);
				stop.setEnabled(false);
			}
		}
		if (event.getSource().equals(start)) {
			if (!running) {
				running = true;
				Thread t = new Thread(this);
				t.start();
				starttime = System.currentTimeMillis();
				start.setEnabled(false);
				stop.setEnabled(true);
			}
		}
		if (event.getSource().equals(semi)) {
			addVehicle(new Semi(0, 0, 0));
			frame.repaint();
		}
		if (event.getSource().equals(suv)) {
			addVehicle(new SUV(0, 0, 0));
			frame.repaint();
			
		}
		if (event.getSource().equals(sports)) {
			addVehicle(new SportsCar(0, 0, 0));
			frame.repaint();
		}
		if (event.getSource().equals(truck)) {
			addVehicle(new Truck(0, 0, 0));
			frame.repaint();
		}
	}
	public void addVehicle(Vehicle v) {
		for (int x = 0; x < road.getWidth(); x+=20) {
			for (int y = 10; y <= 430; y+=110) {
				v.setX(x);
				v.setY(y);
				v.setLane((y-10) / 100);
				if (road.collision(v) == false) {
					road.addCar(v);
					return;
				}
			}
		}
	}
}