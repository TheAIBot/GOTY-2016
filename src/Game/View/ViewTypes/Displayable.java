package Game.View.ViewTypes;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Interface representing Displayables, which as of the current game version
 * only consists of tiles with images
 */
public interface Displayable {
	
	public BufferedImage getDisplayImage();
	
	public Point2D.Double getGoingTowrdsPosition();
	
	public Point2D.Double getCurrentPosition();
	
	public Point[] getCorners();
	
	public int getNumber();
	
	
}
