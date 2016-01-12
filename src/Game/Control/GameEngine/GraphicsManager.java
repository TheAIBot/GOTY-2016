package Game.Control.GameEngine;

import Game.Model.Board.Tile;
import Game.View.Animate;
import Game.View.AnimateUpdateListener;
import Game.View.GraphicsPanel;
import Game.View.RenderInfo;

public class GraphicsManager implements AnimateUpdateListener {
	//private ConsoleGraphics console;
	private Animate animator = new Animate(this);
	private GraphicsPanel panel = new GraphicsPanel();
	
	public void renderTiles(Tile[] tiles, RenderInfo renderInfo){
		panel.setRenderInfo(tiles, renderInfo);
		checkForNewAnimations(renderInfo);
		panel.repaint();
	}
	
	public GraphicsPanel getGraphicsPanel(){
		return this.panel;
	}
	
	public void repaint()
	{
		panel.repaint();
	}

	public void checkForNewAnimations(RenderInfo renderInfo) {
		if (renderInfo.toAnimate.size() > 0) {
			animator.startAnimation(renderInfo.toAnimate);
			renderInfo.toAnimate.clear();
		}
	}

	@Override
	public void animateUpdate() {
		panel.repaint();		
	}
}
