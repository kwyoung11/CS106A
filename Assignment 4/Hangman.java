/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.io.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
    public void run() {
		canvas.reset();
    	println("Welcome to Hangman!");
		playHangman();
	}
    
    private void playHangman() {
    	
    	// convert lexicon to character array
    	for (int i = 0; i < lexicon.length(); i++) {
    		char ch = lexicon.charAt(i);
    		lexicon_array[i] = ch;
    	}
    	// initialize array to hold dashes
    	initializeArray();
    	
    	// get guesses from user
    	int n_guesses = 8;
    	while (true) {
    		if (printArray(guesses_array).equals(lexicon)) break;
    		contains_letter = false;
    		if (n_guesses == 0) break;
    		println("The word now looks like this: " + printArray(guesses_array));
			println("You have " + n_guesses + " guesses left");
			String ch = readLine("Your guess: ");
			while (ch.length() != 1) {
				ch = readLine("Only 1 letter allowed. Guess again: ");
			}
			ch = ch.toUpperCase();
			char c = ch.charAt(0);
			getIndex(c);
			if (contains_letter == true) {
				println("That guess is correct.");
			} else if (contains_letter == false) {
				println("There are no " + c + "'s in the word.");
				n_guesses--;
				canvas.noteIncorrectGuess(c);
			}
			if (printArray(guesses_array).equals(lexicon)) break;
    	}
    	
    	if (n_guesses == 0) {
			println("Your completely hung.");
			println("The word was: " + lexicon);
			println("You lose.");
    	} else {
    		println("You guessed the word: " + lexicon);
    		println("You win.");
    	}
    }
    
    private void initializeArray() {
    	guesses = "";
    	for (int i = 0; i < lexicon.length(); i++) {
    		guesses_array[i] = '-';
    		guesses += guesses_array[i];
    	}
    }
    
    private String printArray(char[] array) {
    	guesses = "";
    	for (int i = 0; i < array.length; i++) {
    		guesses += array[i];
    	}
    	canvas.displayWord(guesses);
    	return guesses;
    }
    
    private void getIndex(char c) {
    	for (int k = 0; k < guesses_array.length; k++) {
			int index = lexicon.indexOf(c, k);
			if (index != - 1 && index != temp) { // get position of character in lexicon array, if any
				guesses_array[index] = c;
				correct_guesses++;
				contains_letter = true;
			} 
			temp = index;
		}
    }
    
    
    /* Private instance variables */
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private HangmanLexicon word = new HangmanLexicon();
    private int x = word.getWordCount();
    private int number = rgen.nextInt(x);
    private String lexicon = word.getWord(number);
    private char[] lexicon_array = new char[lexicon.length()];
    char[] guesses_array = new char[lexicon.length()];
    private String guesses;
    boolean contains_letter = false;
    private int correct_guesses = 0;
    int temp = -1;
    private HangmanCanvas canvas;
    
}
