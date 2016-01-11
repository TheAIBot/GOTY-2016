package Game.AI;

import Game.Board.Tile;

public interface Strategy {
	public boolean canUse(Tile[] tiles);
	
	public void calculatePath(Tile[] tiles);
	
	public void getStep();
}
