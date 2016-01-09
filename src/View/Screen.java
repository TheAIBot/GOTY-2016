package View;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Model.Displayable;
import Model.RenderInfo;
import Model.Tile;

public class Screen {

	private Rectangle imageBound;
	private Graphics2D gDisplay;
	private Point vantagePoint;
	//final int screenSize = 500;
	private double imageScaling = 1;

	public int xOffset, yOffset;

	public final static int TILE_POSITION_TO_PIXEL_POSITION = 100;

	public Screen(Graphics2D gDisplay, Rectangle imageBound) {
		this.gDisplay = gDisplay;
		this.imageBound = imageBound;
	}

	/**
	 * Renders a displayable object onto the screen. If the object is in a
	 * position where, even if it is rendred, the displayable can't be shown, it
	 * returns false, and doesen't render the object. If it is in a position
	 * where it can be seen, it renders the object and returns true.
	 * 
	 * ((*) Errors)
	 * 
	 * @param d
	 */
	public boolean render(Displayable d, RenderInfo renderInfo) {
		//Checks if the displayable has the neccesary information required for displaying it on the screen.
		if (d == null) {
			throw new NullPointerException();
		}
		BufferedImage currentImage = d.getDisplayImage();
		Point imagePosition = d.getPosition();
		if (currentImage == null || imagePosition == null) {
			throw new NullPointerException();
		} else {
			//Checks if the displayable is in a position, so that the image can be displayed on the screen. 
			//If not, it dosen't render it (to increase performance), else it does.
			if (isInsideDisplay(d)) {
				if (!renderInfo.renderColor) {
					gDisplay.drawImage(currentImage, (int) (imagePosition.x * imageScaling * currentImage.getWidth()),
													 (int) (imagePosition.y * imageScaling * currentImage.getHeight()), null);
				} else {
					gDisplay.setColor(d.getColor());
					gDisplay.fillRect((int) (imagePosition.x * imageScaling * currentImage.getWidth()),
									  (int) (imagePosition.y * imageScaling * currentImage.getHeight()), 
									  currentImage.getWidth(), 
									  currentImage.getHeight());
				}
				gDisplay.setColor(Color.WHITE);
				gDisplay.drawString(String.valueOf(d.getNumber()), (int) (imagePosition.x * imageScaling * currentImage.getWidth() + currentImage.getWidth() / 2),
											     				   (int) (imagePosition.y * imageScaling * currentImage.getHeight() + currentImage.getHeight() / 4));
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * @param currentImage
	 * @param imagePosition
	 * @return
	 */
	public boolean isInsideDisplay(Displayable d) { //Rename? (*)
		//Checks if any of the points along the image bounds of image to be displayed is inside the bounds of the display,
		//with the vantage point representing the middle of the display.
		BufferedImage dImage = d.getDisplayImage();
		Point imagePosition =  d.getPosition();
		Point[] corners = d.getCorners();
		for (Point corner : corners) {
			if ((corner.x + imagePosition.x) * dImage.getWidth() * imageScaling <= imageBound.getWidth() &&
				(corner.y + imagePosition.y) * dImage.getHeight() * imageScaling <= imageBound.getHeight()) {
				return true;
			}
		}
		return false;
		/*for (Point corner : corners) {
			corner.translate( (int) (dImage.getWidth()*(corner.x + imagePosition.x)*imageScaling), 
					(int) (dImage.getHeight()*(corner.y + imagePosition.y)*imageScaling));
			if (!imageBound.contains(corner)) {
				return false;
			}
			corner.translate( (int) -(dImage.getWidth()*corner.x*imageScaling), (int) -(dImage.getHeight()*corner.y*imageScaling));
		}*/
		//return true;	
		
		

		//((*)Might be an error depending on the placement of the imageBound)
		/*
		if (imagePosition.x < imageBound.width && imagePosition.y < imageBound.height) {
			return true;
		}
		return false;
		*/
	}

	/**
	 * Clears the display, coloring everything white.
	 */
	public void clear() {
		Color currentColor = gDisplay.getColor();
  		gDisplay.setColor(Color.WHITE);		
 		gDisplay.fillRect(0, 0, imageBound.width, imageBound.height);
  		gDisplay.setColor(currentColor);
		//gDisplay.clearRect(0, 0, imageBound.x, imageBound.y);
	}

	public void windowResized(Rectangle newSize, Graphics2D newGDisplay) {
		this.imageBound = newSize;
		this.gDisplay = newGDisplay;
	}
}
