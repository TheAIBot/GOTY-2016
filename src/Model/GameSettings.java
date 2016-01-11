package Model;
import java.awt.event.KeyEvent;

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
	private PlayerSettings playerOne;
	private PlayerSettings playerTwo;
	private boolean isMultiplayer;
	
	public GameSettings(float vol, int size, GameModes mode, PlayerSettings p_one, PlayerSettings p_two, boolean mp)
	{
		soundVolume = vol;
		gameSize = size;
		gameMode = mode;
		playerOne = p_one;
		playerTwo = p_two;
		isMultiplayer = mp;
	}
	public GameSettings()
	{
		this(0.5f,4,GameModes.NORMAL,
				new PlayerSettings(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, "Player 1"),
				new PlayerSettings(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, "Player 2"),false);
	}
	
	/**
	 * 
	 * @return true: if multiplayer; false: if no multiplyer
	 */
	public boolean isMultiplyer()
	{
		return isMultiplayer;
	}
	
	
	/**
	 * Sets if the game is multiplyer
	 * @param mp
	 */
	public void setMultiplayer(boolean mp)
	{
		isMultiplayer = mp;
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
}
