package Game.View;

import java.io.Serializable;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import Game.View.Animation.AnimationInfo;

public class RenderInfo implements Serializable {
	public boolean renderColor;
	public int xOffset = 0;
	public int yOffset = 0;
	public ConcurrentLinkedQueue<AnimationInfo> toAnimate = new ConcurrentLinkedQueue<AnimationInfo>();
	public double imageScale = 1;
	private final int size;
<<<<<<< HEAD
	private static final double MIN_ZOOM_LEVEL = 0.1;
=======
	private static final double MIN_ZOOM_LEVEL = 0.05;
>>>>>>> refs/remotes/origin/Andreas

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

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	public void setImageScale(double newScale)
	{
		imageScale = newScale;
	}
}
