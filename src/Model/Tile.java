package Model;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.imageio.ImageIO;

<<<<<<< HEAD
import View.Screen;

import Control.*;
=======
import Control.GameEngine.Log;
>>>>>>> refs/remotes/origin/Dev

public class Tile implements java.io.Serializable, Displayable {
	
	private int number;
	transient Point position;	
	Color color;	
<<<<<<< HEAD
	BufferedImage displayImage;
	int size = 100; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
=======
	private static transient BufferedImage displayImage;
	private int size = 100;
>>>>>>> refs/remotes/origin/Dev
	
	public Tile(int number, Point position, Color color)
	{
		this.number = number;
		this.position = position;
		this.color = color;	
<<<<<<< HEAD
		if (number % 2 == 0) setCurrentImage("res/tempchest.png");
		else setCurrentImage("res/hveranden.png");
		displayImage.createGraphics().drawString("" + number, displayImage.getHeight()/2, displayImage.getWidth()/4);
=======
		createImage();
>>>>>>> refs/remotes/origin/Dev
	}

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        createImage();
    }
	
<<<<<<< HEAD

	public BufferedImage getDisplay(){
		return displayImage;
=======
	private void createImage()
	{
		setCurrentImage("res/tempchest.png");
>>>>>>> refs/remotes/origin/Dev
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
		return new Point(position.x * size, position.y * size);
	}
	
	public Point getImagePositionPixelPrecision() {
		return new Point(position.x * size, position.y * size);
	}
	
	public int getNumber(){
		return number;
	}

	public Point getPosition()
	{
		return position;
	}
}
