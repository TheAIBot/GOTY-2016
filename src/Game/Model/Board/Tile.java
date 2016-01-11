package Game.Board;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;


import Control.GameEngine.Log;
import Game.Resources.ResourceImages;
import View.Displayable;

public class Tile implements java.io.Serializable, Displayable {
	
	private Point[] corners;
	private int number;
	transient Point position;	
	private Color color;
	private static transient BufferedImage displayImage;
	
	
	public Tile(int number, Point position, Color color, BufferedImage displayImage)
	{
		this(number, position, color);
		Tile.displayImage = displayImage;
	}
	
	public Tile(int number, Point position, Color color)
	{
		this.number = number;
		this.position = position;
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

	public Point getPosition()
	{
		return position;
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
}
