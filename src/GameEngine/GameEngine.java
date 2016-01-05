package GameEngine;

import java.awt.Color;
import java.awt.Point;

public class GameEngine implements java.io.Serializable {

	private final Tile[] tilePlacements;
	private Point voidTilePosition;
	public final int size;

	public GameEngine(int startSize) {
		size = startSize;
		tilePlacements = new Tile[size * size];
		for (int i = 0; i < tilePlacements.length; i++) {
			tilePlacements[i] = new Tile(i + 1, Color.blue, getPosition(i));
		}
		voidTilePosition = new Point(size - 1, size - 1);
		randomizeGame();
	}

	public Tile[] getTiles() {
		return tilePlacements;
	}

	public boolean moveVoidTile(Directions direction) {
		if (isMoveAllowed(direction)) {
			moveTileVoid(direction);
			return true;
		}
		return false;
	}

	private boolean isMoveAllowed(Directions direction) {
		switch (direction) {
		case RIGHT:
			return voidTilePosition.getX() != size - 1;
		case LEFT:
			return voidTilePosition.getX() != 0;
		case UP:
			return voidTilePosition.getY() != 0;
		case DOWN:
			return voidTilePosition.getY() != size - 1;
		default:
			throw new IllegalArgumentException();
		}
	}

	private void moveTileVoid(Directions direction) {
		moveWithDirection(voidTilePosition, direction);
		Tile tileToMove = getTileAtPoisition(voidTilePosition);
		moveWithDirection(tileToMove.position, direction.getOppositeDirection());
		moveTileIndexes(getIndexFromPoint(tileToMove.position), getIndexFromPoint(voidTilePosition));
	}
	
	private void moveTileIndexes(int tileAIndex, int tileBIndex)
	{
		Tile tileA = tilePlacements[tileAIndex];
		tilePlacements[tileAIndex] = tilePlacements[tileBIndex];
		tilePlacements[tileBIndex] = tileA;
	}

	private Point moveWithDirection(Point toMove, Directions direction) {
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

	public Tile getTileAtPoisition(Point p) {
		return tilePlacements[getIndexFromPoint(p)];
	}
	
	private int getIndexFromPoint(Point p)
	{
		// x + y * width (width = size)
		return p.x + p.y * size;
	}

	private Point getPosition(int number) {
		int row = number / size;
		int col = number % size;

		return new Point(row, col);
	}

	private void randomizeGame() {
		final int RANDOM_MOVES = 1000000;
		for (int i = 0; i < RANDOM_MOVES; i++) {

			switch ((int) (Math.random() * 10 % 4)) {
			case 0:
				moveVoidTile(Directions.LEFT);
				break;
			case 1:
				moveVoidTile(Directions.RIGHT);
				break;
			case 2:
				moveVoidTile(Directions.UP);
				break;
			case 3:
				moveVoidTile(Directions.DOWN);
				break;
			}
		}
	}

	public enum Directions {
		LEFT
		{
			@Override
			public Directions getOppositeDirection()
			{
				return Directions.RIGHT;
			}
		}, 
		RIGHT
		{
			@Override
			public Directions getOppositeDirection()
			{
				return Directions.LEFT;
			}
		}, 
		UP
		{
			@Override
			public Directions getOppositeDirection()
			{
				return Directions.DOWN;
			}
		}, 
		DOWN
		{
			@Override
			public Directions getOppositeDirection()
			{
				return Directions.UP;
			}
		};
		
		public abstract Directions getOppositeDirection();
	}
}
