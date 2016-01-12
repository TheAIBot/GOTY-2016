package Game.Control.GameEngine;

import Game.Control.Input.InputManager;
import Game.Control.Input.KeyPressListener;
import Game.Model.Board.BoardChangedListener;
import Game.Model.Board.Directions;
import Game.Model.Board.GameBoard;
import Game.Model.Board.GameBoardMode;
import Game.Model.Board.GameState;
import Game.Model.Board.Tile;
import Game.Model.Settings.GameSettings;
import Game.View.GraphicsPanel;

public class GameEngine implements BoardChangedListener, KeyPressListener {
	private static final String SAVE_FILE_NAME = "game";
	private final SaveFileManager<GameBoardMode> saver = new SaveFileManager<GameBoardMode>("saveFiles");
	private final GraphicsManager graphics;
	private final InputManager input = new InputManager();
	private final GameSettings settings;
	private GameBoardMode game;
	private final AudioManager audio;

	public GameEngine(GameSettings settings) {	
		//if (screen == null) {
			//throw new NullPointerException("Screen provided is null");
		//}//TODO add more null checks
		this.settings = settings;
		this.graphics = new GraphicsManager();
		audio = new AudioManager(settings.getSoundVolume());
		initGame(settings);
	}
	
	private void initGame(GameSettings settings)
	{
		game = new GameBoard(settings);
		game.addBoardChangedListener(this);
		game.createGame();
		boardChanged();
		new Thread(() -> {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.makeRandom();
		}).start();
		addKeyboardControls();
	}
	
	private void addKeyboardControls()
	{
		String[] subscribeKeys = game.getKeysToSubscribeTo(); //Violation of MVC (*)
		for (String subKey : subscribeKeys) {
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
		//audio.makeSwooshSound();
		render();		
	}

	public void render() {
		graphics.renderTiles(game.getTiles(), game.getRenderInfo());
	}
	
	public void save()
	{
		saver.save(SAVE_FILE_NAME, game);
	}
	
	public void load()
	{
		game = saver.load(SAVE_FILE_NAME);
	}

	public void pauseGame()
	{
		game.pause();
	}
	
	public void restartGame()
	{
		game.restart();
	}

	public GraphicsPanel getScreen()
	{
		return graphics.getGraphicsPanel();
	}
}
