/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while (frontIsClear()) {
			replaceMissingStones();
			moveToNextColumn();
		}
		replaceMissingStones();
		returnToStart();
	}
	
	/*
	 * Replaces missing stones in a column. Precondition and 
	 * postcondition: Karel facing east at bottom of Ave.
	 */
	private void replaceMissingStones() {
		turnLeft();
		while(frontIsClear()) {
			if (noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
		if (noBeepersPresent()) {
			putBeeper();
		}
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
	}
	/*
	 * Moves to next column. Columns are 4 units apart, so Karel
	 * needs to move 3 units to the east.
	 */
	private void moveToNextColumn() {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}
	
	/*
	 * Returns to starting position
	 */
	private void returnToStart() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnAround();
	}
	

}
