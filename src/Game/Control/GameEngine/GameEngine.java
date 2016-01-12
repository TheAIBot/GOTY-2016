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
import Game.Model.Board.TwoPlayerBoard;
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
		this.audio = new AudioManager(settings.getSoundVolume());
		initGame(settings);
		this.graphics = new GraphicsManager(this, game.getNumberOfPlayers());
	}
	
	private void initGame(GameSettings settings)
	{
		game = createGameType(settings);
		game.createGame();
		game.addBoardChangedListener(this);
		new Thread(() -> 
		{
			try {
				final int waitBeforeRandomize = 1000; // 1 sec
				Thread.sleep(waitBeforeRandomize);
			} catch (InterruptedException e) {
				Log.writeln("could not wait before randomizing");
			}
			game.makeRandom();
			addKeyboardControls();
		}).start();
	}
	
	private GameBoardMode createGameType(GameSettings settings)
	{
		switch (settings.getGameMode()) {
		case SINGLE_PLAYER:
			return new SinglePlayerBoard(settings, 0);
		case MULTI_PLAYER:
			return new TwoPlayerBoard(settings);
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
}
