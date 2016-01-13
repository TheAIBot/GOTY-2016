package Game.Model.Board;


import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import Game.Model.Resources.ResourceImages;
import Game.View.Colorfull;
import Game.View.Displayable;
import Game.View.Numreable;
import Game.View.Animation.AnimationInfo;
import Game.View.Animation.ToAnimateListener;

public class Tile implements java.io.Serializable, Displayable, Numreable, Colorfull, AnimationInfo {
	private int scallingX = 1;
	private int scallingY = 1;
	private final Point2D.Double[] corners;
	private final int number;
	transient Point2D.Double position;	
	private final Color color;
	private final Polygon colorPolygon;
	private static transient BufferedImage displayImage;
	private Point2D.Double previousPosition;
	private ToAnimateListener listener;
	private boolean finishedMoving = true;
	
	
	public Tile(int number, Point2D.Double position, Color color, BufferedImage displayImage)
	{
		this(number, position, color);
		Tile.displayImage = displayImage;
		if (displayImage != null) {
			scallingX = displayImage.getWidth();
			scallingY = displayImage.getHeight();
		}
	}
		
 	public Tile(ToAnimateListener listener, int number, Point2D.Double position, Color color, BufferedImage displayImage)
	{
		this(number, position, color, displayImage);
		this.listener = listener;
	}
	
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

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        displayImage = ImageIO.read(in);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(displayImage, ResourceImages.ACCEPTED_EXTENSION, out);
    }

	public BufferedImage getDisplayImage() {
		return displayImage;
	}
	
	public int getNumber(){
		return number;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void translatePosition(int x, int y)
	{
		setPosition(new Point2D.Double(position.x + x, position.y + y));
	}
	
	public void setPosition(Point2D.Double newPosition)
	{
		if (finishedMoving) {
			previousPosition = position;
			finishedMoving = false;
			listener.toAnimate(this);
		}
		position = new Point2D.Double(newPosition.x, newPosition.y);
	}
	
	public Point2D.Double getDisplayPosition()
	{
		return previousPosition;
	}
	
	public Point2D.Double getColorPosition() {
		return this.previousPosition;
	}
	
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

	public Polygon getColorPolygon(){
		return colorPolygon;
	}

	public Point2D.Double[] getColorCorners() {
		return corners;
	}

	@Override
	public double getColorPolygonScallingX() {
		return scallingX;
	}

	@Override
	public double getColorPolygonScallingY() {
		return scallingY;
	}


	@Override
	public double getNumberDisplayScallingX() {
		return scallingX;
	}

	@Override
	public double getNumberDisplayScallingY() {
		return scallingY;
	}
	
	@Override
	public Point2D.Double getPreviousPosition() {
		return previousPosition;
	}

	@Override
	public void finishedMoving() {
		finishedMoving = true;
		
	}

	@Override
	public Double getPosition() {
		return this.position;
	}
}
