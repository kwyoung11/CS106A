import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class WordCount extends ConsoleProgram {
	public void run() {
		println("This program counts the characters, words, and lines in a given text file.");
		try {
		BufferedReader rd = new BufferedReader(new FileReader("lear.txt"));
		int count = 0;
		while (true) {
			line = rd.readLine();			
			if (line == null) break;
			words.add(line);
			num_chars += line.length();
			StringTokenizer tokenizer = new StringTokenizer(line, " '");
			if (tokenizer != null) {
				while (tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();
					tokens.add(token);
				}
			}
			count++;
		}
		println("Chars: " + num_chars);
		println("Lines: " + words.size());
		println("Words: " + tokens.size());

		
		} catch (IOException ex) {
			
		}
	}
	
	/* ivars */
	private ArrayList<String> words = new ArrayList<String>();
	private ArrayList<String> tokens = new ArrayList<String>();
	private String line;
	private int num_chars = 0;
	
}
