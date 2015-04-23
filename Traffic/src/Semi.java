import java.awt.Color;
import java.awt.Graphics;
public class Semi extends Vehicle{
	public Semi(int newx, int newy, int newlane) {
		super(newx, newy);
		lane = newlane;
		width = 120;
		height = 35;
		speed = 4;
	}
	public void paintMe(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}