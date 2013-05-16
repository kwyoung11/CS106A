import acm.program.*; 
import acm.util.*; 
import java.io.*; 
import java.util.ArrayList;
import java.util.*;

public class UniqueNames extends ConsoleProgram {

	private static final String SENTINEL = "";
	
	public void run() {
		println("This program prints a unique list of names");
		while (true) {
			String name = readLine("?");
			if (name.equals(SENTINEL)) break;
			nameArray.add(name);
		}
		println("Unique name list contains: ");
		// Extract only unique names and insert into new ArrayList
		for (int i = 0; i < nameArray.size(); i++) {
			if(!uniqueNameArray.contains(nameArray.get(i))) {
				uniqueNameArray.add(nameArray.get(i));
			}
		}
		// Print unique names
		for (int i = 0; i < uniqueNameArray.size(); i++) {
			println(" " + uniqueNameArray.get(i));
		}
	}
	
	private ArrayList<String> nameArray = new ArrayList<String>();
	private ArrayList<String> uniqueNameArray = new ArrayList<String>();
}
