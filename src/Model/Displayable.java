package Model;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

public interface Displayable {
	
	public BufferedImage getDisplayImage();
	
	public Point getImagePosition();
	
	public Point[] getCorners();
	
	public int getNumber();
	
	public Color getColor();
	
}
