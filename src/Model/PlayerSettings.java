package Model;

import java.awt.event.KeyEvent;

public class PlayerSettings {
	private int upKey;
	private int downKey;
	private int leftKey;
	private int rightKey;
	
	private String name;
	
	/**
	 * 
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 * @param playerName
	 */
	public PlayerSettings(int up, int down, int left, int right, String playerName)
	{
		upKey = up;
		downKey = down;
		leftKey = left;
		rightKey = right;
		name = playerName;
	}

	/**
	 * @return The code for the key used to go up
	 */
	public int getUpKeyCode() {
		return upKey;
	}
	
	/**
	 * 
	 * @return The name for the key used to go up
	 */
	public String getUpKeyName(){
		return KeyEvent.getKeyText(upKey);
	}

	/**
	 * @param Sets the key code corresponding to the key used to go up
	 */
	public void setUpKeyCode(int up) {
		upKey = up;
	}

	/**
	 * @return The code for the key used to go down
	 */
	public int getDownKeyCode() {
		return downKey;
	}
	
	/**
	 * 
	 * @return The name for the key used to go down
	 */
	public String getDownKeyName(){
		return KeyEvent.getKeyText(downKey);
	}

	/**
	 * @param Sets the key code corresponding to the key used to go down
	 */
	public void setDownKeyCode(int down) {
		downKey = down;
	}

	/**
	 * @return The code for the key used to go left
	 */
	public int getLeftKeyCode() {
		return leftKey;
	}
	
	/**
	 * 
	 * @return The name for the key used to go left
	 */
	public String getLeftKeyName(){
		return KeyEvent.getKeyText(leftKey);
	}

	/**
	 * @param Sets the key code corresponding to the key used to go left
	 */
	public void setLeftKeyCode(int left) {
		leftKey = left;
	}

	/**
	 * @return The code for the key used to go right
	 */
	public int getRightKeyCode() {
		return rightKey;
	}

	/**
	 * 
	 * @return The name for the key used to go right
	 */
	public String getRightKeyName() {
		return KeyEvent.getKeyText(rightKey);
	}
	
	/**
	 * @param Sets the key code corresponding to the key used to go right
	 */
	public void setRightKeyCode(int right) {
		rightKey = right;
	}

	
	/**
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * @param name of the player
	 */
	public void setName(String playerName) {
		name = playerName;
	}
	
	public boolean hasKeyCode(int key)
	{
		return (key == upKey || key == downKey || key == leftKey || key == rightKey);
	}
}
