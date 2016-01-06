package GameEngine;

public class PlayerSettings {
	private char upKey;
	private char downKey;
	private char leftKey;
	private char rightKey;
	
	private String name;

	/**
	 * @return up key
	 */
	public char getUpKey() {
		return upKey;
	}

	/**
	 * @param up the key used to press up 
	 */
	public void setUpKey(char up) {
		this.upKey = up;
	}

	/**
	 * @return down key
	 */
	public char getDownKey() {
		return downKey;
	}

	/**
	 * @param down the key used to press down
	 */
	public void setDownKey(char down) {
		this.downKey = down;
	}

	/**
	 * @return left key
	 */
	public char getLeftKey() {
		return leftKey;
	}

	/**
	 * @param left the key used to press left
	 */
	public void setLeftKey(char left) {
		this.leftKey = left;
	}

	/**
	 * @return right key
	 */
	public char getRightKey() {
		return rightKey;
	}

	/**
	 * @param right the key used to press right
	 */
	public void setRightKey(char right) {
		this.rightKey = right;
	}

	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
