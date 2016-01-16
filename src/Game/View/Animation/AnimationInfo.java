package Game.View.Animation;

import java.awt.geom.Point2D;

/**
 * Interface implemented by classes that need to do an animation (among others SinglePlayerBoard)
 */
public interface AnimationInfo {
	
	public Point2D.Double getPosition();
	
	public Point2D.Double getPreviousPosition();
	
	public void finishedMoving();
}
