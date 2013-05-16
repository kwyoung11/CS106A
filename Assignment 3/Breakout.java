/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Load sound */	
//	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au"); 

	public static void main(String[] args) {
		new Breakout().start(args);
	}
	
	/* Plays game */
	public void run() {
		drawBall();
		waitForClick();
		moveBall();
		
	}
	
	/* Sets up game (bricks and paddle) and event listeners */
	public void init() {
		drawBricks();
		drawPaddle();

		addMouseListeners();
	}
	
	/* Draws 10 rows of 10 bricks per row, coloring the
	 * bricks based on row number */
	private void drawBricks() {
		for (int i = 0; i < NBRICK_ROWS; i++) {
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				brick = new GRect(calculateXCoordinate(j), calculateYCoordinate(i), BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setColor(chooseBrickColor(i));
				add(brick);
			}
		}
	}
	
	private int calculateXCoordinate(int j) {
		return (getWidth() / 2) - ((BRICK_WIDTH * NBRICKS_PER_ROW) / 2) + (j * BRICK_WIDTH + (j * BRICK_SEP));
	}
	
	private int calculateYCoordinate(int i) {
		return BRICK_Y_OFFSET + (i * BRICK_HEIGHT + (i * BRICK_SEP));
	}
	
	/* Returns color of bricks to drawBricks() method */
	private Color chooseBrickColor(int i) {
		if (i < 2) return Color.RED;
		else if (i >= 2 && i < 4) return Color.ORANGE;
		else if (i >= 4 && i < 6) return Color.YELLOW;
		else if (i >= 6 && i < 8) return Color.GREEN;
		else return Color.CYAN;
	}
	
	private void drawPaddle() {
		int x = getWidth() / 2;
		int y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	/* Sets paddle location to X coordinate of new mouse position.
	 * Ensures paddle does not track beyond left & right boundaries*/
	public void mouseMoved(MouseEvent e) {
		int y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		if (e.getX() < WIDTH - PADDLE_WIDTH/2 && e.getX() > PADDLE_WIDTH/2) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH/2, y);
		}
	}
	
	/* Draws ball */
	private void drawBall() {
		int x = getWidth() / 2;
		int y = getHeight() / 2;
		ball = new GOval(x, y, BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball);
	}
	
	/* Moves ball. Ball starts moving at set speed but random direction.
	 * If ball hits left, right or top wall, ball bounces off, otherwise if it 
	 * hits bottom wall, game ends. Checks to see if paddle or bricks were hit.
	 * If all bricks are gone, player wins game.
	 * */
	private void moveBall() {
		brickCounter = 0;
		paddleCollisions = 0;
		setInitSpeedAndDirection();
		while (ball.getX() < WIDTH && ball.getY() < HEIGHT) {
			ball.move(vx, vy);
			pause(10);
			/* Reverses vx direction if ball hits left/right, top, or bottom wall*/
			if (ball.getX() + 2*BALL_RADIUS >= WIDTH || ball.getX() <= 0) vx = -vx; 
			if (ball.getY() <= 0) vy = -vy; 
			if (ball.getY() + 2*BALL_RADIUS > HEIGHT) break; 
			
			GObject collider = setBallSensors(); 
			determineObject(collider);
			if (brickCounter == 100) break;
		}
		gameOver();
	}
	
	private void setInitSpeedAndDirection() {
		vx = rgen.nextDouble(1.0, 3.0);
		vy = 3.0;
		if (rgen.nextBoolean(0.5)) vx = -vx;
	}
	
	/* 
	 * Checks if sensor is null, otherwise returns object sensed.
	 * Params are the sensors passed in from setBallSensors().
	 */
	private GObject getCollidingObject(GObject TL_sensor, GObject TR_sensor, GObject BL_sensor, GObject BR_sensor) {
		if (TL_sensor != null) return TL_sensor;
		else if (TR_sensor != null) return TR_sensor;
		else if (BL_sensor != null) return BL_sensor;
		else if (BR_sensor != null) return BR_sensor;
		else return null;
	}
	
	/*
	 * Sets sensors to the 4 corners of the bounding rectangle
	 * of the ball. Returns GObject via getCollidingObject().
	 */
	private GObject setBallSensors() {
		double x = ball.getX();
		double y = ball.getY();
		GObject TL_sensor = getElementAt(x, y);
		GObject TR_sensor = getElementAt(x + 2*BALL_RADIUS, y);
		GObject BL_sensor = getElementAt(x, y + 2*BALL_RADIUS);
		GObject BR_sensor = getElementAt(x + 2*BALL_RADIUS, y + 2*BALL_RADIUS);
		return getCollidingObject(TL_sensor, TR_sensor, BL_sensor, BR_sensor);
	}
	
	/* Checks if ball hit paddle or brick */
	private void determineObject(GObject collider) {
		/* Checks to see if ball hits paddle*/
		if (collider == paddle) {	
			double BLOfBall = ball.getY() + BALL_RADIUS*2;
			double topOfPaddle = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
			if (BLOfBall == topOfPaddle) {
				vy = -vy;
//				bounceClip.play(); 
			}
			/* Increases vx as game goes on */
			paddleCollisions++;
			if (paddleCollisions == 7) vx *= 2; 
			if (paddleCollisions == 21) vx *= 1.5;
		}
		/* Checks to see if ball hits brick */
		else if (collider != null) {
			vy = -vy;
			remove(collider);
			brickCounter++;
		}
	}
	
	/*
	 * Checks if all bricks are gone (game won) or if ball has
	 * hit the bottom wall (game lost)
	 */
	private void gameOver() {
		if (brickCounter == 100) { 	// if game was won ...
			GLabel gameWon = new GLabel("Game Over: You Won!");
			gameWon.setLocation(WIDTH / 2 - (gameWon.getWidth() / 2), HEIGHT / 2);
			add(gameWon);
		} else { 					// otherwise, if game was lost ...
			GLabel gameOver = new GLabel("Game Over: You Lost!");
			gameOver.setLocation(WIDTH / 2 - (gameOver.getWidth() / 2), HEIGHT / 2);
			add(gameOver);
		}
	}
	
	/*Private instance variables */
	private int paddleCollisions;
	private GRect brick;
	private int brickCounter;
	private GRect paddle;
	private GOval ball;
	private double vx, vy;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	

	
	
}
