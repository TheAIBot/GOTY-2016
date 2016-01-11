package Game.Model.Board;

public class RenderInfo {
	public boolean renderColor;
	public int xOffset, yOffset;

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
