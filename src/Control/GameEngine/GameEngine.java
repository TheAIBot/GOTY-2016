package Control.GameEngine;

import Model.BoardChangedListener;
import Model.GameBoard;
import Model.GameBoardMode;
import Model.GameSettings;
import Model.GameState;
import Model.Tile;
import View.GraphicsPanel;
import Model.Directions;

public class GameEngine implements BoardChangedListener, KeyPressListener {
	private static final String SAVE_FILE_NAME = "game";
	private final SaveFileManager<GameBoardMode> saver = new SaveFileManager<GameBoardMode>("saveFiles");
	private final GraphicsManager graphics;
	private final InputManager input = new InputManager();
	private final GameSettings settings;
	private final AudioManager audio;
	private GameBoardMode game;

	public GameEngine(GameSettings settings) {	
		//if (screen == null) {
			//throw new NullPointerException("Screen provided is null");
		//}//TODO add more null checks
		this.settings = settings;
		this.graphics = new GraphicsManager();	
		this.audio = new AudioManager();
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
		render();
		audio.makeSwooshSound();
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
