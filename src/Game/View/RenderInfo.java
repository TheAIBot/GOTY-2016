package Game.View;

import java.io.Serializable;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import Game.View.Animation.AnimationInfo;

public class RenderInfo implements Serializable {
	public boolean renderColor;
	public int xOffset = 0;
	public int yOffset = 0;
	//ConcurrentLinkedQueue is used because multiple thread manipulates and reads this queue while 
	//randomizing and animating the board at the same time
	public ConcurrentLinkedQueue<AnimationInfo> toAnimate = new ConcurrentLinkedQueue<AnimationInfo>();
	public double imageScale = 1;
	//size of the board
	private final int size;
	private static final double MIN_ZOOM_LEVEL = 0.05;

	public RenderInfo(boolean renderColor, int size) {
		this.renderColor = renderColor;
		this.size = size;
	}
	
	public void addOffset(int xOffset, int yOffset) {
		this.xOffset += xOffset;
		this.yOffset += yOffset;
	}
	
	public void addImageScale(double imageScale) {
		if (this.imageScale + imageScale >= MIN_ZOOM_LEVEL) {
			this.imageScale += imageScale;
		}
	}

	public void toggleRenderColor() {
		renderColor = !renderColor;
	}

	public int getSize() {
		return size;
	}
	
	public void setImageScale(double newScale)
	{
		imageScale = newScale;
	}
}
