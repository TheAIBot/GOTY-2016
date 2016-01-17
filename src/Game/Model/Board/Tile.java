package Game.Model.Board;


import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

import Game.View.Colorfull;
import Game.View.Displayable;
import Game.View.Numreable;
import Game.View.Animation.AnimationInfo;
import Game.View.Animation.ToAnimateListener;

public class Tile implements java.io.Serializable, Displayable, Numreable, Colorfull, AnimationInfo {
	private static final long serialVersionUID = -3423525350188897586L;
	// contains all the corners of the polygon that has to be shown
	//used to detect wether the tile should be shown on the screen or not
	private final Point2D.Double[] corners;
	private int number;
	//This is the position that the want to move to
	Point2D.Double position;	
	// is the color of the tile as it's shown on the screen when there is a GUI
	private final Color color;
	//contains the shape of the tile when drawing the color ofthe tile
	private final Polygon colorPolygon;
	//contains the image all the tiles will be a part of
	private static transient BufferedImage displayImage;
	//contains the position the tile is at
	private Point2D.Double previousPosition;
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
		this.position = position;
		this.previousPosition = position;
		this.color = color;	
		this.corners = new Point2D.Double[]{new Point2D.Double(0, 0),
				  			  				new Point2D.Double(1, 0),
				  			  				new Point2D.Double(1, 1),
				  			  				new Point2D.Double(0, 1)};
		this.colorPolygon = new Polygon();
		for (Point2D.Double corner : corners) {
			colorPolygon.addPoint((int) corner.x, (int) corner.y);
		}
	}

	/**
	 * return the image to be displayed on the screen
	 */
	public BufferedImage getDisplayImage() {
		return displayImage;
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
		//set position requires a new point as to prevent previsouPoisition
		//and Position from pointing the the same object as that would 
		//make it unable to animate the tiles movement
		setPosition(new Point2D.Double(position.x + x, position.y + y));
	}
	
	/**
	 * Sets the position of the tile and adds it to the queue of objects to be animated 
	 * @param newPosition a new point to set the position.
	 * This point must not bea refrence of a tiles position as that
	 * will make the animation unable to animate this tiles movement correctly 
	 */
	public void setPosition(Point2D.Double newPosition)
	{
		if (finishedMoving) {
			previousPosition = position;
			finishedMoving = false;
			listener.toAnimate(this);
		}
		position = new Point2D.Double(newPosition.x, newPosition.y);
	}
	
	/**
	 * returns the current position of the time
	 */
	public Point2D.Double getDisplayPosition()
	{
		return previousPosition;
	}
	
	/**
	 * returns the current position of the time
	 */
	public Point2D.Double getColorPosition() {
		return this.previousPosition;
	}
	
	/**
	 * returns the current position of the time
	 */
	public Point2D.Double getNumberPosition() {
		return this.previousPosition;
	}
	
	/**
	 * Returns the corners of the tiles. This is from the top left corner of the rectangle/square that holds the tile,
	 * and is in percents (as in a corner being in the middle of the square has the x coordinate 0.5, and the y coordinate 0.5 ).
	 */
	public Point2D.Double[] getCorners() {
		return corners;
	}

	/**
	 * returns the polygon of this tile
	 */
	public Polygon getColorPolygon(){
		return colorPolygon;
	}

	/**
	 * returns the corners of the tile
	 */
	public Point2D.Double[] getColorCorners() {
		return corners;
	}
	
	/**
	 * returns this tiles current position
	 * @Override
	 */
	public Point2D.Double getPreviousPosition() {
		return previousPosition;
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
	public Double getPosition() {
		return this.position;
	}

	/**
	 * get the image all tiles uses
	 * @return til image
	 */
	public static BufferedImage getTileImage()
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
