package Game.Model.Board;


import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import Game.Model.Resources.ResourceImages;
import Game.View.AnimationInfo;
import Game.View.Displayable;
import Game.View.Numreable;
import Game.View.ToAnimateListener;

public class Tile implements java.io.Serializable, Displayable, Numreable, AnimationInfo {
	
	private Point[] corners;
	private int number;
	transient Point2D.Double position;	
	private Color color;
	private static transient BufferedImage displayImage;
	private Point2D.Double previousPosition;
	private ToAnimateListener listener;
	private boolean finishedMoving = true;
	
	
 	public Tile(ToAnimateListener listener, int number, Point2D.Double position, Color color, BufferedImage displayImage)
	{
		this(number, position, color);
		Tile.displayImage = displayImage;
		this.listener = listener;
	}
	
	public Tile(int number, Point2D.Double position, Color color)
	{
		this.number = number;
		this.position = position;
		this.previousPosition = position;
		this.color = color;	
		corners = new Point[]{new Point(0, 0),
				  			  new Point(1, 0),
				  			  new Point(0, 1), 
				  			  new Point(1, 1)};
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

	public Point2D.Double getPosition()
	{
		return position;
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
	
	/**
	 * Returns the corners of the tiles. This is from the top left corner of the rectangle/square that holds the tile,
	 * and is in percents (as in a corner being in the middle of the square has the x coordinate 0.5, and the y coordinate 0.5 ).
	 */
	public Point[] getCorners() {
		return corners;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public Point getNumberPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Point2D.Double getPreviousPosition() {
		return previousPosition;
	}

	@Override
	public void finishedMoving() {
		finishedMoving = true;
		
	}

}
