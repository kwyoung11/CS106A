import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.*;
import acm.util.*;

public class FlightPlanner extends ConsoleProgram {
	
	public void run() {
		println("Welcome to Flight Planner!");
		String filename = "flights.txt";
		ArrayList<String> cityValues = new ArrayList<String>();
		String cityKey = "";
		
		// Start reading in cities and destinations from text file
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = rd.readLine();
				
				// If line is null, add city and destination to hashmap and exit loop
				if (line == null) {
					ArrayList<String> temp = new ArrayList<String>();
					for (int i = 0; i < cityValues.size(); i++) {
						temp.add(cityValues.get(i));
					}
					flights.put(cityKey, temp);
					break;
				}
				
				// If line is empty, add city and destinations to hashmap
				if (line.isEmpty())  {
					ArrayList<String> temp = new ArrayList<String>();
					for (int i = 0; i < cityValues.size(); i++) {
						temp.add(cityValues.get(i));
					}
					flights.put(cityKey, temp);
					cityValues.clear();
					continue;
				}
				
					// Extract cities and their destinations from text file
					int startArrow = line.indexOf("-");
					cityKey = line.substring(0, startArrow - 1);
					cityValues.add(line.substring(startArrow + 3));
				}
			
		} catch (IOException ex) {
			throw new ErrorException(ex);	
		}
		handleInteraction(); 
	}
	
	// Handle user interaction.
	private void handleInteraction() {
		println("Here's a list of all the cities in our database: ");
		println("Let's plan a roundtrip route!");
		String startingCity = readLine("Enter the starting city: ");
		
		// Keep track of trip stop overs.
		ArrayList<String> tripStopPoints = new ArrayList<String>();
		tripStopPoints.add(startingCity);
		
		// Copy Hashmap value to new destinations array.
		destinations = flights.get(startingCity);
		println("From " + startingCity + " you can fly directly to:");
		for (int i = 0; i < destinations.size(); i++) {
			println("" + destinations.get(i));
		}
		
		// While city does not equal starting city, keep asking for destinations.
		while (true) {
			city = readLine("Where do you want to go from " + startingCity + "? ");
			tripStopPoints.add(city);
			if (city.equals(startingCity)) break;
			destinations = flights.get(city);
			println("From " + city + " you can fly directly to:");
			for (int i = 0; i < destinations.size(); i++) {
				println("" + destinations.get(i));
			}
			
		}
		// Print the round-trip flight itinerary.
		println("The route you've chosen is: ");
		for (int i = 0; i < tripStopPoints.size(); i++) {
			if (i == 0) {
				print("" + tripStopPoints.get(i));
			} else {
			print(" -> " + tripStopPoints.get(i));
			}
		}
	}
	
	/* ivars */
	Map<String, ArrayList<String>> flights = new HashMap<String, ArrayList<String>>();
	ArrayList<String> destinations;
	String city = "";


} 
