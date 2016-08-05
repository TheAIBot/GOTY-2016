package Game.View;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;

/**
 * Interface representing the colored tiles
 */
public interface Colorfull {
	
	public Point2D.Double getColorPosition();
	
	public Polygon getColorPolygon();
	
	public Point[] getColorCorners();

	public Color getColor();
	
}
