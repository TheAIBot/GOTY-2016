package Game.View;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Interface representing Displayables, which as of the current game version
 * only consists of tiles with images
 */
public interface Displayable {
	
	public BufferedImage getDisplayImage();
	
	public Point2D.Double getPosition();
	
	public Point2D.Double getDisplayPosition();
	
	public Point2D.Double[] getCorners();
	
	public int getNumber();
	
	
}
