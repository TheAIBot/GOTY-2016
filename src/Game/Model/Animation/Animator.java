package Game.Model.Animation;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.Timer;

public class Animator  {
	private HashSet<AnimationInfo> toAnimate = new HashSet<AnimationInfo>();
	private static final Point2D.Double MIN_MOVEMENT_PER_FRAME = new Point2D.Double(0.04, 0.04);
	private double movementovementPerFrame = 0;
	//EPSILON is used to handle rounding errors checking the positioning of the tiles
	private static final double EPSILON = 0.02;
	private final AnimateUpdateListener listener;
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
		if (toAnimate.size()  > 0) {
			animationTimer.start();
		}
	}
	
	private void updateAnimators()
	{
		//adjust maxMovementPerFrame according to the number of tiles that needs to be animated. 
		//Many tiles = faster movement. Fewer tiles = slower movement. 
		final double extraMovementPerAnimation = 0.00002;
		movementovementPerFrame = MIN_MOVEMENT_PER_FRAME.x + toAnimate.size() * extraMovementPerAnimation;
		
		HashSet<AnimationInfo> toKeep = new HashSet<AnimationInfo>();
		synchronized (toAnimate) {
			for (AnimationInfo animationInfo : toAnimate) {
				//If the current animationInfo is close enough to the final position, then stop the animation.
				//ELse move the animationInfo and add it to toKeep
				Point2D.Double position = animationInfo.getGoingTowardsPosition();
				Point2D.Double previousPosition = animationInfo.getCurrentPosition();
				if (Math.abs(position.x - previousPosition.x) < movementovementPerFrame + EPSILON &&
					Math.abs(position.y - previousPosition.y) < movementovementPerFrame + EPSILON) {
					previousPosition.setLocation(Math.round(position.x), Math.round(position.y));
					animationInfo.finishedMoving();
				}
				else {
					updatePosition(position, previousPosition);
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

	private void updatePosition(Point2D.Double position, Point2D.Double previousPosition)
	{
		final double x = position.x - previousPosition.x;
		final double y = position.y - previousPosition.y;
		final double vectorLength = Math.sqrt(x * x + y * y);

		final double moveX = x * (movementovementPerFrame / vectorLength);
		final double moveY = y * (movementovementPerFrame / vectorLength);

		previousPosition.setLocation(previousPosition.x + moveX, previousPosition.y + moveY);
	}
}
