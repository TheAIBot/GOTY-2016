package Game.Model.Settings;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Game.Model.Board.GameModes;
import Game.Model.Difficulty.DifficultyLevel;

public class GameSettings {
	public static final int SOUND_MAX = 100;
	public static final int SOUND_MIN = 0;
	public static final int DIFF_MIN = 0;
	public static final int DIFF_MAX = 3;
	public static final int SIZE_MIN = 3;
	public static final int SIZE_MAX = 100;
	
	private float soundVolume;
	private int gameSize;
	private GameModes gameMode;
	private DifficultyLevel difficultyLevel;
	private boolean randomize;
	private PlayerSettings playerOne;
	private PlayerSettings playerTwo;
	private BufferedImage tileImage;
	
	public GameSettings(float vol, int size, GameModes mode, DifficultyLevel difficulty, boolean randomize, PlayerSettings p_one, PlayerSettings p_two)
	{
		this.soundVolume = vol;
		this.gameSize = size;
		this.gameMode = mode;
		this.difficultyLevel = difficulty;
		this.randomize = randomize;
		this.playerOne = p_one;
		this.playerTwo = p_two;
	}
	public GameSettings()
	{
		this(0.5f,
				4,
				GameModes.NORMAL, 
				DifficultyLevel.NORMAL, 
				true,
				new PlayerSettings(KeyEvent.VK_W, 
								   KeyEvent.VK_S, 
								   KeyEvent.VK_A, 
								   KeyEvent.VK_D, 
								   KeyEvent.VK_Q, 
								   "Player 1"),
				new PlayerSettings(KeyEvent.VK_UP, 
								   KeyEvent.VK_DOWN, 
								   KeyEvent.VK_LEFT, 
								   KeyEvent.VK_RIGHT, 
								   KeyEvent.VK_PERIOD, 
								   "Player 2"));
	}
	
	/**
	 * @return the soundVolume
	 */
	public float getSoundVolume() {
		return soundVolume;
	}
	
	/**
	 * @param soundVolume the soundVolume to set
	 */
	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}

	/**
	 * @return the gameSize
	 */
	public int getGameSize() {
		return gameSize;
	}
	
	/**
	 * @param gameSize the gameSize to set
	 */
	public void setGameSize(int gameSize) {
		this.gameSize = gameSize;
	}
	
	/**
	 * @return the gameMode
	 */
	public GameModes getGameMode() {
		return gameMode;
	}
	
	/**
	 * @param gameMode the gameMode to set
	 */
	public void setGameMode(GameModes gameMode) {
		this.gameMode = gameMode;
	}
	
	/**
	 * @return the playerOne
	 */
	public PlayerSettings getPlayerOne() {
		return playerOne;
	}
	
	/**
	 * @param playerOne the playerOne to set
	 */
	public void setPlayerOne(PlayerSettings playerOne) {
		this.playerOne = playerOne;
	}
	
	/**
	 * @return the playerTwo
	 */
	public PlayerSettings getPlayerTwo() {
		return playerTwo;
	}
	
	/**
	 * @param playerTwo the playerTwo to set
	 */
	public void setPlayerTwo(PlayerSettings playerTwo) {
		this.playerTwo = playerTwo;
	}
	/**
	 * @return the difficultyLevel
	 */
	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}
	/**
	 * @param difficultyLevel the difficultyLevel to set
	 */
	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	/**
	 * @return the tileImage
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
}
