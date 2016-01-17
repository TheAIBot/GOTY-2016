package Game.View;

import java.awt.geom.Point2D;


/**
 * Interface representing tile numbers
 */
public interface Numreable {	

	public Point2D.Double getNumberPosition();
	
	public int getNumber();
	
	public double getNumberDisplayScallingX();
	
	public double getNumberDisplayScallingY();
	
}
