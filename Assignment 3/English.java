import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class English extends ConsoleProgram {

	public void run() {
		String word = readLine("Give me a word: ");
		println(removeDoubledLetters(word));
	}
	
	private String removeDoubledLetters(String word) {
		String result = "";
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (i > 0 && i < word.length()) {
				char previousChar = word.charAt(i - 1);
				while (ch == previousChar && i != word.length() - 1) {
					ch = word.charAt(i + 1);
					i++;
				}
				if (ch == previousChar && i == word.length() - 1) {
					ch = ' ';
				}
			}
			result += ch;
		}
		return result;
	}
}