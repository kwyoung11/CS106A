/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import acm.gui.*;
import java.awt.*;
import static java.util.Arrays.asList;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entries.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		entries.add(entry);
		update();
	}
	
	public boolean getEntry(NameSurferEntry entry) {
		return entries.contains(entry);
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawGraph();
		addDataLines();
		addDataLabels();
		addYearLabels();
	}
	
	private void drawGraph() {
		GLine topBorder = new GLine(0, GRAPH_MARGIN_SIZE, APPLICATION_WIDTH, GRAPH_MARGIN_SIZE);
		add(topBorder);
		GLine bottomBorder = new GLine(0, APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE - 60, APPLICATION_WIDTH, APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE - 60);
		add(bottomBorder);
		// Draw vertical lines.
		for (int i = 0; i < NDECADES; i++) {
			double x1 = i * getWidth()/NDECADES;
			double x2 = i * getWidth()/NDECADES;
			double y1 = 0;
			double y2 = APPLICATION_HEIGHT;
			GLine verticalLine = new GLine(x1, y1, x2, y2);
			add(verticalLine);
		}
	}
	
	private void addDataLines() {
		for (int i = 0; i < entries.size(); i++) {
			for (int j = 0; j < NDECADES - 1; j++) {
				NameSurferEntry entry = entries.get(i);
				double x1 = j * getWidth()/NDECADES;
				double x2 = (j + 1) * getWidth()/NDECADES;
				double y1 = entry.getRank(j) == 0 ? APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE - 60 : entry.getRank(j) + GRAPH_MARGIN_SIZE;
				double y2 = entry.getRank(j+1) == 0 ? APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE - 60 : entry.getRank(j+1) + GRAPH_MARGIN_SIZE;
				GLine dataLine = new GLine(x1, y1, x2, y2);
				dataLine.setColor(determineColor(i));
				add(dataLine);
			}
		}
	}
	
	private void addDataLabels() {
		for (int i = 0; i < entries.size(); i++) {
			for (int j = 0; j < NDECADES; j++) {
				NameSurferEntry entry = entries.get(i);
				double x1 = j * getWidth()/NDECADES;
				double y1 = entry.getRank(j) == 0 ? APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE - 60 : entry.getRank(j) + GRAPH_MARGIN_SIZE;
				String label = entry.getRank(j) == 0 ? "" + entry.getName() + "*" : "" + entry.getName() + " " + entry.getRank(j);
				GLabel dataLabel = new GLabel(label, x1 + 2, y1 - 2);
				dataLabel.setColor(determineColor(i));
				add(dataLabel);
				
			}
		}
	}
	
	private void addYearLabels() {
		for (int i = 0; i < entries.size(); i++) {
			for (int j = 0; j < NDECADES; j++) {
				double x1 = j * getWidth()/NDECADES;
				double y1 = APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE - 40;
				String label = "" + (START_DECADE + (j * 10));
				GLabel bottomLabel = new GLabel(label, x1 + 2, y1 - 2);
				add(bottomLabel);
			}
		}
	}
	
	private Color determineColor(int entryIndex) {
		int i = entryIndex % 4;
		Color color = i == 0 ? Color.BLACK : i == 1 ? Color.red : i == 2 ? Color.BLUE : Color.MAGENTA;
		return color;
	}
	
	
	/* ivars */
	int[] rankings = new int[11];
	public ArrayList<NameSurferEntry> entries = new ArrayList<NameSurferEntry>();
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
