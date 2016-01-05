package GameEngine;

import graphics.Screen;

import java.awt.Point;

public class GameEngine implements java.io.Serializable {

	private final GameBoard game;

	public GameEngine(int startSize, Screen screen) {		
		game = new GameBoard(startSize, screen);
		game.createGame();
		game.makeRandom();
	}

	public Tile[] getTiles() {
		return game.getTiles();
	}
	
	public boolean moveVoidTile(Directions direction)
	{
		return game.moveVoidTile(direction);
	}
	
	public int getBoardSize()
	{
		return game.size;
	}

	public Tile getTileAtPoisition(Point p) {
		return game.getTileAtPoisition(p);
	}	
}
