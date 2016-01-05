package GameEngine;

import graphics.Screen;

import java.awt.Point;

public class GameEngine implements java.io.Serializable {
	private final SuperGameBoard game;
	private Screen screen;

	public GameEngine(int startSize, Screen screen) {	
		this.screen = screen;
		game = new GameBoard(startSize, screen);
		game.createGame();
		game.makeRandom();
	}
	
	public GameEngine(int startSize) {	
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
	

	public void update() {
		
	}
	
	public void render() {
		screen.clear();
		for (int i = 0; i < getTiles().length; i++) {
			if (getTiles()[i] != null) getTiles()[i].render(screen);
		}
	}
	

}
