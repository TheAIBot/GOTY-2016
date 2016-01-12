package Game.Control.GameEngine;

import Game.Model.Board.Tile;
import Game.View.GraphicsPanel;
import Game.View.RenderInfo;
import Game.View.Animation.Animator;
import Game.View.Animation.AnimateUpdateListener;

public class GraphicsManager implements AnimateUpdateListener {
	//private ConsoleGraphics console;
	private Animator animator = new Animator(this);
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
