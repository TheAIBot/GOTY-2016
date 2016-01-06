package Control.GameEngine;

import Model.GraphicsPanel;
import Model.Tile;
import View.Screen;

public class GraphicsManeger {
	private Screen screen;
	private GraphicsPanel panel;
	
	public GraphicsManeger(Screen screen, GraphicsPanel panel) {
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
}
