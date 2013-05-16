


import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class RandomCircles extends GraphicsProgram {
	
	
	public void run() {
		drawCircles();
	}
	
	/* Draws 10 circles with a random x, y position,
	 * radius, and color across the height and width of
	 * the screen.
	 */
	private void drawCircles() {
		for (int i = 0; i < 10; i++) {
			int radius = rgen.nextInt(5, 150);
			int x = rgen.nextInt(0, getWidth() - 150);
			int y = rgen. nextInt(0, getHeight() - 150);
			GOval circle = new GOval(x, y, radius, radius);
			circle.setFilled(true);
			circle.setFillColor(rgen.nextColor());
			add(circle);
		}
	}
	
	/* Private instance variables */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
}
