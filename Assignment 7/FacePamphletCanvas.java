/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		
		// If message already exists, remove it
		if (getElementAt(getWidth()/2, getHeight() - BOTTOM_MESSAGE_MARGIN) != null) {
			GObject oldMsg = getElementAt(getWidth()/2, getHeight() - BOTTOM_MESSAGE_MARGIN);
			remove(oldMsg);
		}
		
		// Display new message
		GLabel newMsg = new GLabel(msg, getWidth()/3, getHeight() - BOTTOM_MESSAGE_MARGIN);
		newMsg.setFont(MESSAGE_FONT);
		add(newMsg);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		
		// Draw the profile name
		GLabel name = new GLabel(profile.getName(), LEFT_MARGIN, TOP_MARGIN*2);
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		add(name);
		
		// Display the image
		GImage image = profile.getImage();
		if (image != null) {
			add(image, LEFT_MARGIN, TOP_MARGIN*2 + name.getDescent() + IMAGE_MARGIN);
		} else {
			GRect placeHolderImage = new GRect(LEFT_MARGIN, TOP_MARGIN*2 + name.getDescent() + IMAGE_MARGIN, IMAGE_HEIGHT, IMAGE_WIDTH);
			add(placeHolderImage);
			GLabel placeHolderText = new GLabel("No Image");
			add(placeHolderText, placeHolderImage.getWidth()/2 - placeHolderText.getWidth()/5, TOP_MARGIN*2 + name.getDescent() + IMAGE_MARGIN + IMAGE_HEIGHT/2);
		}
		
		// Display the status
		String status = profile.getStatus() == "" ? "No current status" : profile.getName() + " is " + profile.getStatus();
		GLabel displayStatus = new GLabel(status, LEFT_MARGIN, TOP_MARGIN*2 + name.getDescent() + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN*2);
		displayStatus.setFont(PROFILE_STATUS_FONT);
		add(displayStatus);
		
		
		// Display the list of friends
		GLabel friendLabel = new GLabel("Friends: ", getWidth()/2, TOP_MARGIN*2 + name.getDescent() + IMAGE_MARGIN);
		friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendLabel);
		Iterator<String> it = profile.getFriends();// Add new label for each friend
		int i = 1;
		while(it.hasNext()) {
			GLabel singleFriendLabel = new GLabel(it.next());
			singleFriendLabel.setFont(PROFILE_FRIEND_FONT);
			add(singleFriendLabel, getWidth()/2, TOP_MARGIN*2 + name.getDescent() + IMAGE_MARGIN + (singleFriendLabel.getHeight() * i));
			i++;
		}
	}

	/* ivars */
	GLabel newMsg;
	int friendCount = 1;
}
