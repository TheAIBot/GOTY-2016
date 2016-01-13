package Game.Control.GameEngine;

import javax.swing.JPanel;

import com.sun.security.auth.NTDomainPrincipal;

import Game.Control.Input.InputManager;
import Game.Control.Input.KeyPressListener;
import Game.Model.Board.BoardChangedListener;
import Game.Model.Board.Directions;
import Game.Model.Board.SinglePlayerBoard;
import Game.Model.Board.GameBoardMode;
import Game.Model.Board.GameState;
import Game.Model.Board.GameStateChangedListener;
import Game.Model.Board.MultiPlayerBoard;
import Game.Model.Board.Tile;
import Game.Model.Settings.GameSettings;
import Game.View.GraphicsPanel;
import Game.View.RenderInfo;

public class GameEngine implements BoardChangedListener, KeyPressListener, GameStateChangedListener {
	private static final String SAVE_FILE_NAME = "game";
	private final SaveFileManager<GameBoardMode> saver = new SaveFileManager<GameBoardMode>("saveFiles");
	private final GraphicsManager graphics;
	private final InputManager input = new InputManager();
	private final GameSettings settings;
	private GameBoardMode game;
	private final AudioManager audio;

	public GameEngine(GameSettings settings) {	
		this.settings = settings;
		this.audio = new AudioManager(settings.getSoundVolume());
		//initGame(settings);
		game = createGameType(settings);
		game.createGame();
		this.graphics = new GraphicsManager(this, game.getNumberOfPlayers());
		game.addBoardChangedListener(this);
		game.addGameStateChangedListener(this);
		graphics.repaint();
		game.makeRandom();
		//new Thread(() -> {
		try {
			final int waitBeforeRandomize = 1000; // 1 sec
			Thread.sleep(waitBeforeRandomize);
		} catch (InterruptedException e) {
			Log.writeln("could not wait before randomizing");
		}
		addKeyboardControls();
		//});
	}
	
	private GameBoardMode createGameType(GameSettings settings)
	{
		switch (settings.getGameMode()) {
		case SINGLE_PLAYER:
			return new SinglePlayerBoard(settings, 0);
		case MULTI_PLAYER:
			return new MultiPlayerBoard(settings);
		default:
			throw new IllegalArgumentException();
		}
	}
	
	private void addKeyboardControls()
	{
		for (int playerIndex = 0; playerIndex < game.getNumberOfPlayers(); playerIndex++) {
			String[] subscribeKeys = game.getKeysToSubscribeTo(playerIndex);
			for (String subKey : subscribeKeys) {
				input.AttachListenerToKey(graphics.getGraphicsPanel(), this, subKey);
			}
		}
	}
	
	public RenderInfo getRenderInfo(int playerIndex)
	{
		return game.getRenderInfo(playerIndex);
	}
	
	@Override
	public void keyPressed(String keyPressed) {
		game.keyPressed(keyPressed);
	}
	
	public Tile[] getTiles(int playerIndex) {
		return game.getTiles(playerIndex);
	}
	
	public int getBoardSize()
	{
		return game.getSize();
	}
	
	public GameState getGameState(int playerIndex)
	{
		return game.getGameState(playerIndex);
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
	public void boardChanged(int playerIndex) {
		//audio.makeSwooshSound();
		render(playerIndex);		
	}

	public void render(int playerIndex) {
		graphics.renderTiles(game.getTiles(playerIndex), game.getRenderInfo(playerIndex), playerIndex);
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

	public JPanel getScreen()
	{
		return graphics.getGraphicsPanel();
	}
	
	public void setScoreAndTime(int score, int time, int screenIndex)
	{
		graphics.setScoreAndTime(score, time, screenIndex);
	}

	@Override
	public void gameStateChanged(GameState newGameState, int playerIndex) {
		graphics.setGameState(newGameState, playerIndex);
	}
}
