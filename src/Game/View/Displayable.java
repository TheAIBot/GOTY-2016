package Game.View;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public interface Displayable {
	
	public BufferedImage getDisplayImage();
	
	public Point2D.Double getDisplayPosition();
	
	public Point2D.Double[] getCorners();
	
	public int getNumber();
	
	
}
