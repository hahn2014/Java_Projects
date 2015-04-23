import java.awt.Graphics;
public abstract class Vehicle {
	int x;
	int y;
	int width = 0;
	int height = 0;
	int speed;
	int lane;
	public Vehicle(int newx, int newy) {
		x = newx;
		y = newy;
	}
	public void paintMe(Graphics g) {
		//we dont need to use this. lol what uselessness
	}
	public int getX() { //return the current x
		return x;
	}
	public void setX(int newx) { //set the vehicle's x
		x = newx;
	}
	public int getSpeed() { //return the speed of the vehicle
		return speed;
	}
	public int getY() { //get the current y
		return y;
	}
	public void setY(int newy) { //set the vehicle's y
		y = newy;
	}
	public int getWidth() { //get the width of the car (comes from the specified vehicle class)
		return width;
	}
	public int getLane() { //returns the lane number it is in
		return lane;
	}
	public void setLane(int newlane) { //set the current lane
		lane = newlane;
	}
}