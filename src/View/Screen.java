package View;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Model.Displayable;

public class Screen {

	private Rectangle imageBound;
	private Point imageBounds; //To delete, replaced with rectangel
	public Graphics2D gDisplay;
	private Point vantagePoint;
	
	public final static int TILE_POSITION_TO_PIXEL_POSITION = 50;

	public Screen(Graphics2D gDisplay, Rectangle imageBound) {
		this.gDisplay = gDisplay;
		gDisplay.setBackground(Color.WHITE);
		this.imageBound = imageBound;
	}
	
	/**
	 * Renders a displayable object onto the screen. If the object is in a position where, 
	 * even if it is rendred, the displayable can't be shown, it returns false, and doesen't render the object.
	 * If it is in a position where it can be seen, it renders the object and returns true.
	 * 
	 * ((*) Errors)
	 * @param d
	 */
	public boolean render(Displayable d) {
		//Checks if the displayable has the neccesary information required for displaying it on the screen.
		if (d == null) {
			throw new NullPointerException();
		}
		BufferedImage currentImage = d.getDisplayImage();
		Point imagePosition = d.getImagePosition();
		if (currentImage == null || imagePosition == null) {
			throw new NullPointerException();
		} else {
			//Checks if the displayable is in a position, so that the image can be displayed on the screen. 
			//If not, it dosen't render it (to increase performance), else it does.
			if (true) { 
				//isInsideDisplay(currentImage, imagePosition)
				gDisplay.drawImage(currentImage, imagePosition.x, imagePosition.y, null);
				gDisplay.drawString(String.valueOf(d.getNumber()),
									imagePosition.x + currentImage.getHeight()/2, 
									imagePosition.y + currentImage.getWidth()/4);
				return true;				
			} else {
				return false;
			}
		}
	}
	
	private boolean isInsideDisplay(BufferedImage currentImage, Point imagePosition){ //Rename? (*)
		//Checks if any of the points along the image bounds of image to be displayed is inside the bounds of the display,
		//with the vantage point representing the middle of the display.
		
		//((*)Might be an error depending on the placement of the imageBound)
		if (imageBound.contains(imagePosition.x - vantagePoint.x, 
				imagePosition.y - vantagePoint.y) ||
			imageBound.contains(imagePosition.x - vantagePoint.x,
					imagePosition.y - vantagePoint.y - currentImage.getHeight()) ||
			imageBound.contains(imagePosition.x - vantagePoint.x + currentImage.getWidth(), 
					imagePosition.y - vantagePoint.y) ||
			imageBound.contains(imagePosition.x - vantagePoint.x + currentImage.getWidth(),
					imagePosition.y - vantagePoint.y - currentImage.getHeight())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Clears the display, coloring everything white.
	 */
	public void clear() {
		//Color currentColor = gDisplay.getColor();
		//gDisplay.setColor(Color.WHITE);
		//gDisplay.fillRect(0, 0, imageBound.x, imageBound.y);
		//gDisplay.setColor(currentColor);
		gDisplay.clearRect(0, 0, imageBound.x, imageBound.y);
	}

	//Duplicate of render()
	public void renderTile(Displayable d) {
		if (d == null) {
			throw new Error();
		}
		BufferedImage currentImage = d.getDisplayImage();
		if (currentImage == null || d.getImagePosition() == null) {
			throw new NullPointerException();
		} else {
			Point imagePosition = d.getImagePosition();
			gDisplay.drawImage(currentImage, imagePosition.x, imagePosition.y, null);
		}
	}

}
