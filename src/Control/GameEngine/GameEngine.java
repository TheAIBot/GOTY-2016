package Control.GameEngine;

import java.awt.Rectangle;

import Model.BoardChangedListener;
import Model.GameBoard;
import Model.GameBoardMode;
import Model.GameSettings;
import Model.GameState;
import Model.GraphicsPanel;
import Model.PlayerSettings;
import Model.Tile;
import View.Screen;
import Model.Directions;

public class GameEngine implements BoardChangedListener, KeyPressListener {
	private final SaveFileManager<GameBoardMode> saver = new SaveFileManager<GameBoardMode>("saveFiles");
	private final GraphicsManager graphics;
	private final InputManager input = new InputManager();
	private GameBoardMode game;
	private GameSettings settings;

	public GameEngine(GameSettings settings, Screen screen, GraphicsPanel panel) {	
		if (screen == null) {
			throw new NullPointerException("Screen provided is null");
		}//TODO add more null checks
		this.settings = settings;
		this.graphics = new GraphicsManager(screen, panel);		
		initGame(settings);
	}
	
	public GameEngine(GameSettings settings) {	
		
		graphics = null;
		initGame(settings);
	}
	
	private void initGame(GameSettings settings)
	{
		game = new GameBoard(settings);
		game.addBoardChangedListener(this);
		game.createGame();
		game.makeRandom();
		addKeyboardControls();
	}
	
	private void addKeyboardControls()
	{
		String[] subscribeKeys = game.getKeysToSubscribeTo();
		for (String subKey : subscribeKeys) {
			//System.out.println(subKey);
			input.AttachListenerToKey(graphics.getGraphicsPanel(), this, subKey);
		}
	}
	
	@Override
	public void keyPressed(String keyPressed) {
		game.keyPressed(keyPressed);
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
	public void boardChanged() {
		graphics.renderTiles(game.getTiles(), game.getRenderInfo());
	}

	public void render() {
		graphics.renderTiles(game.getTiles(), game.getRenderInfo());
	}
	
	public void save()
	{
		saver.save("game", game);
	}
	
	public void load()
	{
		game = saver.load("game");
	}

	public void pauseGame()
	{
		game.pause();
	}
	
	public void restartGame()
	{
		game.restart();
	}
	
	public void windowResized(Rectangle newSize)
	{
		graphics.windowResized(newSize, game.getTiles(), game.getRenderInfo());
	}
}
