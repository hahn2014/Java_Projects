import java.awt.*;
import javax.swing.*;

public class LifePanel extends JPanel {
	private static final long serialVersionUID = 4L;
	boolean[][] cells;
	public LifePanel(boolean[][] in) {
		cells = in;
	}
	public void setCells(boolean[][] newcells) {
		cells = newcells;
	}
	//Set a new Graphics object
	public void paintComponent(Graphics g) {
		//Clear graphics
		super.paintComponent(g);
		double width = (double)this.getWidth() / cells[0].length;
		double height = (double)this.getHeight() / cells[0].length;
		Color red = new Color(139, 0, 204);
		if (Life_Hahn_Bryce.code) { // If EA is completed ...
			red = (Color.BLUE); //Set block color to blue
		}
		else { //Otherwise ...
			red = new Color(139, 0, 204); //Set color to purple
		}
		g.setColor(red);
		for (int row = 0; row < cells[0].length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				if (cells[row][column] == true) {
					g.fillRect((int)(column * width) + 1,
							   (int)(row * height) + 1, 
							   (int)width, 
							   (int)height);
				}
			}
		}
		//Check if grid is disabled. If (grid = disabled) { change grid color to invisible} else { reset grid color}
		if (Life_Hahn_Bryce.grid == true) {
			g.setColor(Color.DARK_GRAY);
		}
		else if (Life_Hahn_Bryce.grid == false) {
			g.setColor(Color.BLACK);
		}
		
		for (int x = 0; x < cells[0].length; x++) {
			g.drawLine((int)(Math.round(x * width)),
					 0,(int)(Math.round(x * width)), 
					 this.getHeight());
		}
		for (int y = 0; y < cells[0].length; y++) {
			g.drawLine(0, (int)(Math.round(y * height)),
					this.getWidth(), 
					(int)(Math.round(y * height)));
		}
	}
}