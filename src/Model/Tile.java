package Model;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.imageio.ImageIO;


import Control.GameEngine.Log;

public class Tile implements java.io.Serializable, Displayable {
	
	private Point[] corners;
	private int number;
	transient Point position;	
	Color color;

	private static transient BufferedImage displayImage;
	//private int size = 100;
	
	public Tile(int number, Point position, Color color)
	{
		this.number = number;
		this.position = position;
		this.color = color;	
		createImage();
		corners = new Point[]{new Point(position.x, position.y),
				  new Point((position.x + 1), position.y),
				  new Point(position.x, (position.y + 1)), 
				  new Point((position.x + 1), (position.y + 1))};
	}

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        createImage();
    }
	

	private void createImage()
	{
		setCurrentImage("res/tempchest.png");
	}
	
	private boolean setCurrentImage(String filePath) { //Basseret på oracles beskrivelse
		if (displayImage == null) {
			File imageFile = new File(filePath);
			if (imageFile.exists() && imageFile.isFile()) {
				try {
					displayImage = ImageIO.read(imageFile);
					return true;
				} catch (Exception e) {
					Log.writeln("Something went wrong with the image loading process");
					Log.writeError(e);
				}
			} else {
				Log.writeln("file doesn't exist or is not a file");
			}
		}	
		return false;
	}

	public BufferedImage getDisplayImage() {
		return displayImage;
	}

	/**
	 * tile precision
	 */
	public Point getImagePosition() {
		return new Point(position.x, position.y);
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
}
