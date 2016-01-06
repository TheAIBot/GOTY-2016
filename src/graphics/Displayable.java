package graphics;

import java.awt.Point;
import java.awt.image.BufferedImage;

public interface Displayable {
	
	public BufferedImage getDisplayImage();
	
	public Point getImagePosition();
	
}
