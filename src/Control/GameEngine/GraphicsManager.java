package Control.GameEngine;

import java.awt.Point;
import java.awt.Rectangle;

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
		int drawn = 0;
		for (Tile tile : tiles) {
			if (tile != null && screen.isInsideDisplay(tile)) {
				
			}
		}
		System.out.println(drawn);
		panel.repaint();
	}
	
	public GraphicsPanel getGraphicsPanel(){
		return this.panel;
	}
	
	public void windowResized(Rectangle newSize, Tile[] tiles)
	{
    	panel.windowResized(new Point((int)panel.getBounds().getWidth(), (int)panel.getBounds().getHeight()));
    	screen.windowResized(panel.getBounds(), panel.getGImage());
    	renderTiles(tiles);
	}
}
