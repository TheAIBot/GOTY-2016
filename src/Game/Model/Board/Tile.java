package Game.Model.Board;


import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import Game.Model.Resources.ResourceImages;
import Game.View.Colorfull;
import Game.View.Displayable;
import Game.View.Numreable;

public class Tile implements java.io.Serializable, Displayable, Numreable, Colorfull {
	
	private int scallingX = 1;
	private int scallingY = 1;
	private Point[] corners;
	private int number;
	transient Point position;	
	private Color color;
	private Polygon colorPolygon;
	private static transient BufferedImage displayImage;
	
	
	//Skal den ikke bare altid bruge denne?
	public Tile(int number, Point position, Color color, BufferedImage displayImage)
	{
		this(number, position, color);
		Tile.displayImage = displayImage;
		if (displayImage != null) {
			scallingX = displayImage.getWidth();
			scallingY = displayImage.getHeight();
		}
	}
	
	public Tile(int number, Point position, Color color)
	{
		this.number = number;
		this.position = position;
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
	
	public Color getColor() {
		return color;
	}
	
	public Point getDisplayPosition()
	{
		return position;
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
}
