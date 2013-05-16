/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implement the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.JDialog;




public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		// Initialize Label
		add(new JLabel("Name"), SOUTH);
		// Initialize TextField
		boxField = new JTextField(20);
		boxField.setActionCommand("name");
		boxField.addActionListener(this);
		add(boxField, SOUTH);
		// Initialize Buttons
		add(new JButton("Graph"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		addActionListeners();
		db = new NameSurferDataBase(NAMES_DATA_FILE);
		graph = new NameSurferGraph();
		add(graph);
	}
	

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("name") || e.getActionCommand().equals("Graph")) {
			String name = boxField.getText();
			name = name.toUpperCase();
			NameSurferEntry entry = db.findEntry(name);
			if (entry != null && graph.getEntry(entry) == false) {
				graph.addEntry(entry);
			} else {
				JOptionPane frame = new JOptionPane();
				JOptionPane.showMessageDialog(frame, "That name could not be found, or it has already been graphed.");
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
		}
	}
	
	
	/* ivars */
	private NameSurferDataBase db;
	private JTextField boxField;
	private NameSurferGraph graph;
	
}
