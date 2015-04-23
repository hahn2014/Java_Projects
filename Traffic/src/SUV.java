import java.awt.Color;
import java.awt.Graphics;

public class SUV extends Vehicle{
	public SUV(int newx, int newy, int newlane) {
		super(newx, newy);
		lane = newlane;
		width = 80;
		height = 30;
		speed = 8;
	}
	public void paintMe(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
}