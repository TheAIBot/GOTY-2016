package GameEngine;

import java.awt.Color;
import java.awt.Point;

public class GameBoard extends SuperGameBoard {
	private Point voidTilePosition;
	
	public GameBoard(int startSize) {
		super(startSize);
	}
	
	@Override
	public void createGame()
	{
		tilePlacements = new Tile[size * size];
		for (int i = 0; i < tilePlacements.length - 1; i++) {
			tilePlacements[i] = new Tile(i + 1, Color.blue, getPosition(i));
		}
		voidTilePosition = new Point(size - 1, size - 1);
	}
	
	@Override
	public void makeRandom()
	{
		randomizeGame();
	}
	
	@Override
	public void resetGame()
	{
		createGame();
		randomizeGame();
	}
	
	@Override
	public Tile[] getTiles()
	{
		return tilePlacements;
	}
	
	@Override
	public boolean moveVoidTile(Directions direction) {
		if (isMoveAllowed(direction)) {
			moveTileVoid(direction);
			return true;
		}
		return false;
	}
	
	public Tile getTileAtPoisition(Point p) {
		return tilePlacements[getIndexFromPoint(p)];
	}

	@Override
	public int getSize() {
		return size;
	}
	
	private boolean isMoveAllowed(Directions direction) {
		switch (direction) {
		case RIGHT:
			return voidTilePosition.getX() < size - 1;
		case LEFT:
			return voidTilePosition.getX() > 0;
		case UP:
			return voidTilePosition.getY() > 0;
		case DOWN:
			return voidTilePosition.getY() < size - 1;
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
<<<<<<< HEAD
=======

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

	private int getIndexFromPoint(Point p)
	{
		// x + y * width (width is the size)
		return p.x + p.y * size;
	}

	private Point getPosition(int number) {
		int row = number / size;
		int col = number % size;

		return new Point(col, row);
	}
>>>>>>> dev
	
	private void randomizeGame() {
		final int RANDOM_MOVES = 1000000;
		for (int i = 0; i < RANDOM_MOVES; i++) {
			switch ((((int)(Math.random() * 10)) % 4)) {
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


}