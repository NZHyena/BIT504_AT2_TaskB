//TODO:Commenting
import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
	// Creating object attributes
	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height;
	private Color colour;
	private int initialXPosition, initialYPosition;

	// Getters
	public int getxPosition() {
		return xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public int getxVelocity() {
		return xVelocity;
	}
	public int getyVelocity() {
		return yVelocity;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Color getColour() {
		return colour;
	}
	
	// Setters
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setColour(Color colour) {
		this.colour = colour;
	}
	
	/*
	 * Method to set the x position of a sprite object
	 * 
	 * @param newX integer to represent the new x coordinate of the sprite, panelWidth integer to represent the width of the game window
	 * @return void
	 */
	public void setxPosition(int newX, int panelWidth) {
		
		// Initializing method variable
		xPosition = newX;
		
		// Start of if conditional
		if (xPosition < 0) {
			xPosition = 0;
		} else if (xPosition + width > panelWidth) { // End of if conditional, start of else if conditional
			xPosition = panelWidth - width;
		} // End of else if conditional
	}

	/*
	 * Method to set the y position of a sprite object
	 * 
	 * @param newY integer to represent the new Y coordinate of the sprite, panelHeight integer to represent the height of the game window
	 * @return void
	 */
	public void setyPosition(int newY, int panelHeight) {
		// Initializing method variables
		yPosition = newY;
		
		// Start of if conditional
		if (yPosition < 0) {
			yPosition = 0;
		} else if (yPosition + height > panelHeight) { // End of if conditional, Start of else if conditional
			yPosition = panelHeight - height;
		} // End of else if conditional
	}

	/*
	 * Method to set the initial x and y coordinates of a sprite object
	 * 
	 * @param initalX integer to set the starting x coordinate of the sprite, initalY integer to set the starting y coordinate of the sprite
	 * @return void
	 */
	public void setInitialPosition(int initialX, int initialY) {
		initialYPosition = initialY;
		initialXPosition = initialX;
	}

	/*
	 * Method to reset a sprite object back to it's initial position within the game window
	 * 
	 * @param none
	 * @return void
	 */
	public void resetToInitialPosition() {
		setxPosition(initialXPosition);
		setyPosition(initialYPosition);
	}

	/*
	 * Method to create the rectangular shape of all sprite objects since they are all rectangular in nature
	 * 
	 * @param None
	 * @return A instance of a rectangle object 
	 */
	public Rectangle getRectangle() {
		return new Rectangle(getxPosition(), getyPosition(), getWidth(), getHeight());
	}
}