package Control.GameEngine;

import Model.GraphicsPanel;
import Model.Tile;
import View.Screen;

<<<<<<< HEAD:src/Control/GameEngine/GraphicsManager.java
public class GraphicsManager implements java.io.Serializable {
=======
public class GraphicsManager {
>>>>>>> origin/Andreas:src/Control/GameEngine/GraphicsManager.java
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
}
