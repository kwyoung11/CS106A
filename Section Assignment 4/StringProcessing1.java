import acm.program.*;


public class StringProcessing1 extends ConsoleProgram {

	public void run() {
		while (true) {
			String digits = readLine("Enter a numeric string: ");
			if (digits.length() == 0) break;
				println(addCommasToNumericString(digits));
		}
	}
	
	private String addCommasToNumericString(String digits) {
		int len = digits.length();
		int numCommas = len % 3 == 0? (len / 3) - 1: len / 3;
		int commaPos = 0;
		if (len % 3 == 0) commaPos = 2;
		if (len % 3 == 1) commaPos = 0;
		if (len % 3 == 2) commaPos = 1;
		String result = "";
		for (int i = 0; i < len; i ++) {
			result += digits.charAt(i);
			if (numCommas > 0 && i == commaPos && i < 4) {
				result += ",";
				numCommas--;
				commaPos += 3;
			} else if ((i == commaPos) && (i != len - 1)) {
				result += ",";
				numCommas--;
				commaPos += 3;
			}
		}
		return result;
	}
	
	
}

