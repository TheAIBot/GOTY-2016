package GameEngine;

public class GameSettings {
	private int soundVolume;
	private int gameSize;
	private GameModes gameMode;
	private PlayerSettings playerOne;
	private PlayerSettings playerTwo;
	
	/**
	 * @return the soundVolume
	 */
	public int getSoundVolume() {
		return soundVolume;
	}
	
	/**
	 * @param soundVolume the soundVolume to set
	 */
	public void setSoundVolume(int soundVolume) {
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
