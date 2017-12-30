package Game.View.ViewTypes;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Interface representing the colored tiles
 */
public interface Colorfull {
	
	public Point2D.Double getCurrentPosition();
	
	public Point[] getCorners();
	
	public BufferedImage getColorImage();
	
}
