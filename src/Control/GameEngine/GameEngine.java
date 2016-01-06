package Control.GameEngine;

import View.*;
import Model.*;
import java.awt.Point;
import Control.Directions;

public class GameEngine implements java.io.Serializable {
	private final InputManager input;
	private final Log log;
	private final SaveFileManager save;
	private final SuperGameBoard game;
	private Screen screen;

	public GameEngine(int startSize, Screen screen) {	
		if (screen == null) {
			throw new NullPointerException("Screen provided is null");
		}
		this.screen = screen;
		
		input = new InputManager();
		log = new Log();
		save = new SaveFileManager(""); //Vælg type (*)		
		game = new GameBoard(startSize);
		game.createGame();
		game.makeRandom();
	}
	
	/*
	public GameEngine(int startSize) {	
		game = new GameBoard(startSize, screen);
		game.createGame();
		game.makeRandom();
	}
	*/
	
	public Tile[] getTiles() {
		return game.getTiles();
	}
	
	public boolean moveVoidTile(Directions direction)
	{
		return game.moveVoidTile(direction);
	}
	
	public int getBoardSize()
	{
		return 0; //game.size skal returneres (*)
	}
	
	public GameState getGameState()
	{
		return game.getGameState();
	}

	public void createGame()
	{
		game.createGame();
	}
	
	public void makeRandom()
	{
		game.makeRandom();
	}
	
	public void resetGame()
	{
		game.resetGame();
	}
	
	
	
	public void update() {
		
	}
	
	public void render() {
		screen.clear();
		for (int i = 0; i < getTiles().length; i++) {
			if (getTiles()[i] != null) getTiles()[i].render();
		}
	}
	

}
