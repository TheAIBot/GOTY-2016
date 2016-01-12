package Game.Control.GameEngine;

import Game.Model.Board.Tile;
import Game.View.Animate;
import Game.View.GraphicsPanel;
import Game.View.RenderInfo;

public class GraphicsManager {
	//private ConsoleGraphics console;
	private GraphicsPanel panel = new GraphicsPanel();
	
	public GraphicsManager()
	{
		Animate.start(this);
	}
	
	public void renderTiles(Tile[] tiles, RenderInfo renderInfo){
		panel.setRenderInfo(tiles, renderInfo);
		panel.repaint();
	}
	
	public GraphicsPanel getGraphicsPanel(){
		return this.panel;
	}
	
	public void repaint()
	{
		panel.repaint();
	}
}
