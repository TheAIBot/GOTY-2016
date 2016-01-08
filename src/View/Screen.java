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
	final int screenSize = 500;
	private Rectangle screenBounds;
	final int imgSize = 100;
	
	public int xOffset, yOffset;
	
	public final static int TILE_POSITION_TO_PIXEL_POSITION = 100;

	public Screen(Graphics2D gDisplay, Point imageBounds) {
		this.gDisplay = gDisplay;
		this.imageBounds = imageBounds;
		xOffset = 0;
		yOffset = 0;
		screenBounds = new Rectangle(0, 0, screenSize, screenSize);
	}
	
	
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
			
			Point p1 = new Point(imagePosition.x * imgSize, imagePosition.y * imgSize);
			Point p2 = new Point((imagePosition.x + 1) * imgSize, imagePosition.y * imgSize);
			Point p3 = new Point(imagePosition.x * imgSize, (imagePosition.y + 1) * imgSize);
			Point p4 = new Point((imagePosition.x + 1) * imgSize, (imagePosition.y + 1) * imgSize);
			
			if (screenBounds.contains(p1) || screenBounds.contains(p2) || screenBounds.contains(p3) || screenBounds.contains(p4)) {
				gDisplay.drawImage(currentImage, (imagePosition.x + xOffset) * imgSize, (imagePosition.y + yOffset) * imgSize, null);
				//System.out.println("AAA");
				return true;
			}
			//System.out.println("XXX");
			return false;
		}
	}
	
	
	/**
	 * Renders a displayable object onto the screen. If the object is in a position where, 
	 * even if it is rendred, the displayable can't be shown, it returns false, and doesen't render the object.
	 * If it is in a position where it can be seen, it renders the object and returns true.
	 * 
	 * ((*) Errors)
	 * @param d
	 */
	/*public boolean render(Displayable d) {
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
			double imgSize = 1;
			
			Point p1 = new Point((int) (imagePosition.x * imgSize), (int) (imagePosition.y * imgSize));
			Point p2 = new Point((int) ((imagePosition.x + 1) * imgSize), (int) (imagePosition.y * imgSize));
			Point p3 = new Point((int) (imagePosition.x * imgSize), (int) ((imagePosition.y + 1) * imgSize));
			Point p4 = new Point((int) ((imagePosition.x + 1) * imgSize), (int) ((imagePosition.y + 1) * imgSize));
			
			if (screenBounds.contains(p1) || screenBounds.contains(p2) || screenBounds.contains(p3) || screenBounds.contains(p4)) {
				gDisplay.drawImage(currentImage, (int) ((imagePosition.x + xOffset) * imgSize), (int) ((imagePosition.y + yOffset) * imgSize), null);
				System.out.println("AAA");
				return true;
			}
			System.out.println("XXX");
			return false;
		}
	}*/
	
	
	/**
	 * @param currentImage
	 * @param imagePosition
	 * @return
	 */
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
		Color currentColor = gDisplay.getColor();
		gDisplay.setColor(Color.WHITE);
		gDisplay.fillRect(0, 0, imageBounds.x, imageBounds.y);
		gDisplay.setColor(currentColor);
	}

	//Duplicate of render()
	public void renderTile(Displayable d) {
		if (d == null) {
			throw new Error();
		}
		BufferedImage currentImage = d.getDisplayImage();
		if (currentImage == null || d.getImagePosition() == null) {
			throw new Error();
		} else {
			Point imagePosition = d.getImagePosition();
			gDisplay.drawImage(currentImage, imagePosition.x + xOffset, imagePosition.y + yOffset, null);
		}
	}
	
	/**
	 * @param xOffset
	 * @param yOffset
	 */
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		screenBounds = new Rectangle(this.xOffset, this.yOffset, screenSize, screenSize);
		
		//vantagePoint.translate(xOffset, yOffset);
	}
	
	/**
	 * @param xOffset
	 * @param yOffset
	 */
	public void addOffset(int xOffset, int yOffset) {
		this.xOffset += xOffset;
		this.yOffset += yOffset;
		
		screenBounds = new Rectangle(this.xOffset, this.yOffset, screenSize, screenSize);
		
		String str = "";
		//vantagePoint.translate(xOffset, yOffset);
	}
	


}
