package Game.Model.Animation;

import java.awt.geom.Point2D;

/**
 * Interface implemented by classes that need to do an animation (among others SinglePlayerBoard)
 */
public interface AnimationInfo {
	
	public Point2D.Double getCurrentPosition();
	
	public Point2D.Double getGoingTowrdsPosition();
	
	public void finishedMoving();
}
