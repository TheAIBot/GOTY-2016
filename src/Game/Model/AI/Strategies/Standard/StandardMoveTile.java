package Game.Model.AI.Strategies.Standard;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import Game.Model.AI.Strategies.SuperStrategy;
import Game.Model.Board.Directions;
import Game.Model.Board.Tile;

public class StandardMoveTile extends SuperStrategy {

	@Override
	public Directions[] getStrategyDirections(Tile[] tiles, Double start, Double end, ArrayList<Double> lockedTilePositions) {
		ArrayList<Directions> directionMoves = new ArrayList<Directions>(); 
		Point2D.Double currentPos = start;
		for (int i = tiles.length -  2; i < tiles.length; i++) {
			
		}
	}

}
