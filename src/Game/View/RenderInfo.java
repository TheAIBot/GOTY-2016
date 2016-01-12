package Game.View;

import java.util.ArrayList;
import java.util.HashSet;

public class RenderInfo {
	public boolean renderColor;
	public int xOffset, yOffset;
	public HashSet<AnimationInfo> toAnimate = new HashSet<AnimationInfo>();

	public RenderInfo(boolean renderColor) {
		this.renderColor = renderColor;
		xOffset = 0;
		yOffset = 0;
	}
	
	public void addOffset(int xOffset, int yOffset) {
		this.xOffset += xOffset;
		this.yOffset += yOffset;
	}

	public void toggleRenderColor() {
		renderColor = !renderColor;
	}
}
