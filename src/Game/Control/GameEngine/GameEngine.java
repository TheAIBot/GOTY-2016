package Game.Control.GameEngine;

import Game.Control.Input.InputManager;
import Game.Control.Input.KeyPressListener;
import Game.Model.Board.BoardChangedListener;
import Game.Model.Board.Directions;
import Game.Model.Board.SinglePlayerBoard;
import Game.Model.Board.GameBoardMode;
import Game.Model.Board.GameState;
import Game.Model.Board.MultiPlayerBoard;
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
		this.graphics = new GraphicsManager(this);
		initGame(settings);
	}
	
	private void initGame(GameSettings settings)
	{
		switch (settings.getGameMode()) {
		case SINGLE_PLAYER:
			
			break;

		default:
			break;
		}
		game = new SinglePlayerBoard(settings);
		game.addBoardChangedListener(this);
		game.createGame();
		boardChanged();
<<<<<<< HEAD
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
=======
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					game.makeRandom();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
>>>>>>> refs/remotes/origin/Dev
		}).start();
	}
	
	private GameBoardMode createGameType(GameSettings settings)
	{
		switch (settings.getGameMode()) {
		case SINGLE_PLAYER:
			return new SinglePlayerBoard(settings, 0);
		case MULTI_PLAYER:
			return new MultiPlayerBoard();
		default:
			throw new IllegalArgumentException();
		}
	}
	
	private void addKeyboardControls()
	{
		for (int playerIndex = 0; playerIndex < game.getNumberOfPlayers(); playerIndex++) {
			String[] subscribeKeys = game.getKeysToSubscribeTo(playerIndex);
			for (String subKey : subscribeKeys) {
				input.AttachListenerToKey(graphics.getGraphicsPanel(), this, subKey, playerIndex);
			}
		}
	}
	
	@Override
	public void keyPressed(String keyPressed, int playerIndex) {
		game.keyPressed(keyPressed, playerIndex);
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
	public void boardChanged(int playerIndex) {
		//audio.makeSwooshSound();
		render(playerIndex);		
	}

	public void render(int playerIndex) {
		graphics.renderTiles(game.getTiles(playerIndex), game.getRenderInfo(playerIndex));
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
