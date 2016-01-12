package Game.Model.Board;


import java.awt.Color;
import java.awt.Point;
<<<<<<< HEAD
import java.awt.geom.Point2D;
=======
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
>>>>>>> refs/remotes/origin/Jesper
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

<<<<<<< HEAD
public class Tile implements java.io.Serializable, Displayable, Numreable, AnimationInfo {
=======
public class Tile implements java.io.Serializable, Displayable, Numreable, Colorfull {
>>>>>>> refs/remotes/origin/Jesper
	
	private int scallingX = 1;
	private int scallingY = 1;
	private Point[] corners;
	private int number;
	transient Point2D.Double position;	
	private Color color;
	private Polygon colorPolygon;
	private static transient BufferedImage displayImage;
	private Point2D.Double previousPosition;
	private ToAnimateListener listener;
	private boolean finishedMoving = true;
	
	
<<<<<<< HEAD
 	public Tile(ToAnimateListener listener, int number, Point2D.Double position, Color color, BufferedImage displayImage)
	{
		this(number, position, color);
		Tile.displayImage = displayImage;
		this.listener = listener;
=======
	//Skal den ikke bare altid bruge denne?
	public Tile(int number, Point position, Color color, BufferedImage displayImage)
	{
		this(number, position, color);
		Tile.displayImage = displayImage;
		if (displayImage != null) {
			scallingX = displayImage.getWidth();
			scallingY = displayImage.getHeight();
		}
>>>>>>> refs/remotes/origin/Jesper
	}
	
	public Tile(int number, Point2D.Double position, Color color)
	{
		this.number = number;
		this.position = position;
		this.previousPosition = position;
		this.color = color;	
		corners = new Point[]{new Point(0, 0),
				  			  new Point(1, 0),
				  			  new Point(1, 1),
				  			  new Point(0, 1)};
		colorPolygon = new Polygon();
		for (Point corner : corners) {
			colorPolygon.addPoint(corner.x, corner.y);
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
<<<<<<< HEAD

	public Point2D.Double getPosition()
	{
		return position;
=======
	
	public Color getColor() {
		return color;
>>>>>>> refs/remotes/origin/Jesper
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
	
	public Point getColorPosition() {
		return this.position;
	}
	
	public Point getNumberPosition() {
		return this.position;
	}
	
	/**
	 * Returns the corners of the tiles. This is from the top left corner of the rectangle/square that holds the tile,
	 * and is in percents (as in a corner being in the middle of the square has the x coordinate 0.5, and the y coordinate 0.5 ).
	 */
	public Point[] getCorners() {
		return corners;
	}

	public Polygon getColorPolygon(){
		return colorPolygon;
	}

	public Point[] getColorCorners() {
		// TODO Auto-generated method stub
		return corners;
	}

	@Override
	public int getColorPolygonScallingX() {
		return scallingX;
	}

	@Override
	public int getColorPolygonScallingY() {
		return scallingY;
	}

	@Override
	public int getNumberDisplayScallingX() {
		return scallingX;
	}

	@Override
	public int getNumberDisplayScallingY() {
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

}
