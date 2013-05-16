/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*; 
import java.io.*; 
import java.util.*;

import static java.util.Arrays.asList;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		categoriesSelected = new int[nPlayers][13];
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		for (int i = 0; i < 13; i++) {
			for (int j = 1; j < nPlayers + 1; j++) {
				String name = playerNames[j - 1];
				display.printMessage("Round " + (i+1) + ", " + name + "'s turn! Click \"Roll Dice\" button to roll the dice.");
				display.waitForPlayerToClickRoll(j);
				rollDie();
				display.displayDice(dieValues);
				carryOutRemainingTurns(j);
			}
			roundNum++;
		}
		handleGameOver();
	}

	
	private void carryOutRemainingTurns(int playerNum) {
		String name = playerNames[playerNum - 1];
		for (int j = 0; j < 2; j++) {
			display.printMessage("Select the die you wish to re-roll and click \"Roll Again\".");
			display.waitForPlayerToSelectDice();
			getSelectedDie();
			reRollDie(dieSelected);
			display.displayDice(dieValues);
		}
		display.printMessage(name + ", your turn is over. Please click a category to receive a score.");
		int catNum = display.waitForPlayerToSelectCategory();
		catNum = handleWrongCategorySelection(catNum, playerNum);
		boolean categoryMatch = checkCategory(dieValues, catNum, playerNum);
		display.updateScorecard(catNum, playerNum, calculateScore(dieValues, catNum, categoryMatch));
	}
	
	private void getSelectedDie() {
		// Determine number of selections
		int count = 0;
		ArrayList<Integer> storeIndices = new ArrayList<Integer>();
		for (int k = 0; k < N_DICE; k++) {
			if (display.isDieSelected(k)) {
				count++;
				storeIndices.add(k);
			}
		}
		// Push indices of selections into new array
		dieSelected = new int[count];
		for (int k = 0; k < count; k++) {
			dieSelected[k] = storeIndices.get(k);
		}	
	}
	
	
	private int calculateScore(int[] dieValues, int catNum, boolean categoryMatch) {
		int score = 0;
		if (catNum >= ONES && catNum <= SIXES) {
			for (int i = 0; i < dieValues.length; i++) {
				if (dieValues[i] == catNum) {
					score += dieValues[i];
				}
			}
		} else if (catNum == THREE_OF_A_KIND || catNum == FOUR_OF_A_KIND || catNum == CHANCE) {
			for (int i = 0; i < dieValues.length; i++) {
				score += dieValues[i];
			}
		} else if (catNum == FULL_HOUSE) {
			score = 25;
		} else if (catNum == SMALL_STRAIGHT) {
			score = 30;
		} else if (catNum == LARGE_STRAIGHT) {
			score = 40;
		} else if (catNum == YAHTZEE) {
			score = 50;
		}
		if (categoryMatch == false) {
			score = 0;
		}
		storeScore(catNum, score);
		return score;
	}
	
	private void storeScore(int catNum, int score) {
		catMap.put(catNum, score);
	}
	
	private boolean checkCategory(int[] dieValues, int catNum, int playerNum) {
		// Add category to selected array.
		categoriesSelected[playerNum - 1][roundNum] = catNum;
		
		ArrayList<Integer> configIndependent = new ArrayList<Integer>(asList(CHANCE, ONES, TWOS, THREES, FOURS, FIVES, SIXES));
		if (configIndependent.indexOf(catNum) > -1) {
			return true;
		} else if (catNum == THREE_OF_A_KIND || catNum == FOUR_OF_A_KIND || catNum == YAHTZEE) {
			return checkForNOfKind(dieValues, catNum);
		} else if (catNum == FULL_HOUSE) {
			return checkForFullHouse(dieValues);
		} else {
			return false;
		}
	}
	
	private int handleWrongCategorySelection(int catNum, int playerNum) {
		determineIfCatAlreadySelected(catNum, playerNum);
		while (true) {
			if (selected == true) {
				display.printMessage("That category has already been chosen. Select another category.");
				catNum = display.waitForPlayerToSelectCategory();
				determineIfCatAlreadySelected(catNum, playerNum);
			}
			if (selected == false) {
				break;
			}
		}
		return catNum;
	}
	
	private void determineIfCatAlreadySelected(int catNum, int playerNum) {
		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			if (categoriesSelected[playerNum - 1][i] == catNum) {
				selected = true;
				break;
			} else {
				selected = false;
			}
		}
	}
	
	private boolean checkForNOfKind(int[] dieValues, int cat) {
		int catValue = cat == THREE_OF_A_KIND ? 3 : cat == FOUR_OF_A_KIND ? 4 : 5; 
			for (int i = 0; i < dieValues.length; i++) {
				count = 0;
				for (int j = 0; j < dieValues.length; j++) {
					if (dieValues[i] == dieValues[j]) {
						count++;
					}
				}
				if (count == catValue) break;
			}
			return count == catValue ? true : false;
	}
	
	private boolean checkForFullHouse(int[] dieValues) {
		int firstCount = 0;
		int secondCount = 0;
		// Check for three values.
		for (int i = 0; i < dieValues.length; i++) {
			firstCount = 0;
			for (int j = 0; j < dieValues.length; j++) {
				if (dieValues[i] == dieValues[j]) {
					firstCount++;
				}
			}
			if (firstCount == 3) break;
		}
		// Check for two values.
		for (int i = 0; i < dieValues.length; i++) {
			secondCount = 0;
			for (int j = 0; j < dieValues.length; j++) {
				if (dieValues[i] == dieValues[j]) {
					secondCount++;
				}
			}
			if (secondCount == 2) break;
		}
		return firstCount == 3 && secondCount == 2 ? true : false;
	}
	
	
	private int[] rollDie() {
		dieValues = new int[N_DICE];
		for (int i = 0; i < N_DICE; i++) {
			int dieNum = rgen.nextInt(1,6);
			dieValues[i] += dieNum;
		}
		return dieValues;
	}
	// Takes the indices of the selected die, generates new 
	// random values for the number of selected die, and using a parallel array,
	// merges them into the original array of die values.
	// @param dieSelected. An integer array holding the indices of the selected die.
	// @return. Returns an integer array of new die values.
	private int[] reRollDie(int[] dieSelected) {
		reRolledValues = new int[dieSelected.length];
		for (int i = 0; i < dieSelected.length; i++) {
			int dieNum = rgen.nextInt(1,6);
			reRolledValues[i] = dieNum;
		}
		// Merge re-rolled values into original array.
		for (int i = 0; i < dieSelected.length; i++) {
			dieValues[dieSelected[i]] = reRolledValues[i];
		}
		return dieValues;
	}
	
	private void handleGameOver() {
		display.printMessage("Game Over.");
		tallyFinalScores();
		int max = final_scores[0];
		int winner = 0;
		for (int i = 0; i < final_scores.length; i++) {
			if (final_scores[i] > max) {
				max = final_scores[i];
				winner = i;
			}
		}
		display.printMessage("Congratulations, " + playerNames[winner - 1] + " you're the winner with a total score of " + max);
	}
	
	private void tallyFinalScores() {
	 for (int i = 1; i < nPlayers + 1; i++) {
		 int playerNum = i;
		for (Map.Entry<Integer, Integer> cat : catMap.entrySet()) {
			int category = cat.getKey();
			int catValue = cat.getValue();
			if (catValue >= ONES && catValue <= SIXES) {
				upper_total += catValue;	
			} else if (catValue >= THREE_OF_A_KIND && catValue <= CHANCE) {
				lower_total += catValue;
			}
		}
		display.updateScorecard(UPPER_SCORE, playerNum, upper_total);
		display.updateScorecard(LOWER_SCORE, playerNum, lower_total);
		final_total = upper_total + lower_total;
		display.updateScorecard(TOTAL, playerNum, final_total);
		if (upper_total >= 63) {
			display.updateScorecard(UPPER_BONUS, playerNum, 35);
		}
		final_scores[i] = final_total; 
	 }
	}
	
		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] dieValues;
	private int[] dieSelected;
	private int[] reRolledValues;
	private int count = 0;
	private int[][] categoriesSelected;
	private boolean selected;
	private int catsSelected = 0;
	private Map<Integer, Integer> catMap = new HashMap<Integer, Integer>();
	private int upper_total;
	private int lower_total;
	private int final_total;
	private int roundNum = 0;
	private int[] final_scores = new int[5];
	
}
