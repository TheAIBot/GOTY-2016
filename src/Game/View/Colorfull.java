package Game.View;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Point2D;

public interface Colorfull {
	
	public Point2D.Double getColorPosition();
	
	public Polygon getColorPolygon();
	
	public Point2D.Double[] getColorCorners();

	public Color getColor();
	
	public double getColorPolygonScallingX();
	
	public double getColorPolygonScallingY();
	
}
