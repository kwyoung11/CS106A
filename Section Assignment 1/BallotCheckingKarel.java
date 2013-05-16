/*
 * File: BallotCheckingKarel.java
 * -------------------------------
 * The BallotCheckingKarel class should check each ballot
 * in Karel's world one by one. If there is a punched hole 
 * in the center of the ballot, Karel checks for any remaining
 * chad, which if present, is cleaned up. Karel ends facing east
 * to the right of the last ballot
 */

import stanford.karel.*;
	
public class BallotCheckingKarel extends SuperKarel {
	
	public void run() {
		move();
		while (frontIsClear()) {
			checkBallot();
			moveToNextBallot();
		}
	}
	
	private void moveToNextBallot() {
			move();
			if (frontIsClear()) {
				move();
			}
	}
	
	private void checkBallot() {
		if (noBeepersPresent()) {
			checkLeft();
			checkRight();
		}
	}

	private void checkLeft() {
		turnLeft();
		move();
		while (beepersPresent()) {
			pickBeeper();
		}
		turnAround();
		move();
		turnLeft();
	}
	
	
	private void checkRight() {
		turnRight();
		move();
		while (beepersPresent()) {
			pickBeeper();
		}
		turnAround();
		move();
		turnRight();
	}
	
}
