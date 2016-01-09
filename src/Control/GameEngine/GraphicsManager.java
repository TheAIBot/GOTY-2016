package Control.GameEngine;

import java.awt.Point;
import java.awt.Rectangle;

import Model.GraphicsPanel;
import Model.RenderInfo;
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
	

	public void renderTiles(Tile[] tiles, RenderInfo renderInfo){
		screen.clear();
		int drawn = 0;
		for (Tile tile : tiles) {
			if (tile != null) {
				if (screen.render(tile, renderInfo)) {
					drawn++;
				}
			} 
		}
		System.out.println(drawn);
		panel.repaint();
	}
	
	public GraphicsPanel getGraphicsPanel(){
		return this.panel;
	}
	
	public void windowResized(Rectangle newSize, Tile[] tiles, RenderInfo renderInfo)
	{
    	panel.windowResized(new Point(newSize.width, newSize.width));
    	screen.windowResized(panel.getBounds(), panel.getGImage());
    	renderTiles(tiles, renderInfo);
	}
}
