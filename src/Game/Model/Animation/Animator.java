package Game.Model.Animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;

public class Animator  {
	private HashSet<AnimationInfo> toAnimate = new HashSet<AnimationInfo>();
	private static Point2D.Double MIN_MOVEMENT_PER_FRAME = new Point2D.Double(0.04, 0.04);
	private Point2D.Double movementovementPerFrame;
	//EPSILON is used to handle rounding errors checking the positioning of the tiles
	private static final double EPSILON = 0.02;
	private AnimateUpdateListener listener;
	//animationTimer makes sure to animate every 16 ms
	private final Timer animationTimer = new Timer(16, x -> updateAnimators());
	
	public Animator(AnimateUpdateListener listener)
	{
		this.listener = listener;
	}
		
	public void startAnimation(ConcurrentLinkedQueue<AnimationInfo> animators)
	{
		synchronized (toAnimate) {
			AnimationInfo aniInfo;
			while ((aniInfo = animators.poll()) != null) {
				toAnimate.add(aniInfo);
			}
		}
		//If there is animators to animate, start animationTimer
		if (toAnimate.size() > 0) {
			animationTimer.start();
		}
	}
	
	private void updateAnimators()
	{
		//adjust maxMovementPerFrame according to the number of tiles that needs to be animated. 
		//Many tiles = faster movement. Fewer tiles = slower movement. 
		final double extraMovementPerAnimation = 0.00002;
		movementovementPerFrame = new Point2D.Double(MIN_MOVEMENT_PER_FRAME.x + toAnimate.size() * extraMovementPerAnimation, 
													 MIN_MOVEMENT_PER_FRAME.y + toAnimate.size() * extraMovementPerAnimation);
		HashSet<AnimationInfo> toKeep = new HashSet<AnimationInfo>();
		synchronized (toAnimate) {
			for (AnimationInfo animationInfo : toAnimate) {
				//If the current animationInfo is close enough to the final position, then stop the animation.
				//ELse move the animationInfo and add it to toKeep
				Point2D.Double position = animationInfo.getPosition();
				Point2D.Double previousPosition = animationInfo.getPreviousPosition();
				if (Math.abs(position.x - previousPosition.x) < movementovementPerFrame.x + EPSILON &&
					Math.abs(position.y - previousPosition.y) < movementovementPerFrame.y + EPSILON) {
					previousPosition.setLocation(Math.round(position.x), Math.round(position.y));
					animationInfo.finishedMoving();
				}
				else {
					Point2D.Double moveVector = getMoveVector(previousPosition, position);
					previousPosition.setLocation(previousPosition.x + moveVector.x, previousPosition.y + moveVector.y);
					toKeep.add(animationInfo);
				}
			}
			//Add the tiles that still needs to be animated to toAnimate
			toAnimate = toKeep;
			//Stop animating when all tiles are positioned correctly.
			if (toAnimate.size() == 0) {
				animationTimer.stop();
				System.out.println("animation stopped");
			}
		}
		listener.animateUpdate();
	}
	
	private Point2D.Double getMoveVector(Point2D.Double prevPos, Point2D.Double nowPos)
	{
		double abX = nowPos.x - prevPos.x;
		double abY = nowPos.y - prevPos.y;
		
		//Check if the difference of x- and y-values is big enough to be animated
		if (Math.abs(abX) < movementovementPerFrame.x) {
			abX = 0;
		}
		if (Math.abs(abY) < movementovementPerFrame.y) {
			abY = 0;
		}
		double xyDifference = Math.abs(abX / abY);
		xyDifference = (Double.isInfinite(xyDifference)) ? 1 : xyDifference;
		xyDifference = (Double.isNaN(xyDifference)) ? 0 : xyDifference;
		
		//Get the correct operational sign for the coordinates
		Point2D.Double signVector = getSignVector(abX ,abY);
		
		//Return the vector for moving the tile animation
		return new Point2D.Double(movementovementPerFrame.x * xyDifference * signVector.x, 
								  movementovementPerFrame.y * signVector.y);
	}
	
	/**
	 * 
	 * @param abDistance
	 * @return a Point2D.Double with the correct operational sign for coordinates
	 */
	private Point2D.Double getSignVector(double x, double y)
	{
		Point2D.Double signVector = new Point2D.Double();
		if (x == 0) {
			signVector.x = 0;
		} else if (x < 0) {
			signVector.x = -1;
		} else {
			signVector.x = 1;
		}
		if (y == 0) {
			signVector.y = 0;
		} else if (y < 0) {
			signVector.y = -1;
		} else {
			signVector.y = 1;
		}
		return signVector;
	}
			
	
}
