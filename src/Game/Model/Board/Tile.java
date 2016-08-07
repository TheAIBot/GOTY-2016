package Game.Model.Board;


import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

import Game.Model.Animation.AnimationInfo;
import Game.Model.Animation.ToAnimateListener;
import Game.View.ViewTypes.Colorfull;
import Game.View.ViewTypes.Displayable;
import Game.View.ViewTypes.Numreable;

public class Tile implements java.io.Serializable, Displayable, Numreable, Colorfull, AnimationInfo {
	private static final long serialVersionUID = -3423525350188897586L;
	// contains all the corners of the polygon that has to be shown
	//used to detect whether the tile should be shown on the screen or not
	private final Point[] corners;
	private int number;
	//This is the position that the want to move to
	Point2D.Double goingTowardsPosition;	
	//contains the position the tile is at
	private Point2D.Double currentPosition;
	// is the color of the tile as it's shown on the screen when there is a GUI
	private final Color color;
	//contains the image all the tiles will be a part of
	private static transient BufferedImage displayImage;
	private ToAnimateListener listener;
	// is true when the tile is not animating and false when it's animating
	private boolean finishedMoving = true;
	
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
		this.corners = new Point[]{new Point(0, 0),
								   new Point(0, 1),
								   new Point(1, 1),
				  			  	   new Point(1, 0)
				  			  	   };
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
	 * Returns the corners of the tiles. This is from the top left corner of the rectangle/square that holds the tile,
	 * and is in percents (as in a corner being in the middle of the square has the x coordinate 0.5, and the y coordinate 0.5 ).
	 */
	public Point[] getCorners() {
		return corners;
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
