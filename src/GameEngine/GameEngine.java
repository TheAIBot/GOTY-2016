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
	
	public Tile[] getTiles()
	{
		return tilePlacements;
	}

	public boolean moveVoidTile(Directions direction) {
		if (direction == Directions.DOWN) {
			if (voidTilePosition.getY() != size - 1) {
				moveWithDirection(voidTilePosition, direction);
				moveWithDirection(getTileAtPoisition(voidTilePosition).position, Directions.UP);
				return true;
			} else {
				return false;
			}
		} else if (direction == Directions.UP) {
			if (voidTilePosition.getY() != 0) {
				moveWithDirection(voidTilePosition, direction);
				moveWithDirection(getTileAtPoisition(voidTilePosition).position, Directions.DOWN);
				return true;
			} else {
				return false;
			}
		} else if (direction == Directions.LEFT) {
			if (voidTilePosition.getX() != 0) {
				moveWithDirection(voidTilePosition, direction);
				moveWithDirection(getTileAtPoisition(voidTilePosition).position, Directions.RIGHT);
				return true;
			} else {
				return false;
			}
		} else {
			if (voidTilePosition.getY() != size - 1) {
				moveWithDirection(voidTilePosition, direction);
				moveWithDirection(getTileAtPoisition(voidTilePosition).position, Directions.LEFT);
				return true;
			} else {
				return false;
			}
		}
	}
	
	private Point moveWithDirection(Point toMove, Directions direction)
	{
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
		}
		return toMove;
	}
	
	public Tile getTileAtPoisition(Point toFind)
	{
		for (Tile tile : tilePlacements) {
			if (tile.position.getX() == toFind.getX() &&
				tile.position.getY() == toFind.getY()) {
				return tile;
			}
		}
		//TODO if this eturn is ever hit then thee is an error in the game
		//don't want ot fix now
		return null;
	}

	private Point getPosition(int number) {
		int row = number % size;
		int col = number / size;
		return new Point(row, col);
	}

	public void randomizeGame() {
		final int RANDOM_MOVES = 1000;
		for (int i = 0; i < RANDOM_MOVES; i++) {

			switch ((int)(Math.random() * 10 % 4)) {
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
		LEFT, RIGHT, UP, DOWN
	}
}
