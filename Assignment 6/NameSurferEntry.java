/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;
import java.util.Arrays;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		int firstSpace = line.indexOf(" ");
		name = line.substring(0, firstSpace);
		for (int i = 0; i < 11; i++) {
			if (i < 10) {
			int secondSpace = line.indexOf(" ", firstSpace + 1);
			rank[i] = Integer.parseInt(line.substring(firstSpace + 1, secondSpace));
			firstSpace = secondSpace;
			} else {
				rank[i] = Integer.parseInt(line.substring(firstSpace + 1));
			}
		}
		 
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		if (decade < 11) {
			return rank[decade];
		} else {
			return 0;
		}
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		return name + " " + Arrays.toString(rank).replace(",", "");
	}
	
	/* ivars */
	private String name;
	private int[] decades;
	private int[] rank = new int[11];
}

