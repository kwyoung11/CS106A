/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		add(new JLabel("Name"), NORTH);
		// Initialize name TextField
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField, NORTH);
		
		// Add buttons on north border
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		// Add Status field
		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusField.setActionCommand("Change Status");
		statusField.addActionListener(this);
		add(statusField, WEST);
		add(new JButton("Change Status"), WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		// Add picture field
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		pictureField.setActionCommand("Change Picture");
		pictureField.addActionListener(this);
		add(pictureField, WEST);
		add(new JButton("Change Picture"), WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		// Add friend field
		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendField.setActionCommand("Add Friend");
		friendField.addActionListener(this);
		add(friendField, WEST);
		add(new JButton("Add Friend"), WEST);
	
		addActionListeners();
		db = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		add(canvas);
		
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	topBorderActions(e);
    	leftBorderActions(e);
	}
    
    // Action commands for the input fields on the Norther border.
    private void topBorderActions(ActionEvent e) {
    	name = nameField.getText();
    	// On Add profile event.
    	if (e.getActionCommand().equals("Add")) {
    		if (name != "") { // Make sure field is not empty
    			if (!db.containsProfile(name)) {
    				FacePamphletProfile profile = new FacePamphletProfile(name);
    				db.addProfile(profile);
    			
    				current_profile = profile;
    				canvas.displayProfile(current_profile);
    				canvas.showMessage("New profile created");
    			} else {
    				FacePamphletProfile profile = db.getProfile(name);
    				current_profile = profile;
    				canvas.showMessage("A profile with the name " + current_profile.getName() + " already exists");
    			}
    		}
		}
    	// On Delete profile event.
    	else if (e.getActionCommand().equals("Delete")) {
    		if (name != "") { 
    			if (db.containsProfile(name)) {
    				db.deleteProfile(name);
    				current_profile = null;
    				canvas.showMessage("Profile of " + name + " deleted");
    			} else {
    				canvas.showMessage("A profile with the name " + name + " does not exist");
    			}
    		}
		}
    	// On Lookup event.
    	else if (e.getActionCommand().equals("Lookup")) {
    		if (name != "") {
    			if (db.containsProfile(name)) {
        			FacePamphletProfile profile = db.getProfile(name);
    				current_profile = profile;
    				canvas.displayProfile(current_profile);
        			canvas.showMessage("Display" + name);
    			} else {
    				current_profile = null;
    				canvas.showMessage("A profile with the name " + name + " does not exist");
    			}
    		}
		}
		
    }
     
    // Action commands for the input fields on the Western border.
    private void leftBorderActions(ActionEvent e) {
    	status = statusField.getText();
    	picture = pictureField.getText();
    	friend = friendField.getText();
    	
    	// On Change Status event.
    	if (e.getActionCommand().equals("Change Status")) {
    		if (status != "") { // Make sure field is not empty
    			String status = statusField.getText();
    			if (current_profile != null) {
    				current_profile.setStatus(status);
    				canvas.displayProfile(current_profile);
    				canvas.showMessage("Status updated to " + status);
    			} else {
    				canvas.showMessage("Please select a profile to change status.");
    			}
    		}
		}
    	// On Change Picture event.
    	else if (e.getActionCommand().equals("Change Picture")) {
    		if (picture != "" && current_profile != null) { 
    			String filename = pictureField.getText();
    			GImage image = null;
    			try {
    				image = new GImage(filename);
    				current_profile.setImage(image);
    				canvas.displayProfile(current_profile);
    				canvas.showMessage("Picture updated.");
    			} catch (ErrorException ex) {
    				JOptionPane frame = new JOptionPane();
    				JOptionPane.showMessageDialog(frame, "Unable to open image file: " + filename);
    			}
    		} else {
    			canvas.showMessage("Please select a profile to change picture.");
    		}
		}
    	// On Add Friend event.
    	else if (e.getActionCommand().equals("Add Friend")) {
    		String friend = friendField.getText();
    		if (friend != "") {
    			// Check if user is already friends with friend.
    			Iterator<String> it = current_profile.getFriends();
				while(it.hasNext()) {
					if (it.next().equals(friend)) {
						canvas.showMessage(current_profile.getName() + " already has " + friend + " as a friend.");
					}
				}
				
				// Check if profile is null.
				if (current_profile == null) {
    				canvas.showMessage("Please select a profile to add friend.");
    			}
				
				// If friend does not exist.
				if (!db.containsProfile(friend)) {
					canvas.showMessage(friend + " does not exist.");
				}
				
				// If friend text is equal to current profile name.
    			if (current_profile.getName().equals(friend)) {
    				canvas.showMessage("You can't add yourself as a friend.");
    			}
    			
    			// If profile exists, friend is not equal to current profile and friend has a valid profile, then add friend.
    			if (current_profile != null && !current_profile.getName().equals(friend) && db.containsProfile(friend)) {
    				if (db.containsProfile(friend)) {
    					current_profile.addFriend(friend); 
    					db.getProfile(friend).addFriend(current_profile.getName()); // Make friendship reciprocal
    					canvas.displayProfile(current_profile);
    					canvas.showMessage(friend + " added as a friend.");
    				}
    			} 
    		}
    	}
    }
    
    private FacePamphletDatabase db;
	private JTextField nameField;
	private JTextField statusField;
	private JTextField pictureField;
	private JTextField friendField;
	private String name;
	private String status;
	private String picture;
	private String friend;
	private FacePamphletProfile current_profile;
	private FacePamphletCanvas canvas;
}
