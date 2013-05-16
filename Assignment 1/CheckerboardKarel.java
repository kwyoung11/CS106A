/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		fillRowWithCheckers();
		while (leftIsClear()) {
			moveUpOneRowAndRepositionToWest();
			checkForBeeperToSide();
			fillRowWithCheckers();
			if (rightIsClear()) {
				moveUpOneRowAndRepositionToEast();
				checkForBeeperToSide();
				fillRowWithCheckers();
			}
			else {
				turnAround();
			}
	
		}
	}

	private void moveUpOneRowAndRepositionToEast() {
		turnRight();
		move();
		turnRight();
		
	}

	private void moveUpOneRowAndRepositionToWest() {
		turnLeft();
		move();
		turnLeft();
	}

	private void fillRowWithCheckers() {
		if (facingEast()) {
			putBeeper();	
		}
		while (frontIsClear()) {
			if (noBeepersPresent()) {
				putBeeper();	
			}
				move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
	}

	private void checkForBeeperToSide() {
		if (facingWest()) {
			turnLeft();
			move();
			if (beepersPresent()) {
				turnAround();
				move();
				turnLeft();
				if (frontIsClear()) {
					move();
				}
			}
			else {
				turnAround();
				move();
				if (noBeepersPresent()) {
					putBeeper();
				}
				turnLeft();
			}
		}
		
	}
	
	
}
