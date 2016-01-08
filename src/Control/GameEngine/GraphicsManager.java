package Control.GameEngine;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.sun.javafx.scene.control.skin.TitledPaneSkin;

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
	
	public void windowResized(Rectangle newSize, Tile[] tiles)
	{
    	panel.windowResized(new Point((int)panel.getBounds().getWidth(), (int)panel.getBounds().getHeight()));
    	screen.windowResized(panel.getBounds(), panel.getGImage());
    	renderTiles(tiles);
	}
}
