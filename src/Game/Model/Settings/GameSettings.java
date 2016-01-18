package Game.Model.Settings;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import Game.Control.GameEngine.SaveFileManager;
import Game.Model.Board.GameModes;
import Game.Model.Difficulty.DifficultyLevel;
import Game.Model.Resources.ResourceImages;

public class GameSettings implements Serializable{
	private static final long serialVersionUID = -7337035071280469025L;
	
	//Constants defining the minimal og maximal value for sound, difficulty and size of the board.
	public static final int SOUND_MAX = 100;
	public static final int SOUND_MIN = 0;
	public static final int DIFF_MIN = 0; // min difficulty level
	public static final int DIFF_MAX = 3; // max difficulty level
	public static final int SIZE_MIN = 3; // min size of the board
	public static final int SIZE_MAX = 100; // max size of the board
	
	private boolean isPaused;
	private boolean isConsoleMode;
	private float soundVolume;
	private int gameSize;
	private GameModes gameMode;
	private DifficultyLevel difficultyLevel;
	private PlayerSettings[] players;
	private transient BufferedImage tileImage;
	private boolean isRandomized;
	private boolean hasGUI;
	// used to save and load gamesettings
	private static transient SaveFileManager<GameSettings> gameSettingsSaver = new SaveFileManager<GameSettings>("Game_settings");
	
	/**
	 * @param vol sound volume
	 * @param size board size
	 * @param mode game mode
	 * @param difficulty board difficulty
	 * @param players an array of the different players settings
	 * @param isRandom make a random board or use the default one
	 * @param isConsoleMode show the game in the console or in a GUI
	 * @param hasGUI can the game also show  the game in a GUI
	 */
	public GameSettings(float vol, int size, GameModes mode, DifficultyLevel difficulty, PlayerSettings[] players, boolean isRandom, boolean isConsoleMode, boolean hasGUI)
	{
		this.soundVolume = vol;
		this.gameSize = size;
		this.gameMode = mode;
		this.difficultyLevel = difficulty;
		this.players = players;
		this.isRandomized = isRandom;
		this.isConsoleMode = isConsoleMode;
		this.hasGUI = hasGUI;
	}
	
	/**
	 * initializes GameSettings with default values
	 */
	public GameSettings()
	{
		this(0.5f,
				4,
				GameModes.SINGLE_PLAYER, 
				DifficultyLevel.NORMAL,
				new PlayerSettings[] {
					new PlayerSettings(KeyEvent.VK_W, 
									   KeyEvent.VK_S, 
									   KeyEvent.VK_A, 
									   KeyEvent.VK_D, 
									   KeyEvent.VK_G,
									   KeyEvent.VK_T,
									   KeyEvent.VK_F,
									   KeyEvent.VK_H,
									   KeyEvent.VK_Q,
									   KeyEvent.VK_E,
									   KeyEvent.VK_R,
									   "Player 1"),
					new PlayerSettings(KeyEvent.VK_UP, 
									   KeyEvent.VK_DOWN, 
									   KeyEvent.VK_LEFT, 
									   KeyEvent.VK_RIGHT, 
									   KeyEvent.VK_K,
									   KeyEvent.VK_I,
									   KeyEvent.VK_J,
									   KeyEvent.VK_L,
									   KeyEvent.VK_U,
									   KeyEvent.VK_O,
									   KeyEvent.VK_P,
									   "Player 2"),
		},
				false,
				false, //Assumes it doesn't start in console mode as default
				true);
	}
	
	/**
	 * @return the value of the volume
	 */
	public float getSoundVolume() {
		return soundVolume;
	}
	
	/**
	 * Set the value of the sound volume (from 0-100%)
	 * @param soundVolume
	 */
	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}

	/**
	 * Returns the size of the game board
	 * @return gameSize
	 */
	public int getGameSize() {
		return gameSize;
	}
	
	/**
	 * Return an array containing all the instanses of PlayerSettings.
	 * @return players
	 */
	public PlayerSettings[] getPlayers()
	{
		return players;
	}
	
	/**
	 * Set the size of the game board (Values from 3-100 is advised)
	 * @param gameSize
	 */
	public void setGameSize(int gameSize) {
		this.gameSize = gameSize;
	}
	
	/**
	 * Get this GameSettings objects instans of GameModes
	 * @return gameMode
	 */
	public GameModes getGameMode() {
		return gameMode;
	}
	
	/**
	 * Set the game mode of the game settings
	 * @param gameMode
	 */
	public void setGameMode(GameModes gameMode) {
		this.gameMode = gameMode;
	}
	
	/**
	 * Checks if the game settings
	 * @return isRandomized
	 */
	public boolean isRandomized()
	{
		return isRandomized;
	}
	
	/**
	 * Set wether or not the game should be randomized
	 * @param isRandomized
	 */
	public void setIsRandomize(boolean isRandomized)
	{
		this.isRandomized = isRandomized;
	}
	
	/**
	 * Returns the PlayerSettings for player 1
	 * @return players[0]
	 */
	public PlayerSettings getPlayerOne() {
		return players[0];
	}
	
	/**
	 * Set the PlayerSettings for player 1
	 * @param playerOne
	 */
	public void setPlayerOne(PlayerSettings playerOne) {
		this.players[0] = playerOne;
	}
	
	/**
	 * Returns the PlayerSettings for player 2
	 * @return players[1]
	 */
	public PlayerSettings getPlayerTwo() {
		return players[1];
	}
	
	/**
	 * Set the PlayerSettings for player 2
	 * @param playerTwo
	 */
	public void setPlayerTwo(PlayerSettings playerTwo) {
		this.players[1] = playerTwo;
	}
	/**
	 * Returns this GameSettings' current difficulty level
	 * @return difficultyLevel
	 */
	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}
	/**
	 * Sets this GameSettings' current difficulty level
	 * @param difficultyLevel
	 */
	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	/**
	 * Returns this GameSettings' tile image
	 * @return tileImage
	 */
	public BufferedImage getTileImage() {
		return tileImage;
	}
	/**
	 * @param tileImage the tileImage to set
	 */
	public void setTileImage(BufferedImage tileImage) {
		this.tileImage = tileImage;
	}
	
	/**
	 * Save this GameSettings to the hard drive.
	 */
	public void save(){
		gameSettingsSaver.save("game_settings", this);
	}
	
	/**
	 * Load the current saved GameSettings
	 */
	public static GameSettings load(){
		return gameSettingsSaver.load("game_settings");
	}
	
	/**
	 * Manual reading of the GameSettings' tileImage from the hard drive
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        tileImage = ImageIO.read(in);
    }
    
    /**
     * Manual writing of the GameSettings' tileImage from the hard drive
     * @param out
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(tileImage, ResourceImages.ACCEPTED_EXTENSION, out);
    }
    
    /**
     * Returns true if console mode is activated. Returns false otherwise.
     * @return isConsoleMode
     */
    public boolean isConsoleMode(){
    	return this.isConsoleMode;
    }
    
    /**
     * Sets activation of console mode
     * @param isConsoleMode
     */
    public void setConsoleMode(boolean isConsoleMode){
    	this.isConsoleMode = isConsoleMode;
    }
    
    /**
     * Returns true if the game is paused and false otherwise.
     * @return isPaused
     */
	public boolean isPaused() {
		return isPaused;
	}
	
	/**
	 * Set to true if the game should be paused and false otherwise.
	 * @param isPaused
	 */
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	/**
	 * @return the hasGUI
	 */
	public boolean hasGUI() {
		return hasGUI;
	}

	/**
	 * @param hasGUI the hasGUI to set
	 */
	public void setHasGUI(boolean hasGUI) {
		this.hasGUI = hasGUI;
	}
}
