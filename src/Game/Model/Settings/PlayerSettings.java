package Game.Model.Settings;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class PlayerSettings implements Serializable {
	private int upKey;
	private int downKey;
	private int leftKey;
	private int rightKey;
	private int upViewKey;
	private int downViewKey;
	private int leftViewKey;
	private int rightViewKey;
	private int toggleColorKey;
	private int zoomInKey;
	private int zoomOutKey;
	private String name;
	
	/**
	 * 
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 * @param playerName
	 */
	public PlayerSettings(int up, int down, int left, int right, int viewUp, int viewDown, int viewLeft, int viewRight, int toggleColor, int zoomIn, int zoomOut, String playerName)
	{
		upKey = up;
		downKey = down;
		leftKey = left;
		rightKey = right;
		upViewKey = viewUp;
		downViewKey = viewDown;
		leftViewKey = viewLeft;
		rightViewKey = viewRight;
		toggleColorKey = toggleColor;
		zoomInKey = zoomIn;
		zoomOutKey = zoomOut;
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
	 * @param Sets the key code corresponding to the key used to go right
	 */
	public void setRightKeyCode(int right) {
		rightKey = right;
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
	 * Sets the key code corresponding to the key used to view up
	 */
	public void setUpViewKeyCode(int up) {
		upViewKey = up;
	}
	
	/**
	 * @return The code for the key used to view up
	 */
	public int getUpViewKeyCode() {
		return upViewKey;
	}

	/**
	 * 
	 * @return The name for the key used to view up
	 */
	public String getUpViewKeyName() {
		return KeyEvent.getKeyText(upViewKey);
	}
	
	/**
	 * Sets the key code corresponding to the key used to view down
	 */
	public void setDownViewKeyCode(int down) {
		downViewKey = down;
	}
	
	/**
	 * @return The code for the key used to view down
	 */
	public int getDownViewKeyCode() {
		return downViewKey;
	}

	/**
	 * 
	 * @return The name for the key used to view down
	 */
	public String getDownViewKeyName() {
		return KeyEvent.getKeyText(downViewKey);
	}
	
	/**
	 * Sets the key code corresponding to the key used to view left
	 */
	public void setLeftViewKeyCode(int left) {
		leftViewKey = left;
	}
	
	/**
	 * @return The code for the key used to view left
	 */
	public int getLeftViewKeyCode() {
		return leftViewKey;
	}

	/**
	 * 
	 * @return The name for the key used to view left
	 */
	public String getLeftViewKeyName() {
		return KeyEvent.getKeyText(leftViewKey);
	}
	
	/**
	 * Sets the key code corresponding to the key used to view right
	 */
	public void setRightViewKeyCode(int right) {
		rightViewKey = right;
	}
	
	/**
	 * @return The code for the key used to view right
	 */
	public int getRightViewKeyCode() {
		return rightViewKey;
	}
	
	/**
	 * 
	 * @return The name for the key used to view right
	 */
	public String getRightViewKeyName() {
		return KeyEvent.getKeyText(rightViewKey);
	}

	/**
	 * 
	 * @return The name for the key used to zoom in
	 */
	public String getZoomInKeyName() {
		return KeyEvent.getKeyText(zoomInKey);
	}
	
	/**
	 * Sets the key code corresponding to the key used to zoom in
	 */
	public void setZoomInKeyCode(int zoomIn) {
		zoomInKey = zoomIn;
	}
	
	/**
	 * @return The code for the key used to zoom in
	 */
	public int getZoomInKeyCode() {
		return zoomInKey;
	}
	
	/**
	 * 
	 * @return The name for the key used to zoom out
	 */
	public String getZoomOutKeyName() {
		return KeyEvent.getKeyText(zoomOutKey);
	}
	
	/**
	 * Sets the key code corresponding to the key used to zoom out
	 */
	public void setZoomOutKeyCode(int zoomOut) {
		zoomOutKey = zoomOut;
	}
	
	/**
	 * @return The code for the key used to zoom out
	 */
	public int getZoomOutKeyCode() {
		return zoomOutKey;
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
				key == upViewKey ||
				key == downViewKey ||
				key == leftViewKey ||
				key == rightViewKey ||
				key == zoomInKey ||
				key == zoomOutKey ||
				key == toggleColorKey);
	}
}
