import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;


public class DrawLines extends GraphicsProgram {
	
	/* Adds mouse listeners */
	public void init() {
		addMouseListeners();
	}
	
	/* Creates line with mouse coordinates if mouse pressed */
	public void mousePressed(MouseEvent e) {
		line = new GLine(e.getX(), e.getY(), e.getX(), e.getY());
		add(line);
	}
	
	/* Sets endpoint of line to x, y coordinates 
	 * (i.e. the distance it was dragged) */
	public void mouseDragged(MouseEvent e) {
		line.setEndPoint(e.getX(), e.getY());
	}
	
	/* Private instance variables */
	private GLine line;
}
