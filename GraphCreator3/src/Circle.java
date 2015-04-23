
public class Circle {
	int x;
	int y;
	int radius = 20;
	String label;
	boolean highlighted = false;
	public Circle(int newx, int newy, String newlabel) {
		x = newx;
		y = newy;
		label = newlabel;
	}
	public boolean getHighlighted() {
		return highlighted;
	}
	public void setHighlighted(boolean b) {
		highlighted = b;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}