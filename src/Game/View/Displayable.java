package Game.View;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

public interface Displayable {
	
	public BufferedImage getDisplayImage();
	
	public Point getDisplayPosition();
	
	public Point[] getCorners();
	
	
}
