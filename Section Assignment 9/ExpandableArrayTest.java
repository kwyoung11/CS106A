import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class ExpandableArrayTest extends ConsoleProgram {

	public void run() {
		ExpandableArray myList = new ExpandableArray();
		myList.set(14, "Bob");
		myList.set(21, "Sally");
		print("" + myList.get(14) + " " + myList.get(21));
	}
}
