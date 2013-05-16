/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	private static final int PIX_PER_INCH = 72;
	private static final double FACTOR = 0.35;
	
	
	public void run() {
		for (int i = 0; i < 3; i++) {
			double radius = (PIX_PER_INCH * (1 - i * FACTOR));
			double x = getWidth() / 2 - radius;
			double y = getHeight() / 2 - radius;
			GOval oval = new GOval(x, y, radius*2, radius*2);
			oval.setFilled(true);
			if (i == 1)
				oval.setFillColor(Color.WHITE);
			else
				oval.setFillColor(Color.RED);
			add(oval);
		}
	}
}
