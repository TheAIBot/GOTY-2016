package Game.Control.GameEngine;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.omg.PortableServer.ServantActivator;

import Game.Control.Input.ConsoleControl;
import Game.Control.Input.InputManager;
import Game.Control.Input.KeyPressListener;
import Game.Control.Input.SpecialKeys;
import Game.Control.Sound.PlaySoundListener;
import Game.Model.Board.BoardChangedListener;
import Game.Model.Board.GameBoardMode;
import Game.Model.Board.GameState;
import Game.Model.Board.GameStateChangedListener;
import Game.Model.Board.MultiPlayerBoard;
import Game.Model.Board.Tile;
import Game.Model.Resources.ResourceAudio;
import Game.Model.Score.ScoreChangedListener;
import Game.Model.Settings.GameSettings;
import Game.View.RenderInfo;

public class GameEngine implements BoardChangedListener, KeyPressListener, GameStateChangedListener, ScoreChangedListener, PlaySoundListener {
	private static final String SAVE_FILE_NAME = "game";
	private static final String SAVE_FILE_DIRECTORY = "savefiles";
	private transient static final SaveFileManager<GameEngine> saver = new SaveFileManager<GameEngine>(SAVE_FILE_DIRECTORY);
	private transient GraphicsManager graphics;
	private transient InputManager input = new InputManager();
	private final GameSettings settings;
	private ConsoleControl consoleControl;
	private transient AudioManager audio;
	private  transient ArrayList<GameEventsListener> gameEventsListeners = new ArrayList<GameEventsListener>();
	private GameBoardMode game;

	public GameEngine(GameSettings settings) {	
		this.settings = settings;		
		this.audio = new AudioManager(settings.getSoundVolume());
		this.consoleControl = new ConsoleControl(this, settings);
		//initGame(settings);
		game = createGameType(settings);
		game.createGame();
		this.graphics = new GraphicsManager(this, game.getNumberOfPlayers(),settings);
		game.addBoardChangedListener(this);
		game.addGameStateChangedListener(this);
		game.addScoreChangedListener(this);
		game.addPlaySoundListener(this);
		graphics.repaint();
		game.makeRandom();
		try {
			final int waitBeforeRandomize = 1000; // 1 sec
			Thread.sleep(waitBeforeRandomize);
		} catch (InterruptedException e) {
			Log.writeln("could not wait before randomizing");
		}
		
		if (true) {//
			consoleControl.startGameInConsole();
		} else {
			game.pause();
			addKeyboardControls();
			game.unpause();
		}
	}
	
	private GameBoardMode createGameType(GameSettings settings){
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
		if (!settings.isPaused()) {
			if (!SpecialKeys.isSpecialKey(keyPressed)) {
				game.keyPressed(keyPressed);
			} else {
				handleSpecialKeyPress(keyPressed);
			}
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
		render(playerIndex);		
	}

	public void render(int playerIndex) {
		graphics.renderTiles(game.getRenderInfo(playerIndex), playerIndex);
	}
	
	public void shutdown()
	{
		releaseAllResources();
		for (GameEventsListener gameEventsListener : gameEventsListeners) {
			gameEventsListener.closeGame();
		}
	}
	
	private void releaseAllResources()
	{
		audio.close();
		BufferedImage image = Tile.getTileImage();
		if (image != null) {

			image.flush();
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
		loadedGame.graphics = new GraphicsManager(loadedGame, load().game.getNumberOfPlayers(), loadedGame.settings);
		loadedGame.gameEventsListeners = new ArrayList<GameEventsListener>();
		loadedGame.input = new InputManager();
		loadedGame.audio = new AudioManager(loadedGame.settings.getSoundVolume());
		loadedGame.addKeyboardControls();
		return loadedGame;		
	}

	public void togglePause()
	{
		if (settings.isPaused()) {
			unpause();
		} else {
			pause();
		}
	}

	public void pause(){
		settings.setPaused(true);
		game.pause();
	}
	
	public void unpause() {
		settings.setPaused(true);
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

	
	@Override
	public void playSound(String name) {
		audio.playSound(name);		
	}
}
