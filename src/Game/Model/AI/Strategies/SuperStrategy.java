package Game.Model.AI.Strategies;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import Game.Model.Board.Directions;
import Game.Model.Board.Tile;

public abstract class SuperStrategy {
	
	public abstract Directions[] getStrategyDirections(Tile[] tiles, Point2D.Double start, Point2D.Double end, ArrayList<Point2D.Double> lockedTilePositions);
}
