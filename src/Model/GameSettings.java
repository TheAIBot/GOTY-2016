package Model;
import java.awt.event.KeyEvent;

public class GameSettings {
	private float soundVolume;
	private int gameSize;
	private GameModes gameMode;
	private DifficultyLevel difficultyLevel;
	private boolean randomize;
	private PlayerSettings playerOne;
	private PlayerSettings playerTwo;
	
	public GameSettings(float vol, int size, GameModes mode, DifficultyLevel difficulty, PlayerSettings p_one, PlayerSettings p_two)
	{
		soundVolume = vol;
		gameSize = size;
		gameMode = mode;
		setDifficultyLevel(difficulty);
		playerOne = p_one;
		playerTwo = p_two;
	}
	public GameSettings()
	{
		this(0.5f,4,GameModes.NORMAL, DifficultyLevel.NORMAL,
				new PlayerSettings(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, "Player 1"),
				new PlayerSettings(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, "Player 2"));
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
}
