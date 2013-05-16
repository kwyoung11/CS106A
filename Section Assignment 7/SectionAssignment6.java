import acm.graphics.*;
import acm.gui.*;
import acm.io.*;
import acm.program.*;
import java.awt.*;
import acm.util.*; 
import java.io.*; 
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class SectionAssignment6 extends GraphicsProgram {
	
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	
	public void init() {
		// Initialize Label
		add(new JLabel("Name"), SOUTH);
		// Initialize TextField
		boxField = new JTextField(20);
		boxField.setActionCommand("name");
		boxField.addActionListener(this);
		add(boxField, SOUTH);
		// Initialize Buttons
		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		addActionListeners();
		addMouseListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("name") || e.getActionCommand().equals("Add")) {
			double x = getWidth() / 2 - (BOX_WIDTH / 2);
			double y = getHeight() / 2 - (BOX_HEIGHT/2);
			labeledBox box = new labeledBox(boxField.getText(), x, y);
			add(box);
			labeledBoxMap.put(boxField.getText(), box);
		}
		
		if (e.getActionCommand().equals("Remove")) {
			String text = boxField.getText();
			if (labeledBoxMap.get(text) != null) {
			remove(labeledBoxMap.get(text));
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			removeAll();
		}
	}
	
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (gobj != null) {
			gobj.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}
	
	
	/* ivars */
	private JTextField boxField;
	private GPoint last;
	private GObject gobj;
	private Map<String, labeledBox> labeledBoxMap = new HashMap<String,labeledBox>(); 
	
	
	public class labeledBox extends GCompound {
		public labeledBox(String str, double x, double y) {
			GRect box = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
			add(box);
			GLabel boxLabel = new GLabel(str);
			add(boxLabel, x + BOX_WIDTH / 2 - (boxLabel.getWidth()/2), y + BOX_HEIGHT/2 + boxLabel.getDescent());
		}
	}
	
	
}
