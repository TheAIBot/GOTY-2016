package GameEngine;

import java.awt.Point;

public abstract class SuperGameBoard {
	protected Tile[] tilePlacements;
	protected final int size;
	
	public SuperGameBoard(int startSize)
	{
		size = startSize;
	}
	
	public abstract void createGame();
	
	public abstract void makeRandom();
	
	public abstract void resetGame();
	
	public abstract Tile[] getTiles();
	
	public abstract boolean moveVoidTile(Directions direction);
	
	public abstract int getSize();
	
	public abstract Tile getTileAtPoisition(Point p);
	
	
	
	protected Point moveWithDirection(Point toMove, Directions direction) {
		switch (direction) {
		case RIGHT:
			toMove.translate(1, 0);
			break;
		case LEFT:
			toMove.translate(-1, 0);
			break;
		case UP:
			toMove.translate(0, -1);
			break;
		case DOWN:
			toMove.translate(0, 1);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return toMove;
	}

	protected int getIndexFromPoint(Point p)
	{
		// x + y * width (width = size)
		return p.x + p.y * size;
	}

	protected Point getPosition(int number) {
		int row = number / size;
		int col = number % size;

		return new Point(col, row);
	}
}
