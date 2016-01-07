package Control.GameEngine;

import Model.GraphicsPanel;
import Model.Tile;
import View.Screen;

public class GraphicsManager {
	//private ConsoleGraphics console;
	private Screen screen;
	private GraphicsPanel panel;
	
	public GraphicsManager(Screen screen, GraphicsPanel panel) {
		this.panel = panel;
		this.screen = screen;
	}
	
	public void renderTiles(Tile[] tiles){
		screen.clear();
		for (Tile tile : tiles) {
			if (tile != null) {
				screen.render(tile);
			}
		}
		panel.repaint();
	}
	
	public GraphicsPanel getGraphicsPanel(){
		return this.panel;
	}
}
