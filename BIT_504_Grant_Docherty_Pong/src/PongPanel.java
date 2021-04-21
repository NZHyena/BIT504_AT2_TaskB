import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanel extends JPanel implements ActionListener, KeyListener {

	// Initializing constant attributes 
	private final static Color MY_BLUE = new Color(134, 243, 243);
	private final static Color BACKGROUND_COLOUR = MY_BLUE;
	private final static Color FONT_COLOUR = Color.BLACK;
	private final static int TIMER_DELAY = 5;
	private final static int BALL_MOVEMENT_SPEED = 2;
	private final static int POINTS_TO_WIN = 11;
	private final static int SCORE_TEXT_X = 100;
	private final static int SCORE_TEXT_Y = 100;
	private final static int SCORE_FONT_SIZE = 50;
	private final static String SCORE_FONT_FAMILY = "Serif";
	private final static int WINNER_TEXT_X = 200;
	private final static int WINNER_TEXT_Y = 200;
	private final static int WINNER_FONT_SIZE = 40;
	private final static String WINNER_FONT_FAMILY = "Serif";
	private final static String WINNER_TEXT = "WIN!";

	// Initializing scores for start of game
	int player1Score = 0, player2Score = 0;
	
	// Creating non-initialized Player enumerator and required paddle and ball objects
	Player gameWinner;
	Ball ball;
	Paddle paddle1, paddle2;

	// Initializing game state for application
	GameState gameState = GameState.INITIALISING;

	// PongPannel object constructor
	public PongPanel() {
		setBackground(BACKGROUND_COLOUR);
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}

	/*
	 * Creates new object instances for all required sprite objects 
	 * 
	 * @param None
	 * @return Void
	 */
	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.ONE, getWidth(), getHeight());
		paddle2 = new Paddle(Player.TWO, getWidth(), getHeight());
	}

	/*
	 * Updates the game based on the gameState.
	 * Used to call createObjects() if the game is first initializing
	 * Updates the movement of objects, checks for object collisions, and checks if a player has won if the game is being played
	 * Stops everything is the game is over
	 * 
	 * @param None
	 * @return Void
	 */
	private void update() {
		//Using switch conditional to check the gameState and take action accordingly
		switch (gameState) {
			case INITIALISING: {
				createObjects();
				gameState = GameState.PLAYING;
				ball.setxVelocity(BALL_MOVEMENT_SPEED);
				ball.setyVelocity(BALL_MOVEMENT_SPEED);
				break;
			}
			case PLAYING: {
				moveObject(paddle1); // Move Paddle for player 1
				moveObject(paddle2); // Move Paddle for player 2
				moveObject(ball); // Move ball
				checkWallBounce(); // Check for wall bounce
				checkPaddleBounce(); // Check for paddle bounce
				checkWin(); // Check for game completed
				break;
			}
			case GAMEOVER: {
				break;
			}
		} // End of switch conditional
	}
	
	/*
	 * Paints the dotted line through the middle of the game window
	 * 
	 * @param Graphics
	 * @return Void
	 */
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.BLACK);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.dispose();
	}

	/*
	 * Paints the parameter sprite object in the game window
	 * 
	 * @param Graphics, Sprite game object
	 * @return Whether the file was read successfully
	 */
	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
	}

	/*
	 * Overrides the keyPressed method
	 * Reads the KeyEvent from the keylistener in the Panel constructor for a key being pressed and uses it to move each players paddle
	 * 
	 * @param KeyEvent
	 * @return Void
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		
		// Start of if conditional
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			paddle2.setyVelocity(-2);
		} else if (event.getKeyCode() == KeyEvent.VK_DOWN) { // End of if conditional, start of else if conditional
			paddle2.setyVelocity(2);
		} // End of else if conditional
		
		// Start of if conditional
		if (event.getKeyCode() == KeyEvent.VK_W) {
			paddle1.setyVelocity(-2);
		} else if (event.getKeyCode() == KeyEvent.VK_S) { // End of if conditional, start of else if conditional
			paddle1.setyVelocity(2);
		} // End of else if conditional
	}

	/*
	 * Overrides the keyReleased method
	 * Reads the KeyEvent from the keylistener in the Panel constructor for a key being released and uses it to stop the movement of each player
	 * 
	 * @param KeyEvent
	 * @return Void
	 */
	@Override
	public void keyReleased(KeyEvent event) {
		
		// Start of if conditional
		if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setyVelocity(0);
		} // End of if conditional
		
		// Start of if conditional
		if (event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setyVelocity(0);
		} // End of if conditional
	}

	/*
	 * Auto-Generated Override method
	 * 
	 * @param KeyEvent
	 * @return Void
	 */
	@Override
	public void keyTyped(KeyEvent event) {
		// Auto-generated method stub
	}

	/*
	 * Method used to update and repaint the game whenever any action is performed
	 * 
	 * @param ActionEvent
	 * @return Void
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
	}

	/*
	 * Method used to paint the objects to the game window
	 * 
	 * @param Graphics
	 * @return Void
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if (gameState != GameState.INITIALISING) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
			g.setColor(FONT_COLOUR);
			paintScores(g);
			paintWin(g);
		}
	}

	/*
	 * Method used to move the object based on the positioning and velocity of the parameter object
	 * 
	 * @param Sprite game object
	 * @return Void
	 */
	public void moveObject(Sprite obj) {
		obj.setxPosition(obj.getxPosition() + obj.getxVelocity(), getWidth());
		obj.setyPosition(obj.getyPosition() + obj.getyVelocity(), getHeight());
	}

	/*
	 * Method to check if the ball has hit either side of the screen and adds a point to either players score
	 * Resets the ball back to the original position
	 * 
	 * @param KeyEvent
	 * @return Void
	 */
	public void checkWallBounce() {
		
		// Start of if conditional
		if (ball.getxPosition() <= 0) {
			// Hit left side of screen
			ball.setxVelocity(-ball.getxVelocity());
			addScore(Player.TWO);
			resetBall();
		} else if (ball.getxPosition() >= getWidth() - ball.getWidth()) { // End of if conditional, start of else if conditional
			// Hit right side of screen
			ball.setxVelocity(-ball.getxVelocity());
			addScore(Player.ONE);
			resetBall();
		} // End of else if conditional
		
		// Start of if conditional
		if (ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getHeight()) {
			// Hit top or bottom of screen
			ball.setyVelocity(-ball.getyVelocity());
		} // End of if conditional
	}

	/*
	 * Method to set the ball back into its original position on the game board
	 * 
	 * @param None
	 * @return Void
	 */
	public void resetBall() {
		ball.resetToInitialPosition();
	}

	/*
	 * Method to check if the ball has bounced off a paddle and sets movement to the opposite direction
	 * 
	 * @param None
	 * @return Void
	 */
	public void checkPaddleBounce() {
		
		// Start of if conditional
		if (ball.getxVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
			ball.setxVelocity(BALL_MOVEMENT_SPEED);
		} // End of if conditional
		
		// Start of if conditional
		if (ball.getxVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
			ball.setxVelocity(-BALL_MOVEMENT_SPEED);
		} // End of if conditional
	}

	/*
	 * Method to add a single point to a players score
	 * 
	 * @param Player Enumerator
	 * @return Void
	 */
	private void addScore(Player p) {
		
		// Start of if conditional
		if (p == Player.ONE) {
			player1Score++;
		} else if (p == Player.TWO) { // End of if conditional, start of else if conditional
			player2Score++;
		} // End of else if conditional
	}

	/*
	 * Method to check if the game has been won by checking a players score against the points required to win constant
	 * 
	 * @param KeyEvent
	 * @return Void
	 */
	private void checkWin() {
		
		// Start of if conditional
		if (player1Score >= POINTS_TO_WIN) {
			gameWinner = Player.ONE;
			gameState = GameState.GAMEOVER;
		} else if (player2Score >= POINTS_TO_WIN) { // End of if conditional, start of else if conditional
			gameWinner = Player.TWO;
			gameState = GameState.GAMEOVER;
		} // end of else if conditional
	}

	/*
	 * Method used to paint the scores onto the game window
	 * 
	 * @param Graphics
	 * @return Void
	 */
	private void paintScores(Graphics g) {
		Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		g.setFont(scoreFont);
		g.drawString(leftScore, SCORE_TEXT_X, SCORE_TEXT_Y);
		g.drawString(rightScore, getWidth() - SCORE_TEXT_X, SCORE_TEXT_Y);
	}

	/*
	 * Method used to paint the game winner onto the game window
	 * 
	 * @param Graphics
	 * @return Void
	 */
	private void paintWin(Graphics g) {
		
		// Start of if conditional
		if (gameWinner != null) {
			Font winnerFont = new Font(WINNER_FONT_FAMILY, Font.BOLD, WINNER_FONT_SIZE);
			g.setFont(winnerFont);
			int xPosition = getWidth() / 2;
			
			// Start of if conditional
			if (gameWinner == Player.ONE) {
				xPosition -= WINNER_TEXT_X;
			} else if (gameWinner == Player.TWO) { // End of if conditional, start of else if conditional
				xPosition += WINNER_TEXT_X;
			} // End of if conditional
			
			g.drawString(WINNER_TEXT, xPosition, WINNER_TEXT_Y);
		} // End of if conditional
	}

}