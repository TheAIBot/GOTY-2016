package Control.GameEngine;

import Model.Tile;
import View.Screen;

public class GraphicsManeger {
	private Screen screen;
	
	public GraphicsManeger(Screen screen) {
		this.screen = screen;
	}
	
	public void renderTiles(Tile[] tiles){
		for (Tile tile : tiles) {
			screen.render(tile);
		}
	}
}
