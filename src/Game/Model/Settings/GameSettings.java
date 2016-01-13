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
	private PlayerSettings[] players;
	private transient BufferedImage tileImage;
	private boolean isRandomized;
	private static transient SaveFileManager<GameSettings> gameSettingsSaver = new SaveFileManager<GameSettings>("Game_settings");
	
	public GameSettings(float vol, int size, GameModes mode, DifficultyLevel difficulty, boolean randomize, PlayerSettings[] players, boolean isRandom)
	{
		this.soundVolume = vol;
		this.gameSize = size;
		this.gameMode = mode;
		this.difficultyLevel = difficulty;
		this.randomize = randomize;
		this.players = players;
		this.isRandomized = isRandom;
	}
	
	public GameSettings()
	{
		this(0.5f,
				4,
				GameModes.SINGLE_PLAYER, 
				DifficultyLevel.NORMAL, 
				true,
				new PlayerSettings[] {
					new PlayerSettings(KeyEvent.VK_W, 
									   KeyEvent.VK_S, 
									   KeyEvent.VK_A, 
									   KeyEvent.VK_D, 
									   KeyEvent.VK_T,
									   KeyEvent.VK_G,
									   KeyEvent.VK_H,
									   KeyEvent.VK_F,
									   KeyEvent.VK_Q,
									   KeyEvent.VK_E,
									   KeyEvent.VK_R,
									   "Player 1"),
					new PlayerSettings(KeyEvent.VK_UP, 
									   KeyEvent.VK_DOWN, 
									   KeyEvent.VK_LEFT, 
									   KeyEvent.VK_RIGHT, 
									   KeyEvent.VK_I,
									   KeyEvent.VK_K,
									   KeyEvent.VK_L,
									   KeyEvent.VK_J,
									   KeyEvent.VK_U,
									   KeyEvent.VK_O,
									   KeyEvent.VK_P,
									   "Player 2"),
		},
				false);
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
	
	public PlayerSettings[] getPlayers()
	{
		return players;
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
		return players[0];
	}
	
	/**
	 * @param playerOne the playerOne to set
	 */
	public void setPlayerOne(PlayerSettings playerOne) {
		this.players[0] = playerOne;
	}
	
	/**
	 * @return the playerTwo
	 */
	public PlayerSettings getPlayerTwo() {
		return players[1];
	}
	
	/**
	 * @param playerTwo the playerTwo to set
	 */
	public void setPlayerTwo(PlayerSettings playerTwo) {
		this.players[1] = playerTwo;
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
	
	
	
	public void save(){
		gameSettingsSaver.save("game_settings", this);
	}
	
	public static GameSettings load(){
		return gameSettingsSaver.load("game_settings");
	}
	
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        tileImage = ImageIO.read(in);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(tileImage, ResourceImages.ACCEPTED_EXTENSION, out);
    }
}
