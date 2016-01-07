package Model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import Control.*;

public interface Displayable {
	
	public BufferedImage getDisplayImage();
	
	public Point getImagePosition();
	
}
