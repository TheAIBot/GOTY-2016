package Game.View;

import java.util.HashSet;

import Game.View.Animation.AnimationInfo;

public class RenderInfo {
	public boolean renderColor;
	public int xOffset = 0;
	public int yOffset = 0;
	public HashSet<AnimationInfo> toAnimate = new HashSet<AnimationInfo>();
	public double imageScale = 1;
	private final int size;

	public RenderInfo(boolean renderColor, int size) {
		this.renderColor = renderColor;
		this.size = size;
	}
	
	public void addOffset(int xOffset, int yOffset) {
		this.xOffset += xOffset;
		this.yOffset += yOffset;
	}
	
	public void addImageScale(double imageScale) {
		this.imageScale += imageScale;
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
}
