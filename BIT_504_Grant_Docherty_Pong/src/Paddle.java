import java.awt.Color;

public class Paddle extends Sprite {

	// Initializing constant object attributes
	private static final Color MY_ORANGE = new Color(255,128,0);
	private static final int PADDLE_WIDTH = 10;
	private static final int PADDLE_HEIGHT = 100;
	private static final Color PADDLE_COLOUR = MY_ORANGE;
	private static final int DISTANCE_FROM_EDGE = 40;

	// Paddle object constructor
	public Paddle(Player player, int panelWidth, int panelHeight) {
		setWidth(PADDLE_WIDTH);
		setHeight(PADDLE_HEIGHT);
		setColour(PADDLE_COLOUR);
		int xPos;
		
		// start of if conditional
		if (player == Player.ONE) {
			xPos = DISTANCE_FROM_EDGE;
		} else { // end of if conditional, start of else conditional
			xPos = panelWidth - DISTANCE_FROM_EDGE - getWidth();
		} // end of else conditional
		
		setInitialPosition(xPos, panelHeight / 2 - (getHeight() / 2));
		resetToInitialPosition();
	}
}
