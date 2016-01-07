package Control.GameEngine;

import Model.GraphicsPanel;
import Model.Tile;
import View.Screen;

public class GraphicsManager {
	private Screen screen;
	private GraphicsPanel panel;
	
	public GraphicsManager(Screen screen, GraphicsPanel panel) {
		this.panel = panel;
		this.screen = screen;
	}
	
	public void renderTiles(Tile[] tiles) {
		clearScreen();
		for (Tile tile : tiles) {
			if (tile != null) {
				screen.render(tile);
			}
		}
		updateGraphics();
	}
	
	public void clearScreen(){
		screen.clear();
	}
	
	
	public void updateGraphics(){
		panel.repaint();
	}
	
}
