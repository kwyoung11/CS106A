import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Fibonacci extends ConsoleProgram {	
	
	private static final int MAX_TERM_SIZE = 10000;
	
	public void run() {
		welcomeMessage();
		fibSequence();
	}
		
	private void fibSequence() {
		int i = 0;
		while (true) {
			int term = fib(i);
			if (term > MAX_TERM_SIZE) break;
			println(term);	
			i++;
			
		}
	}
	
	private int fib(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (n > 1)
			return fib(n-1) + fib(n-2);
		
		return 0;
		}
	
	private void welcomeMessage() {
		println("This program lists the Fibonacci sequence.");
	}
}