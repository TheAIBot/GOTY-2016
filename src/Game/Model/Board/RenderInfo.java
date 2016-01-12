package Game.Model.Board;

public class RenderInfo {
	public boolean renderColor;
	public int xOffset, yOffset;
	public double imageScale;

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
