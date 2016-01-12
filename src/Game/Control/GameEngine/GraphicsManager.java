package Game.Control.GameEngine;

import Game.Model.Board.Tile;
import Game.View.Colorfull;
import Game.View.Displayable;
import Game.View.GraphicsPanel;
<<<<<<< HEAD
import Game.View.RenderInfo;
import Game.View.Animation.Animator;
import Game.View.Animation.AnimateUpdateListener;
=======
import Game.View.Numreable;
>>>>>>> refs/remotes/origin/Jesper

public class GraphicsManager implements AnimateUpdateListener {
	//private ConsoleGraphics console;
<<<<<<< HEAD
	private Animator animator = new Animator(this);
	private GraphicsPanel panel = new GraphicsPanel();
	
	public void renderTiles(Tile[] tiles, RenderInfo renderInfo){
		panel.setRenderInfo(tiles, renderInfo);
		checkForNewAnimations(renderInfo);
		panel.repaint();
=======
	private GameEngine gEngine;
	private GraphicsPanel panel = new GraphicsPanel(this);
	RenderInfo renderInfo;
	
	public GraphicsManager(GameEngine gEngine) {
		this.gEngine = gEngine;
	}
	
	public void renderTiles(Tile[] tiles, RenderInfo renderInfo){
		panel.setRenderInfo(tiles, renderInfo);
		this.renderInfo = renderInfo;
		panel.repaint();	
>>>>>>> refs/remotes/origin/Jesper
	}
	
	public void renderScreen(){
		panel.render();
	}
	
	public GraphicsPanel getGraphicsPanel(){
		return this.panel;
	}
	
<<<<<<< HEAD
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
=======
	public Displayable[] getDisplayablesToRender(){
		if (!renderInfo.renderColor) {
			return gEngine.getTiles();
		} else return null;
	}
	
	public Numreable[] getNumreablesToRender(){
		return gEngine.getTiles();
	}
	
	public Colorfull[] getColorfullsToRender(){
		if (renderInfo.renderColor) {
			return gEngine.getTiles();
		} else return null;
>>>>>>> refs/remotes/origin/Jesper
	}
}
