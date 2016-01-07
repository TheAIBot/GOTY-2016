package Model;


import java.awt.Color;
import java.awt.Point;

import View.Screen;
import Control.*;


public class GameBoard extends SuperGameBoard {
	private Point voidTilePosition;
	private Screen screen;
	
	public GameBoard(int startSize) {
		super(startSize);
		currentGameState = GameState.NOT_DECIDED_YET;
	}
	
	public GameBoard(int startSize, Screen screen) {
		super(startSize);
		this.screen = screen;
	}
	
	@Override
	public void createGame()
	{
		tilePlacements = new Tile[size * size];
		for (int i = 0; i < tilePlacements.length - 1; i++) {
			tilePlacements[i] = new Tile(i + 1, getPosition(i), Color.blue);
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
			swapVoidTile(direction);
			boardChanged();
			return true;
		}
		return false;
	}
	
	public boolean moveVoidTileNoUpdate(Directions direction) {
		if (isMoveAllowed(direction)) {
			swapVoidTile(direction);
			return true;
		}
		return false;
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

	private void swapVoidTile(Directions direction) {
		moveWithDirection(voidTilePosition, direction);
		Tile tileToMove = tilePlacements[getIndexFromPoint(voidTilePosition)];
		moveWithDirection(tileToMove.position, direction.getOppositeDirection());
		moveTileIndexes(getIndexFromPoint(tileToMove.position), getIndexFromPoint(voidTilePosition));
	}
	
	private void moveTileIndexes(int tileAIndex, int tileBIndex)
	{
		Tile tileA = tilePlacements[tileAIndex];
		tilePlacements[tileAIndex] = tilePlacements[tileBIndex];
		tilePlacements[tileBIndex] = tileA;
	}
	
	private void randomizeGame() {
		final int RANDOM_MOVES = 10000;
		for (int i = 0; i < RANDOM_MOVES; i++) {
			switch ((((int)(Math.random() * 10)) % 4)) {
			case 0:
				moveVoidTileNoUpdate(Directions.LEFT);
				break;
			case 1:
				moveVoidTileNoUpdate(Directions.RIGHT);
				break;
			case 2:
				moveVoidTileNoUpdate(Directions.UP);
				break;
			case 3:
				moveVoidTileNoUpdate(Directions.DOWN);
				break;
			}
		}
		boardChanged();
	}
	
}
