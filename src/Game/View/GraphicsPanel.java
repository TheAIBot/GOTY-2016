package Game.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import Game.Control.GameEngine.GraphicsManager;
import Game.Model.Board.Tile;
import Game.View.ViewTypes.Colorfull;
import Game.View.ViewTypes.Displayable;
import Game.View.ViewTypes.Numreable;

public class GraphicsPanel extends JPanel {
	private static final long serialVersionUID = 1L; //autogenerated
	final int MIN_FONT_SIZE = 4;
	private final RenderInfo renderInfo;
	private final GraphicsManager gManager;
	private final int screenIndex;
	private boolean firstPaint = true; //used when rendering for the first time

	public GraphicsPanel(GraphicsManager gManager, RenderInfo renderInfo, int screenIndex) {
		super();
		this.gManager = gManager;
		this.setBackground(Color.WHITE);
		this.screenIndex = screenIndex;
		this.renderInfo = renderInfo;
		setFont(new Font("Verdana", 0, 12));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (firstPaint) {
			setImageScale();
		}
		renderDisplayables(gManager.getDisplayablesToRender(screenIndex), g);
		renderColorfull(gManager.getColorfullsToRender(screenIndex), g);
	}
	
	private void setImageScale()
	{
		double widthAndHeight = renderInfo.imageScale * Tile.DEFAULT_TILE_SIZE * renderInfo.getSize();
		double scaler = widthAndHeight / getHeight();
		double newImageScale = renderInfo.imageScale / scaler;
		renderInfo.setImageScale(newImageScale);
		firstPaint = false;
	}
	
	/**
	 * Render the tile images
	 * @param displayables
	 * @param gDisplay
	 */
	private void renderDisplayables(Displayable[] displayables, Graphics gDisplay){
		if (displayables != null) {
			BufferedImage currentImage = Tile.getTileImage();
			for (Displayable d : displayables) {
				//Checks if the displayable has the neccesary information required for displaying it on the screen.
				if (d == null) {
					continue;
				}
				Point2D.Double imagePosition = d.getCurrentPosition();
				if (AnyCornerInsideDisplay(d.getCorners(), d.getCurrentPosition(), Tile.DEFAULT_TILE_SIZE, Tile.DEFAULT_TILE_SIZE)) {
					
					//Move and scale the displayable to the corresponding position on the screen
					int destStartX = (int)Math.ceil((imagePosition.x + renderInfo.xOffset) * Tile.DEFAULT_TILE_SIZE * renderInfo.imageScale);
					int destStartY = (int)Math.ceil((imagePosition.y + renderInfo.yOffset) * Tile.DEFAULT_TILE_SIZE * renderInfo.imageScale);
					int destEndX = destStartX + (int)Math.ceil(Tile.DEFAULT_TILE_SIZE * renderInfo.imageScale);
					int destEndY = destStartY + (int)Math.ceil(Tile.DEFAULT_TILE_SIZE * renderInfo.imageScale);
					
					int srcStartX = (int)Math.ceil((getPosition(d.getNumber() - 1, renderInfo.getSize()).x) * (currentImage.getWidth() / renderInfo.getSize()));
					int srcStartY = (int)Math.ceil((getPosition(d.getNumber() - 1, renderInfo.getSize()).y ) * (currentImage.getHeight() / renderInfo.getSize()));
					int srcEndX = srcStartX + (int)Math.ceil(currentImage.getWidth() / renderInfo.getSize());
					int srcEndY = srcStartY + (int) Math.ceil(currentImage.getHeight() / renderInfo.getSize());
					
					gDisplay.drawImage(currentImage, 
									   destStartX, 
									   destStartY,
									   destEndX, 
									   destEndY,
									   srcStartX, 
									   srcStartY,
									   srcEndX, 
									   srcEndY,
									   null);
								
				}
			}
		}
	}
	
	/**
	 * Renders the colored tiles
	 * @param colored
	 * @param gDisplay
	 */
	private void renderColorfull(Colorfull[] colored, Graphics gDisplay){
		if (colored != null) {
			for (Colorfull colorfull : colored) {
				if (colorfull != null && 
					AnyCornerInsideDisplay(colorfull.getCorners(),colorfull.getCurrentPosition(), Tile.DEFAULT_TILE_SIZE, Tile.DEFAULT_TILE_SIZE)) {	
					
					int x = (int)Math.ceil((colorfull.getCurrentPosition().x + renderInfo.xOffset) * Tile.DEFAULT_TILE_SIZE * renderInfo.imageScale);
					int y = (int)Math.ceil((colorfull.getCurrentPosition().y + renderInfo.yOffset) * Tile.DEFAULT_TILE_SIZE * renderInfo.imageScale);						
					gDisplay.drawImage(colorfull.getColorImage(), x, y, (int)Math.ceil(Tile.DEFAULT_TILE_SIZE * renderInfo.imageScale), (int)Math.ceil(Tile.DEFAULT_TILE_SIZE * renderInfo.imageScale), null);
				}
			}
		}		
	}
		
	public boolean AnyCornerInsideDisplay(Point[] corners, Point2D.Double startingPosition, double scallingX, double scallingY){
		if (corners != null) {
			for (Point corner : corners) { 
				//Check after scaling and offset positioning, if the final position is contained in the window.
				if (isInsideDisplay(corner.x, corner.y, startingPosition, scallingX, scallingY)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isInsideDisplay(double cornerX, double cornerY, Point2D.Double startingPosition, double scallingX, double scallingY){
		double x = (cornerX + startingPosition.x + renderInfo.xOffset) * scallingX * renderInfo.imageScale;
		double y = (cornerY + startingPosition.y + renderInfo.yOffset) * scallingY * renderInfo.imageScale;
		return (x < getWidth() &&
				y < getHeight() &&
				x >= 0 &&
				y >= 0);
	}
	
	public void render()
	{
		repaint();
	}
	
	/**
	 * 
	 * @param number
	 * @param size
	 * @return the corresponding position in the grid
	 */
	public Point getPosition(int number, int size) {
		int row = number / size;
		int col = number % size;
		return new Point(col, row);
	}
}
