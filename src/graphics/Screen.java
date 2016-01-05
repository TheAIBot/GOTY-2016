package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;


public class Screen {

	private Point imageBounds;
	public Graphics2D gDisplay;

	public Screen(Graphics2D gDisplay, Point imageBounds) {
		this.gDisplay = gDisplay;
		this.imageBounds = imageBounds;
	}
	
	public void render(Displayable d) {
		if(d==null){
			throw new Error();
		}
		BufferedImage currentImage = d.getDisplayImage();
		if (currentImage == null || d.getImagePosition() == null) {
			throw new Error();
		} else {
			Point imagePosition = d.getImagePosition();
			gDisplay.drawImage(currentImage, imagePosition.x, imagePosition.y, null);
		}
	}	
	
	public void clear() {
		Color currentColor = gDisplay.getColor();
		gDisplay.setColor(Color.WHITE);
		gDisplay.fillRect(0,0, imageBounds.x,imageBounds.y);
		gDisplay.setColor(currentColor);
	}
	
}
