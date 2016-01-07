package Control.GameEngine;

import Control.Directions;
import Model.BoardChangedListener;
import Model.GameBoard;
import Model.GameState;
import Model.GraphicsPanel;
import Model.SuperGameBoard;
import Model.Tile;
import View.Screen;

import Model.Directions;

public class GameEngine implements KeyPressListener, BoardChangedListener {
	private final SaveFileManager<GameBoardMode> saver = new SaveFileManager<GameBoardMode>("saveFiles");
	private final GraphicsManager graphics;
	private final InputManager input;
	private GameBoardMode game;

	public GameEngine(GameSettings settings, Screen screen, GraphicsPanel panel) {	
		if (screen == null) {
			throw new NullPointerException("Screen provided is null");
		}
		this.graphics = new GraphicsManager(screen, panel);		
		input = new InputManager();	
		game = new GameBoard(settings.getGameSize());
		game.addBoardChangedListener(this);
		game.createGame();
		game.makeRandom();
	}
	
	public GameEngine(int startSize) {	
		graphics = null;
		input = null;	
		game = new GameBoard(startSize);
		game.createGame();
		game.makeRandom();
	}
	
	private void addKeyboardBindingsToPlayerControl(PlayerSettings player)
	{
		input.AttachListenerToKey(graphics, listener, key);
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
		}
	}

	@Override
	public void boardChanged() {
		graphics.renderTiles(game.getTiles());
	}
	
	public void save()
	{
		saver.save("game", game);
	}
	
	public void load()
	{
		game = saver.load("game");
	}
}
