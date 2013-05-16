import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class TestProgram extends ConsoleProgram implements NameSurferConstants {
	public void run() {
		String line = "Addison 779 759 895 0 0 0 0 0 794 585 323";
		NameSurferEntry entry = new NameSurferEntry(line);
		println("The name is " + entry.getName() + " and it was ranked " + entry.getRank(11) + " in 1990");
		println("" + entry.toString());
	}
	
}
