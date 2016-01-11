package Game.Model.Board;

public class RenderInfo {
	public boolean renderColor;
	
	public RenderInfo(boolean renderColor)
	{
		this.renderColor = renderColor;
	}
	
	public void toggleRenderColor()
	{
		renderColor = !renderColor;
	}
}
