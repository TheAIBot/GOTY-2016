package Control.GameEngine;

import Game.Board.RenderInfo;
import Game.Board.Tile;
import View.GraphicsPanel;

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
