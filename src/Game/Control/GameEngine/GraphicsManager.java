package Game.Control.GameEngine;

import Game.Model.Board.RenderInfo;
import Game.Model.Board.Tile;
import Game.View.GraphicsPanel;

public class GraphicsManager {
	//private ConsoleGraphics console;
	private GraphicsPanel panel = new GraphicsPanel();
	
	public void renderTiles(Tile[] tiles, RenderInfo renderInfo){
		panel.setRenderInfo(tiles, renderInfo);
		panel.repaint();
	}
	
	public GraphicsPanel getGraphicsPanel(){
		return this.panel;
	}
}
