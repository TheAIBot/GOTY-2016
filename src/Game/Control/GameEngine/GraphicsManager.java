package Game.Control.GameEngine;

import Game.Model.Board.RenderInfo;
import Game.Model.Board.Tile;
import Game.View.Colorfull;
import Game.View.Displayable;
import Game.View.GraphicsPanel;
import Game.View.Numreable;

public class GraphicsManager {
	//private ConsoleGraphics console;
	private GameEngine gEngine;
	private GraphicsPanel panel = new GraphicsPanel(this);
	RenderInfo renderInfo;
	
	public GraphicsManager(GameEngine gEngine) {
		this.gEngine = gEngine;
	}
	
	public void renderTiles(Tile[] tiles, RenderInfo renderInfo){
		panel.setRenderInfo(tiles, renderInfo);
		this.renderInfo = renderInfo;
		panel.repaint();	
	}
	
	public void renderScreen(){
		panel.render();
	}
	
	public GraphicsPanel getGraphicsPanel(){
		return this.panel;
	}
	
	public Displayable[] getDisplayablesToRender(){
		if (!renderInfo.renderColor) {
			return gEngine.getTiles();
		} else return null;
	}
	
	public Numreable[] getNumreablesToRender(){
		return gEngine.getTiles();
	}
	
	public Colorfull[] getColorfullsToRender(){
		if (renderInfo.renderColor) {
			return gEngine.getTiles();
		} else return null;
	}
}
