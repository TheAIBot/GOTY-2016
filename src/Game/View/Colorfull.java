package Game.View;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public interface Colorfull {
	
	public Point getColorPosition();
	
	public Polygon getColorPolygon();
	
	public Point[] getColorCorners();

	public Color getColor();
	
	public int getColorPolygonScallingX();
	
	public int getColorPolygonScallingY();
	
}
