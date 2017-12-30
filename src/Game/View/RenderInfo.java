package Game.View;

import java.io.Serializable;
import java.util.concurrent.ConcurrentLinkedQueue;

import Game.Model.Animation.AnimationInfo;

/**
 * Class used to organize relevant information used in the rendering process.
 */
public class RenderInfo implements Serializable {
	private static final long serialVersionUID = 2299668499175280826L;
	public boolean renderColor;
	public int xOffset = 0;
	public int yOffset = 0;
	//ConcurrentLinkedQueue is used because multiple thread manipulates and reads this queue while 
	//randomizing and animating the board at the same time
	public ConcurrentLinkedQueue<AnimationInfo> toAnimate = new ConcurrentLinkedQueue<AnimationInfo>();
	public double imageScale = 1;
	private final int size; //size of the board in tiles
	private static final double MIN_ZOOM_LEVEL = 0.05;

	public RenderInfo(boolean renderColor, int size) {
		this.renderColor = renderColor;
		this.size = size;
	}

	/**
	 * Adds to the offset to simulate the camera moving around the board.
	 * xOffset and yOffset is in tile precision and added when objects are
	 * rendered to the object's position.
	 * 
	 * @param xOffset
	 * @param yOffset
	 */
	public void addOffset(int xOffset, int yOffset) {
		this.xOffset += xOffset;
		this.yOffset += yOffset;
	}

	/**
	 * Adds to the image scale to zoom in and out
	 * 
	 * @param imageScale
	 */
	public void addImageScale(double imageScale) {
		if (this.imageScale + imageScale >= MIN_ZOOM_LEVEL) {
			this.imageScale += imageScale;
		}
	}

	/**
	 * Toggles between the two viewing modes: 1. Tiles with images 2. Tiles with
	 * colors and numbers.
	 */
	public void toggleRenderColor() {
		renderColor = !renderColor;
	}

	/**
	 * @return the size of the gameboard in pixels
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the image scale to the specified value. Method is used when the
	 * gameboard is created to specify a good beginning viewing size of the board
	 * @param newScale
	 */
	public void setImageScale(double newScale) {
		imageScale = newScale;
	}
}
