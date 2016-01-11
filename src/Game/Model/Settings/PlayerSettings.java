package Game.Model.Settings;

import java.awt.event.KeyEvent;

public class PlayerSettings {
	private int upKey, downKey, leftKey, rightKey;
	private int toggleColorKey;
	private int cameraUpKey, cameraDownKey, cameraLeftKey, cameraRightKey;
	private String name;
	
	/**
	 * 
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 * @param playerName
	 */
	public PlayerSettings(int up, int down, int left, int right, int toggleColor, String playerName)
	{
		this.upKey = up;
		this.downKey = down;
		this.leftKey = left;
		this.rightKey = right;
		this.toggleColorKey = toggleColor;
		this.name = playerName;
	}
	
	public PlayerSettings(int up, int down, int left, int right, int toggleColor, int cup, int cdown, int cleft, int cright, String playerName) {
		this.upKey = up;
		this.downKey = down;
		this.leftKey = left;
		this.rightKey = right;
		this.toggleColorKey = toggleColor;
		cameraUpKey = cup;
		cameraDownKey = cdown;
		cameraLeftKey = cleft;
		cameraRightKey = cright;
		this.name = playerName;
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
		return (key == upKey || 
				key == downKey || 
				key == leftKey || 
				key == rightKey || 
				key == cameraUpKey ||
				key == cameraDownKey || 
				key == cameraLeftKey || 
				key == cameraRightKey ||
				key == toggleColorKey);
	}


	/**
	 * @param toggleColorKey the toggleColorKey to set
	 */
	public void setToggleColorKey(int toggleColorKey) {
		this.toggleColorKey = toggleColorKey;
	}
	
	/**
	 * @return the toggleColorKey
	 */
	public int getToggleColorKey() {
		return toggleColorKey;
	}

	/**
	 * 
	 * @return The name for the key used to toggle color render
	 */
	public String getToggleColorKeyName()
	{
		return KeyEvent.getKeyText(toggleColorKey);
	}

	public String getCameraUpKeyName() {
		return KeyEvent.getKeyText(cameraUpKey);
	}
	
	public String getCameraDownKeyName() {
		return KeyEvent.getKeyText(cameraDownKey);
	}
	
	public String getCameraLeftKeyName() {
		return KeyEvent.getKeyText(cameraLeftKey);
	}
	
	public String getCameraRightKeyName() {
		return KeyEvent.getKeyText(cameraRightKey);
	}
}
