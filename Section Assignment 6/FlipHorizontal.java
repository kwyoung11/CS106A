
import acm.io.*;
import acm.program.*;
import acm.util.*;

public class FlipHorizontal extends GraphicsProgram {

	private GImage flipHorizontal(GImage image) {
		int[][] array = image.getPixelArray();
		int width = array[0].length;
		int[][] temp = new int [array.length][array[0].length];
		for (int i = 0; i < array.length; i++) {
			for (int p1 = 0; p1 < width / 2; p1++) {
				int p2 = width - p1 - 1;
			    temp[0][0] = array[i][p1];
				array[i][p1] = array[i][p2];
				array[i][p2] = temp[0][0];
			}
		}
		return new GImage(array);
	}
	
}
