package Game.Control.GameEngine;

import Game.Model.Board.Tile;
import Game.View.Colorfull;
import Game.View.Displayable;
import Game.View.GraphicsPanel;
import Game.View.Numreable;
import Game.View.RenderInfo;
import Game.View.Animation.AnimateUpdateListener;
import Game.View.Animation.Animator;

public class GraphicsManager implements AnimateUpdateListener {
	//private ConsoleGraphics console;
	private GameEngine gEngine;
	private GraphicsPanel panel = new GraphicsPanel(this);
	private Animator animator = new Animator(this);
	RenderInfo renderInfo;
	
	public GraphicsManager(GameEngine gEngine) {
		this.gEngine = gEngine;
	}
	
	public void renderTiles(Tile[] tiles, RenderInfo renderInfo){
		panel.setRenderInfo(tiles, renderInfo);
		this.renderInfo = renderInfo;
		checkForNewAnimations(renderInfo);
		panel.repaint();	
	}
	
	public void renderScreen(){
		panel.render();
	}
	
	public GraphicsPanel getGraphicsPanel(){
		return this.panel;
	}
	
	private void animate(){
		animator.startAnimation(renderInfo.toAnimate);
	}
	
	public Displayable[] getDisplayablesToRender(){
		if (!renderInfo.renderColor) {
			animate();
			return gEngine.getTiles();
		} else return null;
	}
	
	public Numreable[] getNumreablesToRender(){
		return gEngine.getTiles();
	}
	
	public Colorfull[] getColorfullsToRender(){
		if (renderInfo.renderColor) {
			animate();
			animator.startAnimation(renderInfo.toAnimate);
			return gEngine.getTiles();
		} else return null;
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
