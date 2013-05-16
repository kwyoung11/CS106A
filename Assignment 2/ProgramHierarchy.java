/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final int HEIGHT = 50;
	private static final int WIDTH = 120;
	
	public void run() {
		drawProgramBox();
		drawConnectors();
		drawGraphicsConsoleDialogBoxes();
		drawBottomRowLabels();
	}
	
	private void drawProgramBox() {
		int x = (getWidth() / 2) - (WIDTH / 2);
		int y = (getHeight() / 2) - (2*HEIGHT);
		GRect programBox = new GRect(x, y, WIDTH, HEIGHT);
		add(programBox);
		putProgramLabel();
	}
	
	private void drawConnectors() {
		int y = (getHeight() / 2) - HEIGHT;
		int Center_Width = getWidth() / 2;
		int Center_Height = getHeight() / 2;
		double x2 = Center_Width - WIDTH * 1.5;
		int y2 = Center_Height + HEIGHT / 2; 
		for (int i = 0; i < 3; i++ ) {
			GLine connectors = new GLine(Center_Width, y, x2 + (i * (WIDTH * 1.5)), y2);
			add(connectors);
		}
	}
	
	private void drawGraphicsConsoleDialogBoxes() {
		int Center_Width = getWidth() / 2;
		int Center_Height = getHeight() / 2;
		double x1 = Center_Width - (WIDTH * 2);
		int y1 = Center_Height + HEIGHT / 2; 
		for (int i = 0; i < 3; i++ ) {
			GRect rect = new GRect(x1 + (i * (WIDTH * 1.5)), y1, WIDTH, HEIGHT);
			add(rect);
		}
	}
	
	private void drawBottomRowLabels() {
			putGraphicsProgramLabel();
			putConsoleProgramLabel();
			putDialogProgramLabel();
	}
	
	private void putProgramLabel() {
		int x = (getWidth() / 2) - (WIDTH / 2);
		double y = getHeight() / 2 - HEIGHT;
		GLabel label = new GLabel("Program");
		label.setLocation(x + (WIDTH - label.getWidth()) / 2, y - 1.5*label.getAscent());
		add(label);
	}
	
	private void putGraphicsProgramLabel() {
		int Center_Width = getWidth() / 2;
		int Center_Height = getHeight() / 2;
		double x1 = Center_Width - (WIDTH * 2);
		int y1 = Center_Height + HEIGHT; 
		GLabel label = new GLabel("GraphicsProgram");
		label.setLocation(x1 + (WIDTH - label.getWidth()) / 2,  y1 + .5*label.getAscent());
		add(label);
	}
	
	private void putConsoleProgramLabel() {
		int Center_Width = getWidth() / 2;
		int Center_Height = getHeight() / 2;
		double x1 = Center_Width - (WIDTH * 2) + (1 * (WIDTH * 1.5));
		int y1 = Center_Height + HEIGHT; 
		GLabel label = new GLabel("ConsoleProgram");
		label.setLocation(x1 + (WIDTH - label.getWidth()) / 2, y1 + .5*label.getAscent());
		add(label);
	}
	
	private void putDialogProgramLabel() {
		int Center_Width = getWidth() / 2;
		int Center_Height = getHeight() / 2;
		double x1 = Center_Width - (WIDTH * 2) + (2 * (WIDTH * 1.5));
		int y1 = Center_Height + HEIGHT; 
		GLabel label = new GLabel("DialogProgram");
		label.setLocation(x1 + (WIDTH - label.getWidth()) / 2, y1 + .5*label.getAscent());
		add(label);
	}
}

