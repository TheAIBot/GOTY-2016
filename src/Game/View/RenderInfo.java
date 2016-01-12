package Game.View;

import java.util.ArrayList;
import java.util.HashSet;

import Game.View.Animation.AnimationInfo;

public class RenderInfo {
	public boolean renderColor;
	public int xOffset, yOffset;
<<<<<<< HEAD:src/Game/View/RenderInfo.java
	public HashSet<AnimationInfo> toAnimate = new HashSet<AnimationInfo>();
<<<<<<< HEAD
=======
	public double imageScale;
>>>>>>> refs/remotes/origin/Emil:src/Game/Model/Board/RenderInfo.java
=======
	private int size;
>>>>>>> refs/remotes/origin/Andreas-2-

	public RenderInfo(boolean renderColor, int size) {
		this.renderColor = renderColor;
		xOffset = 0;
		yOffset = 0;
<<<<<<< HEAD
		imageScale = 1;
=======
		this.size = size;
>>>>>>> refs/remotes/origin/Andreas-2-
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

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	

}
