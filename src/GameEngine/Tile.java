package GameEngine;

import java.awt.Color;
import java.awt.Point;

public class Tile implements java.io.Serializable {
	public final int number;
	public final Color tileColor;
	public Point position;
	
	public Tile(int num, Color color, Point startPosition)
	{
		number = num;
		tileColor = color;
		position = startPosition;
	}
}
