package Game.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Game.Control.GameEngine.GraphicsManager;

public class GraphicsPanel extends JPanel {
	private static final long serialVersionUID = 1L; //autogenerated
	private Displayable[] tiles;
	private RenderInfo renderInfo;
	private double imageScaling = 1;
	private final int tileSize = 100;

	public GraphicsPanel() {
		super();
		this.setBackground(Color.WHITE);
	}

	public void setRenderInfo(Displayable[] tiles, RenderInfo renderInfo) {
		this.tiles = tiles;
		this.renderInfo = renderInfo;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (tiles != null && renderInfo != null) {
			int painted = 0;
			for (Displayable tile : tiles) {
				if (tile != null) {
					if (render((Graphics2D) g, tile, renderInfo)) {
						painted++;
					}
				}
			}
			System.out.println(painted);
		}
	}

	public boolean render(Graphics2D gDisplay, Displayable d, RenderInfo renderInfo) {
		//Checks if the displayable has the neccesary information required for displaying it on the screen.
		if (d == null) {
			throw new NullPointerException();
		}
		BufferedImage currentImage = d.getDisplayImage();
		Point2D.Double imagePosition = d.getDisplayPosition();
		if (currentImage == null || imagePosition == null) {
			throw new NullPointerException();
		} else {
			//Checks if the displayable is in a position, so that the image can be displayed on the screen. 
			//If not, it dosen't render it (to increase performance), else it does.
			if (isInsideDisplay(d)) {
				if (!renderInfo.renderColor) {
					gDisplay.drawImage(currentImage, 
									  (int) (((imagePosition.x + renderInfo.xOffset) + renderInfo.xOffset) * tileSize * imageScaling), 
									  (int) (((imagePosition.y + renderInfo.yOffset) + renderInfo.yOffset) * tileSize * imageScaling), 
									  (int) (((imagePosition.x + renderInfo.xOffset) + renderInfo.xOffset) * tileSize * imageScaling) + (int) (tileSize * imageScaling), 
									  (int) (((imagePosition.y + renderInfo.yOffset) + renderInfo.yOffset) * tileSize * imageScaling) + (int) (tileSize * imageScaling), 
									  
									  (int) ((getPosition(d.getNumber() - 1, renderInfo.getSize()).x + renderInfo.xOffset) * (currentImage.getWidth() / renderInfo.getSize())), 
									  (int) ((getPosition(d.getNumber() - 1, renderInfo.getSize()).y + renderInfo.yOffset) * (currentImage.getHeight() / renderInfo.getSize())), 
									  (int) ((getPosition(d.getNumber() - 1, renderInfo.getSize()).x + renderInfo.xOffset) * (currentImage.getWidth() / renderInfo.getSize())) + (int) (currentImage.getWidth() / renderInfo.getSize()), 
									  (int) ((getPosition(d.getNumber() - 1, renderInfo.getSize()).y + renderInfo.yOffset) * (currentImage.getWidth() / renderInfo.getSize())) + (int) (currentImage.getHeight() / renderInfo.getSize()),
									  null);
					/*gDisplay.drawImage(currentImage, 
									   (int) ((imagePosition.x + renderInfo.xOffset) * currentImage.getWidth() * imageScaling), 
									   (int) ((imagePosition.y + renderInfo.yOffset) * currentImage.getHeight() * imageScaling), 
									   (int) (currentImage.getWidth() * imageScaling), 
									   (int) (currentImage.getHeight() * imageScaling), 
									   null);
									   */
					} else {
					gDisplay.setColor(d.getColor());
					gDisplay.fillRect((int) (imagePosition.x * imageScaling * currentImage.getWidth()), 
									  (int) (imagePosition.y * imageScaling * currentImage.getHeight()), 
									  currentImage.getWidth(), 
									  currentImage.getHeight());
				}
				gDisplay.setColor(Color.WHITE);
				gDisplay.drawString(String.valueOf(d.getNumber()), 
									(int) (((imagePosition.x + renderInfo.xOffset) * currentImage.getWidth() + currentImage.getWidth() / 2) * imageScaling), 
									(int) (((imagePosition.y + renderInfo.yOffset) * currentImage.getHeight() + currentImage.getHeight() / 2) * imageScaling));
				return true;
			} else {
				return false;
			}
		}
	}
	
	public Point2D.Double getPosition(int number, int size) {
		int row = number / size;
		int col = number % size;

		return new Point2D.Double(col, row);
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
		Point2D.Double imagePosition = d.getDisplayPosition();
		Point[] corners = d.getCorners();
		for (Point corner : corners) {
			if ((corner.x + imagePosition.x + renderInfo.xOffset) * dImage.getWidth() < getWidth() &&
				(corner.y + imagePosition.y + renderInfo.yOffset) * dImage.getHeight() < getHeight() && 
				(corner.x + imagePosition.x + renderInfo.xOffset) * dImage.getWidth() > 0 &&
				(corner.y + imagePosition.y + renderInfo.yOffset) * dImage.getHeight() > 0) {
				return true;
			}
			
			/*if ((corner.x + imagePosition.x + renderInfo.xOffset) * dImage.getWidth() * imageScaling <= this.getWidth()
			&& (corner.y + imagePosition.y) * dImage.getHeight() * imageScaling <= this.getHeight()) {
				return true;
			}*/
			/*if ((corner.x + imagePosition.x + renderInfo.xOffset) * dImage.getWidth() * imageScaling <= this.getWidth() && 
			(corner.y + imagePosition.y + renderInfo.yOffset) * dImage.getHeight() * imageScaling <= this.getHeight() &&
			(corner.x + imagePosition.x + renderInfo.xOffset) * dImage.getWidth() * imageScaling  >= renderInfo.xOffset * dImage.getWidth() * imageScaling &&
			(corner.y + imagePosition.y + renderInfo.yOffset) * dImage.getHeight() * imageScaling >= renderInfo.yOffset * dImage.getHeight() * imageScaling) {
			return true;
			}*/
		}
		return false;
	}
}
