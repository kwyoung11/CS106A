/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINEL = 0;
	
	public void run() {
		println("This program takes a list of integers and finds the smallest and largest. Inputting 0 stops the program.");
		findSmallestAndLargest();
		}
	
	private void findSmallestAndLargest() {
		int firstVal = readInt("? ");
		int smallestNumber = firstVal;
		int largestNumber = firstVal;
		if (firstVal == SENTINEL) {
			println("No values have been entered");
		}
		while (true) {
			int value = readInt("? ");
			if (value == SENTINEL) break;
			if (value < smallestNumber) {
				smallestNumber = value;
			}
			if (value > largestNumber) {
				largestNumber = value;
			}
		}
		println("Smallest: " + smallestNumber);
		println("Largest: " + largestNumber);
	}
}

