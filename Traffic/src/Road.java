import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Road extends JPanel{
	private static final long serialVersionUID = 1L;
	final int ROAD_WIDTH = 800;
	ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
	public Road() {
		super();
	}
	public void addCar(Vehicle v) {
		cars.add(v); //add the car to be painted
	}
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		//draw the "road"
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//draw the lane lines
		g.setColor(Color.YELLOW);
		for (int y = 100; y <= 330; y+=110) {
			for (int x = 0; x < this.getWidth(); x+=40) {
				g.fillRect(x, y, 30, 5);
			}
		}
		//draw all the cars
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).paintMe(g);
		}
	}
	public void step() {
		Traffic.timerun = System.currentTimeMillis() - Traffic.starttime + 1L;
		// moving all the cars
		for (int a = 0; a < cars.size(); a++) {
			Vehicle v = cars.get(a);
			v.setX(v.getX() + v.getSpeed());
			if (collision(v) == true) {
				v.setX(v.getX() - v.getSpeed());
				// if they collide move it back
			} else {
				// make sure they aren't off screen
				if (v.getX() > this.getWidth()) {
					int tempx = v.getX();
					v.setX(0);
					if (collision(v)) {
						v.setX(tempx);
					}
					Traffic.time++;
				}
				continue;
			}
			if (v.getLane() != 3) { // make sure we can't draw it off screen
				v.setY(v.getY() + 110);
				v.setLane(v.getLane() + 1);
				if (collision(v) == false) {
					continue;
				} else {
					v.setY(v.getY() - 110);
					v.setLane(v.getLane() - 1);
				}
			}
			if (v.getLane() != 0) { // again, off screen
				v.setY(v.getY() - 110);
				v.setLane(v.getLane() - 1);
				if (collision(v) == false) {
					continue;
				} else {
					v.setY(v.getY() - 110);
					v.setLane(v.getLane() - 1);
				}
			}
		}
		Traffic.throughPut = "Throughput: " + ((double)Traffic.time / (double)Traffic.timerun);
	}
	public boolean collision(Vehicle v) {
		for (int a = 0; a < cars.size(); a++) {
			Vehicle u = cars.get(a);
			if (v.equals(u) == false) {
				if (u.getLane() == v.getLane()) {
					if (!(u.getX() + u.getWidth() < v.getX() ||
						  v.getX() + v.getWidth() < u.getX())) {
						return true;
					}
				}
			}
		}
		return false;
	}
}