package Game.View;

import java.awt.geom.Point2D;

public interface AnimationInfo {
	
	public Point2D.Double getPosition();
	
	public Point2D.Double getPreviousPosition();
	
	public void finishedMoving();
}
