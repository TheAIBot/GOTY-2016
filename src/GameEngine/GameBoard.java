package GameEngine;

import graphics.Screen;

import java.awt.Color;
import java.awt.Point;

public class GameBoard extends SuperGameBoard {
	private Point voidTilePosition;
<<<<<<< HEAD
	
	public GameBoard(int startSize) {
		super(startSize);
=======
	public final int size;
	private Screen screen;
	
	public GameBoard(int startSize, Screen screen) {
		size = startSize;
		this.screen = screen;
>>>>>>> Emil
	}
	
	@Override
	public void createGame()
	{
		tilePlacements = new Tile[size * size];
		for (int i = 0; i < tilePlacements.length - 1; i++) {
			tilePlacements[i] = new Tile(i + 1, getPosition(i), Color.blue);
		}
		voidTilePosition = new Point(size - 1, size - 1);
		
		//Testing: generate some sample tiles
		/*Tile[] tiles = new Tile[9];
		
		tiles[0] = new Tile(1, new Point(0,0), Color.RED);
		tiles[1] = new Tile(2, new Point(1,0), Color.RED);
		tiles[2] = new Tile(3, new Point(2,0), Color.RED);
		
		tiles[3] = new Tile(1, new Point(0,1), Color.RED);
		tiles[4] = new Tile(2, new Point(1,1), Color.RED);
		tiles[5] = new Tile(3, new Point(2,1), Color.RED);
		
		tiles[6] = new Tile(1, new Point(0,2), Color.RED);
		tiles[7] = new Tile(2, new Point(1,2), Color.RED);
		tiles[8] = new Tile(3, new Point(2,2), Color.RED);
		
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].render(screen);
		}*/
		
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
<<<<<<< HEAD


=======
	
>>>>>>> Emil
}
