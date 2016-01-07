package Control.GameEngine;

import View.*;
import Model.*;

public class GameEngine implements java.io.Serializable, KeyPressListener, BoardChangedListener {
	
	private final GraphicsManager graphics;
	private final InputManager input;
	private final SuperGameBoard game;

	public GameEngine(int startSize, Screen screen, GraphicsPanel panel) {	
		if (screen == null) {
			throw new NullPointerException("Screen provided is null");
		}
		this.graphics = new GraphicsManager(screen, panel);		
		input = new InputManager();	
		game = new GameBoard(startSize);
		game.addBoardChangedListener(this);
		game.createGame();
		game.makeRandom();
	}
	
<<<<<<< HEAD
	/*public GameEngine(int startSize) {	
		game = new GameBoard(startSize, screen);
=======
	public GameEngine(int startSize) {	
		graphics = null;
		input = null;	
		game = new GameBoard(startSize);
>>>>>>> refs/remotes/origin/Dev
		game.createGame();
		game.makeRandom();
	}*/
	
	public Tile[] getTiles() {
		return game.getTiles();
	}
	
	public boolean moveVoidTile(Directions direction)
	{
		return game.moveVoidTile(direction);
	}
	
	public int getBoardSize()
	{
		return game.getSize();
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
<<<<<<< HEAD
	
	
	
	public void update() {
		
	}
	
	public void render() {
		screen.clear();
		for (int i = 0; i < getTiles().length; i++) {
			if (getTiles()[i] != null) getTiles()[i].render(screen);
=======

	@Override
	public void KeyPressed(String keyPressed) {
		switch (keyPressed) {
		case "DOWN":
			moveVoidTile(Directions.DOWN);
			break;
		case "UP":
			moveVoidTile(Directions.UP);
			break;
		case "LEFT":
			moveVoidTile(Directions.LEFT);
			break;
		case "RIGHT":
			moveVoidTile(Directions.RIGHT);
			break;
>>>>>>> refs/remotes/origin/Dev
		}
	}

	@Override
	public void boardChanged() {
		graphics.renderTiles(game.getTiles());
	}
	

}
