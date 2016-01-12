package Game.View;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public interface Displayable {
	
	public BufferedImage getDisplayImage();
	
	public Point2D.Double getPosition();
	
	public Point2D.Double getDisplayPosition();
	
	public Point[] getCorners();
	
	public int getNumber();
	
	public Color getColor();
	
}
