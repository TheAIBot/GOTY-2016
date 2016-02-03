package Game.Model.AI;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import Game.Model.Board.Directions;
import Game.Model.Board.Tile;

public class AIBrain {
	private final Stack<Directions> moves = new Stack<Directions>();
	private final Tile[] tiles;
	private final int size;
	
	public AIBrain(Tile[] tiles, int size)
	{
		this.tiles = tiles;
		this.size = size;
	}
	
	public Directions getMove()
	{
		Tile tileToFix = null;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i].getNumber() - 1 != getNumberFromPosition(tiles[i].getPosition(), size)) {
				tileToFix = tiles[i];
			}
		}
		if (tileToFix.getNumber() % (size + 1) < size - 1) {
			Tile toFix = findTileFromNumber(tileToFix.getNumber(), tiles);
			Point2D.Double voidPos = getVoidTilePos(tiles, size);
			if (true) {
				
			}
		}
		return null;
	}
	
	private int getNumberFromPosition(Point2D.Double pos, int size) {
		return (int)(pos.y * size + pos.x);
	}
	
	private Tile findTileFromNumber(int number, Tile[] tiles)
	{
		for (Tile tile : tiles) {
			if (tile.getNumber() == number) {
				return tile;
			}
		}
		return null;
	}
	
	private Point2D.Double getVoidTilePos(Tile[] tiles, int size)
	{
		for (Tile tile : tiles) {
			if (tile != null) {
				return getPosition(tile.getNumber() - 1, size);
			}
		}
		return null;
	}
	
	private Point2D.Double getPosition(int number, int size) {
		int row = number / size;
		int col = number % size;
		return new Point2D.Double(col, row);
	}
}

