/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n;
		do {
			n = readInt("Enter a number greater than 1: ");
		} while (n == 1);
		
		while (true) {
			if (n == 1) break;
			n = EvenOrOdd(n);
		}
	}
	
	
	private int EvenOrOdd(int n) {
		if (n % 2 == 0) {
			int previousN = n;
			n = n / 2;
			println(previousN + " is even, so I take half: " + n);
		} else {
			int previousN = n;
			n = n * 3 + 1;
			println(previousN + " is odd, so I make 3n + 1: " + n);
		}
		return n;
	}
		
}

