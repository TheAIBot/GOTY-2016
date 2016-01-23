package Game.Model.Path;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

import Game.Model.Board.Directions;

public class PathFinder {
	
	private static final int BLOCKED_WAY = -1;
	
	private static int[][] getdistanceMap(ArrayList<Point2D.Double> lockedTiles, Point2D.Double startPosition, Point2D.Double endPosition, int size)
	{
		int[][] map = new int[size][size];
		
		for (Point2D.Double locked : lockedTiles) {
			map[(int)locked.x][(int)locked.y] = BLOCKED_WAY;
		}
		
		HashSet<Point2D.Double> toDo = new HashSet<Point2D.Double>();
		toDo.add(startPosition);
		int distance = 1;
		while (toDo.size() > 0) 
		{
			HashSet<Point2D.Double> newPoints = new HashSet<Point2D.Double>();
			boolean stop = false;
			
			for (Point2D.Double toCheck : toDo) 
			{
				if (toCheck.x > 0 && 
					map[(int)toCheck.x - 1][(int)toCheck.y] == 0) 
				{
					Point2D.Double insideBoard = new Point2D.Double(toCheck.x - 1, toCheck.y);
					if (insideBoard.equals(endPosition)) 
					{
						stop = true;
						break;
					}
					map[(int)insideBoard.x][(int)insideBoard.y] = distance;
					newPoints.add(insideBoard);
				}
				if (toCheck.x < size - 1 && 
					map[(int)toCheck.x + 1][(int)toCheck.y] == 0) 
				{
					Point2D.Double insideBoard = new Point2D.Double(toCheck.x + 1, toCheck.y);
					if (insideBoard.equals(endPosition)) 
					{
						stop = true;
						break;
					}
					map[(int)insideBoard.x][(int)insideBoard.y] = distance;
					newPoints.add(insideBoard);
				}
				if (toCheck.y > 0 && 
					map[(int)toCheck.x][(int)toCheck.y + 1] == 0) 
				{
					Point2D.Double insideBoard = new Point2D.Double(toCheck.x, toCheck.y - 1);
					if (insideBoard.equals(endPosition)) 
					{
						stop = true;
						break;
					}
					map[(int)insideBoard.x][(int)insideBoard.y] = distance;
					newPoints.add(insideBoard);
				}
				if (toCheck.y < size - 1 && 
					map[(int)toCheck.x][(int)toCheck.y - 1] == 0) 
				{
					Point2D.Double insideBoard = new Point2D.Double(toCheck.x, toCheck.y + 1);
					if (insideBoard.equals(endPosition)) 
					{
						stop = true;
						break;
					}
					map[(int)insideBoard.x][(int)insideBoard.y] = distance;
					newPoints.add(insideBoard);
				}
			}
			if (stop) 
			{
				break;
			}
			distance++;
			toDo = newPoints;
		}
		map[(int)startPosition.x][(int)startPosition.y] = 0;
		
		return map;
	}

	private static Directions[] getPath(ArrayList<Point2D.Double> lockedTiles, Point2D.Double start, Point2D.Double end, int size)
	{
		int[][] map = getdistanceMap(lockedTiles, start, end, size);
		Directions[] path = new Directions[map[(int)end.x][(int)end.y]];
		int distance = map[(int)end.x][(int)end.y];
		Point2D.Double currentPosition = end;
		for (int i = 1; i < path.length; i++) {
			if (map) {
				
			}
		}
		return path;
	}
}
