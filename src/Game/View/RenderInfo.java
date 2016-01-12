package Game.View;

import java.util.ArrayList;
import java.util.HashSet;

import Game.View.Animation.AnimationInfo;

public class RenderInfo {
	public boolean renderColor;
	public int xOffset, yOffset;
<<<<<<< HEAD:src/Game/View/RenderInfo.java
	public HashSet<AnimationInfo> toAnimate = new HashSet<AnimationInfo>();
=======
	public double imageScale;
>>>>>>> refs/remotes/origin/Emil:src/Game/Model/Board/RenderInfo.java

	public RenderInfo(boolean renderColor) {
		this.renderColor = renderColor;
		xOffset = 0;
		yOffset = 0;
		imageScale = 1;
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
}
