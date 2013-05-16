import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class robotFace extends GraphicsProgram {	
	
	private static final int HEAD_WIDTH = 300;
	private static final int HEAD_HEIGHT = 300;
	private static final int EYE_RADIUS = 10;
	private static final int MOUTH_WIDTH = 100;
	private static final int MOUTH_HEIGHT = 50;
	
	public void run() {
		drawHead();
		drawEyes();
		drawMouth();
	}
	
	private void drawHead() {
		int centerHeight = getHeight() / 2;
		int centerWidth = getWidth() / 2;
		double y = centerHeight - HEAD_HEIGHT / 2;
		double x = centerWidth - HEAD_WIDTH / 2; 
		GRect head = new GRect(x, y, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.GRAY);
		add(head);
	}
	
	private void drawEyes() {
		int centerHeight = getHeight() / 2;
		int centerWidth = getWidth() / 2;
		double y = centerHeight - HEAD_HEIGHT / 4;
		double x = centerWidth - HEAD_WIDTH / 2; 
		GOval leftEye = new GOval(x + HEAD_WIDTH*.25- EYE_RADIUS*2, y, EYE_RADIUS*2, EYE_RADIUS*2);
		GOval rightEye = new GOval(x + HEAD_WIDTH*.75, y, EYE_RADIUS*2, EYE_RADIUS*2);
		leftEye.setFilled(true);
		leftEye.setFillColor(Color.YELLOW);
		rightEye.setFilled(true);
		rightEye.setFillColor(Color.YELLOW);
		add(leftEye);
		add(rightEye);
	
	}
	
	private void drawMouth() {
		int centerHeight = getHeight() / 2;
		int centerWidth = getWidth() / 2;
		double y = centerHeight + HEAD_HEIGHT / 4 - MOUTH_HEIGHT / 2;
		double x = (centerWidth - HEAD_WIDTH / 2) + (HEAD_WIDTH - MOUTH_WIDTH) / 2; 
		GRect mouth = new GRect(x, y, MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setFillColor(Color.WHITE);
		add(mouth);
	
	}
	
}