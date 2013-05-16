import acm.program.*;


public class StringProcessing2 extends ConsoleProgram {

	
	public void run() {
		String ch = readLine("Enter a character: ");
		char c = ch.charAt(0);
		String str = readLine("Enter a line: ");
		println(removeAllOccurrences(str, c));
	}
	
	public String removeAllOccurrences(String str, char ch) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) != ch)) {
			result += str.charAt(i);
			}
		}
		return result;
	}
}
