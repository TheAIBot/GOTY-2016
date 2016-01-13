package Game.Control.GameEngine;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.sun.security.auth.NTDomainPrincipal;

import Game.Control.Input.InputManager;
import Game.Control.Input.KeyPressListener;
import Game.Control.Input.SpecialKeys;
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
import Game.Model.Score.*;

public class GameEngine implements BoardChangedListener, KeyPressListener, GameStateChangedListener, ScoreChangedListener {
	private static final String SAVE_FILE_NAME = "game";
	private transient static final SaveFileManager<GameEngine> saver = new SaveFileManager<GameEngine>("saveFiles");
	private transient GraphicsManager graphics;
	private transient InputManager input = new InputManager();
	private final GameSettings settings;
	private transient AudioManager audio;
	private boolean isPaused = false;
	private transient ArrayList<GameEventsListener> gameEventsListeners = new ArrayList<GameEventsListener>();
	private GameBoardMode game;

	public GameEngine(GameSettings settings) {	
		this.settings = settings;
		this.audio = new AudioManager(settings.getSoundVolume());
		//initGame(settings);
		game = createGameType(settings);
		game.createGame();
		this.graphics = new GraphicsManager(this, game.getNumberOfPlayers());
		game.addBoardChangedListener(this);
		game.addGameStateChangedListener(this);
		game.addScoreChangedListener(this);
		graphics.repaint();
		game.pause();
		addKeyboardControls();
		game.unpause();
	}
	
	private GameBoardMode createGameType(GameSettings settings)
	{
		switch (settings.getGameMode()) {
		case SINGLE_PLAYER:
			return new MultiPlayerBoard(settings, 1);
		case MULTI_PLAYER:
			return new MultiPlayerBoard(settings, 2);
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
		input.AttachListenerToKey(graphics.getGraphicsPanel(), this, SpecialKeys.EXIT_GAME);
		input.AttachListenerToKey(graphics.getGraphicsPanel(), this, SpecialKeys.TOGGLE_PAUSE);
	}
	
	public RenderInfo getRenderInfo(int playerIndex)
	{
		return game.getRenderInfo(playerIndex);
	}
	
	@Override
	public void keyPressed(String keyPressed) {
		if (!SpecialKeys.isSpecialKey(keyPressed)) {
			game.keyPressed(keyPressed);
		} else {
			handleSpecialKeyPress(keyPressed);
		}
	}
		
	private void handleSpecialKeyPress(String key)
	{
		switch (key) {
		case SpecialKeys.EXIT_GAME:
			shutdown();
		case SpecialKeys.TOGGLE_PAUSE:
			togglePause();
		default:
			break;
		}
	}
	
	public Tile[] getTiles(int playerIndex) {
		return game.getTiles(playerIndex);
	}
	
	public int getBoardSize()
	{
		return game.getSize();
	}
	
	public GameState getGameState()
	{
		return game.getGameState(0);
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
	
	public void shutdown()
	{
		for (GameEventsListener gameEventsListener : gameEventsListeners) {
			gameEventsListener.closeGame();
		}
	}
	
	public void save()
	{
		game.pause();
		saver.save(SAVE_FILE_NAME, this);
		game.unpause();
	}
	
	public static GameEngine load()
	{
		GameEngine loadedGame = saver.load(SAVE_FILE_NAME);
		loadedGame.gameEventsListeners = new ArrayList<GameEventsListener>();
		loadedGame.graphics = new GraphicsManager(loadedGame, loadedGame.game.getNumberOfPlayers());
		loadedGame.input = new InputManager();
		loadedGame.addKeyboardControls();
		loadedGame.audio = new AudioManager(loadedGame.settings.getSoundVolume());
		return loadedGame;		
	}

	public void togglePause()
	{
		if (isPaused) {
			unpause();
		} else {
			pause();
		}
		isPaused = !isPaused;
	}

	public void pause(){
		game.pause();
	}
	
	public void unpause() {
		game.unpause();
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

	@Override
	public void scoreChanged(int score, int time, int screenIndex) {
		setScoreAndTime(score, time, screenIndex);
	}

	public void addGameEventListener(GameEventsListener listener)
	{
		gameEventsListeners.add(listener);
	}
}
