import java.awt.Color;

public class Ball extends Sprite {
	
	// Initializing constant object attributes
	private static final Color MY_ORANGE = new Color(255,128,0);
	private static final int BALL_WIDTH = 25;
	private static final int BALL_HEIGHT = 25;
	private static final Color BALL_COLOUR = MY_ORANGE;

	// Ball object constructor
	public Ball(int panelWidth, int panelHeight) {
		setWidth(BALL_WIDTH);
		setHeight(BALL_HEIGHT);
		setColour(BALL_COLOUR);
		setInitialPosition(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2));
		resetToInitialPosition();
	}
}