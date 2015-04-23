import java.awt.Color;
import java.awt.Graphics;

public class Truck extends Vehicle{
	public Truck(int newx, int newy, int newlane) {
		super(newx, newy);
		lane = newlane;
		width = 80;
		height = 50;
		speed = 7;
	}
	public void paintMe(Graphics g) {
		g.setColor(new Color(125, 125, 125));
		g.fillRect(x, y, width, height);
	}
}