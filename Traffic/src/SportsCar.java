import java.awt.Color;
import java.awt.Graphics;
public class SportsCar extends Vehicle{
	public SportsCar(int newx, int newy, int newlane) {
		super(newx, newy);
		lane = newlane;
		width = 50;
		height = 25;
		speed = 10;
	}
	public void paintMe(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
}