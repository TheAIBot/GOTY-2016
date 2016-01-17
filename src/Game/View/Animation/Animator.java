package Game.View.Animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;

import javax.swing.Timer;

public class Animator  {
	private HashSet<AnimationInfo> toAnimate = new HashSet<AnimationInfo>();
	private Point2D.Double maxMovementPerFrame = new Point2D.Double(0.06,0.06);
	//EPSILON is used to handle rounding errors checking the positioning of the tiles
	private static final double EPSILON = 0.02;
	private AnimateUpdateListener listener;
	//animationTimer makes sure to animate every 16 ms
	private Timer animationTimer = new Timer(16, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateAnimators();
		}
	});
	
	public Animator(AnimateUpdateListener listener)
	{
		this.listener = listener;
	}
		
	public void startAnimation(ConcurrentLinkedQueue<AnimationInfo> animators)
	{
		AnimationInfo aniInfo;
		synchronized (toAnimate) {
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
		maxMovementPerFrame = new Point2D.Double(0.05 + toAnimate.size() * 0.00002, 0.05 + toAnimate.size() * 0.00002);
		HashSet<AnimationInfo> toKeep = new HashSet<AnimationInfo>();
		synchronized (toAnimate) {
			for (AnimationInfo animationInfo : toAnimate) {
				//If the current animationInfo is close enough to the final position, then stop the animation.
				//ELse move the animationInfo and add it to toKeep
				Point2D.Double position = animationInfo.getPosition();
				Point2D.Double previousPosition = animationInfo.getPreviousPosition();
				if (Math.abs(position.x - previousPosition.x) < maxMovementPerFrame.x + EPSILON &&
					Math.abs(position.y - previousPosition.y) < maxMovementPerFrame.y + EPSILON) {
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
		Point2D.Double abDistance = new Point2D.Double(nowPos.x - prevPos.x, nowPos.y - prevPos.y);
		
		//Check if the difference of x- and y-values is big enough to be animated
		if (Math.abs(abDistance.x) < maxMovementPerFrame.x) {
			abDistance.x = 0;
		}
		if (Math.abs(abDistance.y) < maxMovementPerFrame.y) {
			abDistance.y = 0;
		}
		double xyDifference = Math.abs(abDistance.x / abDistance.y);
		xyDifference = (Double.isInfinite(xyDifference)) ? 1 : xyDifference;
		xyDifference = (Double.isNaN(xyDifference)) ? 0 : xyDifference;
		
		//Get the correct operational sign for the coordinates
		Point2D.Double signVector = getSignVector(abDistance);
		
		//Return the vector for moving the tile animation
		return new Point2D.Double(maxMovementPerFrame.x * xyDifference * signVector.x, 
								  maxMovementPerFrame.y * signVector.y);
	}
	
	/**
	 * 
	 * @param abDistance
	 * @return a Point2D.Double with the correct operational sign for coordinates
	 */
	private Point2D.Double getSignVector(Point2D.Double abDistance)
	{
		Point2D.Double signVector = new Point2D.Double();
		if (abDistance.x == 0) {
			signVector.x = 0;
		} else if (abDistance.x < 0) {
			signVector.x = -1;
		} else {
			signVector.x = 1;
		}
		if (abDistance.y == 0) {
			signVector.y = 0;
		} else if (abDistance.y < 0) {
			signVector.y = -1;
		} else {
			signVector.y = 1;
		}
		return signVector;
	}
			
	
}
