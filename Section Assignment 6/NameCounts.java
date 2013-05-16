import acm.program.*; 
import acm.util.*; 
import java.io.*; 
import java.util.ArrayList;
import java.util.*;

public class NameCounts extends ConsoleProgram {

	private static final String SENTINEL = "";
	
	public void run() {
		Map<String, Integer> nameMap = new HashMap<String, Integer>();
		while (true) {
			String name = readLine("Enter name:");
			if (name.equals(SENTINEL)) break;
			if (nameMap.containsKey(name)) {
				int count = nameMap.get(name);
				nameMap.put(name, ++count); // only change the value
			} else {
				nameMap.put(name, 1);// insert the key and value
			}
		}
		for (Map.Entry<String, Integer> name : nameMap.entrySet()) {
			println("Entry [" + name.getKey() + "] has count " + name.getValue());
		}
	}
	
}
