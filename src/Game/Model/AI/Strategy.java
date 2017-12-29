package Game.Model.AI;

import Game.Model.Board.Tile;

public interface Strategy {
	public boolean canUse(Tile[] tiles);
	
	public void calculatePath(Tile[] tiles);
	
	public String getStep();
}
