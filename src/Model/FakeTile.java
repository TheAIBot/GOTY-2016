package Model;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import Control.*;


public class FakeTile implements Displayable {
	
	int number;
	Point position;	
	Color color;	
	BufferedImage displayImage;
	int size = 100;
	
	public FakeTile(int number, Point position, Color color) {
		this.number = number;
		this.position = position;
		this.color = color;	
		setCurrentImage("res/tempchest.png");
		displayImage.createGraphics().drawString("" + number, displayImage.getHeight()/2, displayImage.getWidth()/4);
	}
	
	public void render(Screen screen){
		screen.renderTile(this);
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

	public Point getImagePosition() {
		return new Point(position.x * size, position.y * size);
	}	
	
}
