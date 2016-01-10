package Model.AI;

import Model.Tile;

public interface Strategy {
	public boolean canUse(Tile[] tiles);
	
	public void calculatePath(Tile[] tiles);
	
	public void getStep();
}
