/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		int x = getWidth() / 3;
		int y = getHeight() / 6;
		removeAll();
		GLine post = new GLine(x, y, x, y + SCAFFOLD_HEIGHT);
		add(post);
		GLine beam = new GLine(x, y, x + BEAM_LENGTH, y);
		add(beam);
		GLine rope = new GLine(x + BEAM_LENGTH, y, x + BEAM_LENGTH, y + ROPE_LENGTH);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) { 
		removePreviousDisplay();
		GLabel display = new GLabel(word, getWidth() / 2, getHeight() - 50);
		display.setFont("SansSerif-bold-18");
		add(display);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		GLabel add_letter = new GLabel("" + letter, (getWidth() / 2)  + (text_counter * 10), getHeight() - 40);
		add(add_letter);
		text_counter++;
		
		
		int x = getWidth() / 3 + BEAM_LENGTH;
		int y = getHeight() / 6 + ROPE_LENGTH;
		int arm_y = y + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		int hip_y = arm_y + BODY_LENGTH - ARM_OFFSET_FROM_HEAD;
		int foot_y = hip_y + LEG_LENGTH;
		
		switch (counter) {
		case 0: 
			GOval head = new GOval(x - HEAD_RADIUS / 2, y, HEAD_RADIUS, HEAD_RADIUS);
			add(head); 
			break;
		case 1: 
			GLine body = new GLine(x, y + HEAD_RADIUS, x, y + HEAD_RADIUS + BODY_LENGTH);
			add(body); 
			break;
		case 2: 
			GLine upper_left_arm = new GLine(x, arm_y, x - UPPER_ARM_LENGTH, arm_y);
			add(upper_left_arm); 
			GLine lower_left_arm = new GLine(x - UPPER_ARM_LENGTH, arm_y, x - UPPER_ARM_LENGTH, arm_y + LOWER_ARM_LENGTH);
			add(lower_left_arm); 
			break;
		case 3:
			GLine upper_right_arm = new GLine(x, arm_y, x + UPPER_ARM_LENGTH, arm_y);
			add(upper_right_arm); 
			GLine lower_right_arm = new GLine(x + UPPER_ARM_LENGTH, arm_y, x + UPPER_ARM_LENGTH, arm_y + LOWER_ARM_LENGTH);
			add(lower_right_arm); 
			break;
		case 4: 
			GLine hip = new GLine(x - (HIP_WIDTH / 2), hip_y, x + (HIP_WIDTH / 2), hip_y);
			add(hip); 
			break;
		case 5: 
			GLine left_leg = new GLine(x - (HIP_WIDTH / 2), hip_y, x - (HIP_WIDTH / 2), hip_y + LEG_LENGTH);
			add(left_leg); 
			break;
		case 6: 
			GLine right_leg = new GLine(x + (HIP_WIDTH / 2), hip_y, x + (HIP_WIDTH / 2), hip_y + LEG_LENGTH);
			add(right_leg); 
			break;
		case 7: 
			GLine left_foot = new GLine(x - (HIP_WIDTH / 2), hip_y + LEG_LENGTH, x - (HIP_WIDTH / 2) - FOOT_LENGTH, hip_y + LEG_LENGTH); 
			add(left_foot);
			GLine right_foot = new GLine(x + (HIP_WIDTH / 2), foot_y, x + (HIP_WIDTH / 2) + FOOT_LENGTH, foot_y); 
			add(right_foot); 
			break;
		}
		counter++;
	}
	
	private void removePreviousDisplay() {
		GObject prev_display = getElementAt(getWidth() / 2, getHeight() - 50);
		if (prev_display != null) {
			remove(prev_display);
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	/* Private instance variables */
	private int counter = 0;
	private int text_counter = 0;
}
