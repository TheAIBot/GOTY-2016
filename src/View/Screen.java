package View;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Model.Displayable;

public class Screen {

	private Rectangle imageBound;
	private Graphics2D gDisplay;
	private Point vantagePoint;
	
	public int xOffset, yOffset;
	
	public final static int TILE_POSITION_TO_PIXEL_POSITION = 100;

	public Screen(Graphics2D gDisplay, Rectangle imageBound) {
		this.gDisplay = gDisplay;
<<<<<<< HEAD
		this.imageBounds = imageBounds;
		xOffset = 0;
		yOffset = 0;
=======
		this.imageBound = imageBound;
>>>>>>> refs/remotes/origin/Dev
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
<<<<<<< HEAD
			
			int imgSize = 100;
			int screenSize = 500;
			
			Rectangle screenBounds = new Rectangle(xOffset * imgSize, yOffset * imgSize, screenSize, screenSize);
			
			Point p1 = new Point(imagePosition.x * imgSize, imagePosition.y * imgSize);
			Point p2 = new Point((imagePosition.x + 1) * imgSize, imagePosition.y * imgSize);
			Point p3 = new Point(imagePosition.x * imgSize, (imagePosition.y + 1) * imgSize);
			Point p4 = new Point((imagePosition.x + 1) * imgSize, (imagePosition.y + 1) * imgSize);
			
			/*Point p1 = new Point(imagePosition.x * imgSize, imagePosition.y * imgSize);
			Point p2 = new Point((imagePosition.x + xOffset) * imgSize, imagePosition.y * imgSize);
			Point p3 = new Point(imagePosition.x * imgSize, (imagePosition.y + yOffset) * imgSize);
			Point p4 = new Point((imagePosition.x + xOffset) * imgSize, (imagePosition.y + yOffset) * imgSize);*/
			
			if (screenBounds.contains(p1) || screenBounds.contains(p2) || screenBounds.contains(p3) || screenBounds.contains(p4)) {
				gDisplay.drawImage(currentImage, (imagePosition.x + xOffset) * imgSize, (imagePosition.y + yOffset) * imgSize, null);
				System.out.println("---");
				return true;
=======
			if (isInsideDisplay(currentImage, imagePosition)) { 
				//isInsideDisplay(currentImage, imagePosition)
				gDisplay.drawImage(currentImage, imagePosition.x, imagePosition.y, null);
				gDisplay.drawString(String.valueOf(d.getNumber()),
									imagePosition.x + currentImage.getHeight()/2, 
									imagePosition.y + currentImage.getWidth()/4);
				return true;				
			} else {
				return false;
>>>>>>> refs/remotes/origin/Dev
			}
			System.out.println("BBB");
			return false;
		}
	}
	
	
	/**
	 * @param currentImage
	 * @param imagePosition
	 * @return
	 */
	private boolean isInsideDisplay(BufferedImage currentImage, Point imagePosition){ //Rename? (*)
		//Checks if any of the points along the image bounds of image to be displayed is inside the bounds of the display,
		//with the vantage point representing the middle of the display.
		
		//((*)Might be an error depending on the placement of the imageBound)
		if (imagePosition.x < imageBound.width &&
			imagePosition.y < imageBound.height) {
			return true;
		}
		/*if (imageBound.contains(imagePosition.x - vantagePoint.x, 
				imagePosition.y - vantagePoint.y) ||
			imageBound.contains(imagePosition.x - vantagePoint.x,
					imagePosition.y - vantagePoint.y - currentImage.getHeight()) ||
			imageBound.contains(imagePosition.x - vantagePoint.x + currentImage.getWidth(), 
					imagePosition.y - vantagePoint.y) ||
			imageBound.contains(imagePosition.x - vantagePoint.x + currentImage.getWidth(),
					imagePosition.y - vantagePoint.y - currentImage.getHeight())) {
			return true;
		}*/
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

<<<<<<< HEAD
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
		
		//vantagePoint.translate(xOffset, yOffset);
	}
	
	/**
	 * @param xOffset
	 * @param yOffset
	 */
	public void addOffset(int xOffset, int yOffset) {
		this.xOffset += xOffset;
		this.yOffset += yOffset;
		
		//vantagePoint.translate(xOffset, yOffset);
	}
	


=======
	public void windowResized(Rectangle newSize, Graphics2D newGDisplay)
	{
		imageBound = newSize;
		gDisplay = newGDisplay;
	}
>>>>>>> refs/remotes/origin/Dev
}
