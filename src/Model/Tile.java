package Model;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tile implements java.io.Serializable, Displayable {
	
	private int number;
	Point position;	
	Color color;	
	BufferedImage displayImage;
	int size = 100;
	
	public Tile(int number, Point position, Color color)
	{
		this.number = number;
		this.position = position;
		System.out.println(position.toString());
		this.color = color;	
		setCurrentImage("res/tempchest.png");
		displayImage.createGraphics().drawString("" + number, displayImage.getHeight()/2, displayImage.getWidth()/4);
	}
	
	public BufferedImage getDisplay(){
		return displayImage;
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
}
