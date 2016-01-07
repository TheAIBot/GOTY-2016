package Model;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> refs/remotes/origin/Dev
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
<<<<<<< HEAD
=======
>>>>>>> refs/remotes/origin/Andreas
>>>>>>> refs/remotes/origin/Dev

import Control.*;

public class Tile implements java.io.Serializable, Displayable {
	
	private int number;
	transient Point position;	
	Color color;	
	transient BufferedImage displayImage;
	int size = 100;
	
	public Tile(int number, Point position, Color color)
	{
		this.number = number;
		this.position = position;
		this.color = color;	
		createImage();
	}
	
	public BufferedImage getDisplay(){
		return displayImage;
	}

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        createImage();
    }
	
	private void createImage()
	{
		setCurrentImage("res/tempchest.png");
		displayImage.createGraphics().drawString("" + number, displayImage.getHeight()/2, displayImage.getWidth()/4);
	}
	
	private boolean setCurrentImage(String filePath) { //Basseret på oracles beskrivelse
		File imageFile = new File(filePath);
		if (imageFile.exists() && imageFile.isFile()) {
			try {
				displayImage = ImageIO.read(imageFile);
				return true;
			} catch (Exception e) {
				System.out.println("Something went wrong with the image loading process");
			}
		} else {
			System.out.println("Something went wrong with the image loading process");
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
	
	public int getNumber(){
		return number;
	}

	public Point getPosition()
	{
		return position;
	}
}
