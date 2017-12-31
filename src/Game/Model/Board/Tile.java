package Game.Model.Board;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import Game.Model.Animation.AnimationInfo;
import Game.Model.Animation.ToAnimateListener;
import Game.View.ViewTypes.Colorfull;
import Game.View.ViewTypes.Displayable;
import Game.View.ViewTypes.Numreable;

public class Tile implements java.io.Serializable, Displayable, Numreable, Colorfull, AnimationInfo {
	private static final long serialVersionUID = -3423525350188897586L;
	
	public static final int DEFAULT_TILE_SIZE = 100; //the size of a tile in pixels
	//contains the image all the tiles will be a part of
	private static transient BufferedImage displayImage;
	private int number;
	//This is the position that the tile want to move to
	Point2D.Double goingTowardsPosition;	
	//contains the position the tile is at
	private Point2D.Double currentPosition;
	// is the color of the tile as it's shown on the screen when there is a GUI
	private final Color color;
	private ToAnimateListener listener;
	// is true when the tile is not animating and false when it's animating
	private boolean finishedMoving = true;
	private BufferedImage colorImage;
	
	/**
	 * this constructor is only used in tests
	 * @param number the number this tile contains
	 * @param position the start position of this tile
	 * @param color the color of this tile
	 * @param displayImage the imagee the tiles has to create when shown on the screen sorted
	 */
	public Tile(int number, Point2D.Double position, Color color, BufferedImage displayImage)
	{
		this(number, position, color);
		Tile.displayImage = displayImage;
	}
	
	/**
	 * 
	 * @param listener the listener that listens to Animate vents from this tilesss
	 * @param number the number this tile contains
	 * @param position the start position of this tile
	 * @param color the color of this tile
	 * @param displayImage the imagee the tiles has to create when shown on the screen sorted
	 */
 	public Tile(ToAnimateListener listener, int number, Point2D.Double position, Color color, BufferedImage displayImage)
	{
		this(number, position, color, displayImage);
		this.listener = listener;
	}
	
 	/**
 	 * @param number the number this tile contains
	 * @param position the start position of this tile
	 * @param color the color of this tile
 	 */
	public Tile(int number, Point2D.Double position, Color color)
	{
		this.number = number;
		this.goingTowardsPosition = position;
		this.currentPosition = new Point2D.Double(position.x, position.y);
		this.color = color;
		colorImage = new BufferedImage(DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE, BufferedImage.TYPE_3BYTE_BGR);
		Graphics fisk = colorImage.getGraphics();
		fisk.setColor(color);
		fisk.fillRect(0, 0, DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE);
		fisk.setColor(Color.BLACK);
		fisk.setFont(fisk.getFont().deriveFont(0, 30));
		String numberAsString = String.valueOf(this.number);
		Graphics2D g2 = (Graphics2D)fisk;
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		fisk.drawString(numberAsString, DEFAULT_TILE_SIZE / 2 - numberAsString.length() * 10, DEFAULT_TILE_SIZE / 2);
		fisk.dispose();
	}
	
	/**
	 * returns the number this tile contains
	 */
	public int getNumber(){
		return number;
	}
	
	/**
	 * returns the color this tile contains
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * moves the tile by the speificed x and y numbers
	 * @param x how much to translate it on the x axis
	 * @param y how much to translate it on the y axis
	 */
	public void translatePosition(int x, int y)
	{
		setPosition(goingTowardsPosition.x + x, goingTowardsPosition.y + y);
	}
	
	/**
	 * Sets the position of the tile and adds it to the queue of objects to be animated 
	 * @param newPosition a new point to set the position.
	 * This point must not be a refrence of a tiles position as that
	 * will make the animation unable to animate this tiles movement correctly 
	 */
	private void setPosition(double x, double y)
	{
		if (finishedMoving) {
			currentPosition.x = goingTowardsPosition.x;
			currentPosition.y = goingTowardsPosition.y;
			finishedMoving = false;
			listener.toAnimate(this);
		}
		goingTowardsPosition.x = x;
		goingTowardsPosition.y = y;
	}
	
	public Point2D.Double getCurrentPosition()
	{
		return this.currentPosition;
	}

	/**
	 * allows this tile to animate again
	 * @Override
	 */
	public void finishedMoving() {
		finishedMoving = true;
		
	}

	/**
	 * returns the position this tile wants to go to.
	 * @Override
	 */
	public Point2D.Double getGoingTowardsPosition() {
		return this.goingTowardsPosition;
	}

	/**
	 * get the image all tiles uses
	 * @return til image
	 */
	public static BufferedImage getTileImage()
	{
		return displayImage;
	}
	
	public BufferedImage getImage()
	{
		return displayImage;
	}
	
	public BufferedImage getColorImage()
	{
		return colorImage;
	}
	
	/**
	 * sets the image all tiles have to use
	 * @param image  the new image
	 */
	public static void setTileImage(BufferedImage image)
	{
		displayImage = image;
	}

	/**
	 * sets this tiles number
	 * @param newNumber  the new number
	 */
	public void setNumber(int newNumber)
	{
		number = newNumber;
	}
}
