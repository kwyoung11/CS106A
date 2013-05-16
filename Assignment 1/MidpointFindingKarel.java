/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		fillRow();
		while (beepersPresent()) {
			pickBeeperFromEndAndBeginning();	
		}
	}
	
	/*
	 * Moves Karel backwards one square
	 */
	private void moveBackwards() {
		turnAround();
		move();
		turnAround();
	}
	
	private void moveToWall() {
		while (frontIsClear()) {
			move();
		}
	}
	
	/***
	 * pre-condition: Karel at 1,1 facing east
	 * post-condition: Karel at 1,1 facing east
	 ***/
	private void fillRow() {
		while (frontIsClear()) {
			putBeeper();
			move();
		}
		putBeeper();
		turnAround();
		moveToWall();
		turnAround();
	}
	
	/***
	 * pre-condition: Karel at first beeper in row (1,1)
	 * post-condition: Karel at first beeper in row (2,1)
	 ***/
	private void pickBeeperFromEndAndBeginning() {
		pickEnd();
		pickBeginning();
	}

	/***
	 * pre-condition: Karel at 1,1 facing east
	 * post-condition: Karel at 1,1 facing east
	 ***/
	private void pickBeginning() {
		moveToWall();
		turnAround();
		while (noBeepersPresent()) {
			move();
		}
		pickBeeper();
		move();
	}
	
	/***
	 * pre: Karel at 1,1
	 * post: Karel 1 square ahead of where beeper 
	 * picked at end of row 
	 ***/
	private void pickEnd() {
		moveToWall();
		turnAround();
		while (noBeepersPresent()) {
			move();
		}
		checkForPreviousBeeper();
		pickBeeper();
		move();
	}
	
	private void checkForPreviousBeeper() {
		move();
		if (noBeepersPresent()) {
			moveBackwards();
			putBeeper();
			putBeeper();
		} else {
			moveBackwards();
		}
		
	}
	
	
}
