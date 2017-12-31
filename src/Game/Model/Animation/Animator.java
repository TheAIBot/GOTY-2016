package Game.Model.Animation;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.Timer;

public class Animator  {
	private HashSet<AnimationInfo> toAnimate = new HashSet<AnimationInfo>();
	private static final Point2D.Double MIN_MOVEMENT_PER_FRAME = new Point2D.Double(0.04, 0.04);
	//EPSILON is used to handle rounding errors checking the positioning of the tiles
	private static final double EPSILON = 0.02;
	private final AnimateUpdateListener listener;
	//animationTimer makes sure to animate every 16 ms
	private final Timer animationTimer = new Timer(16, x -> updateAnimators());
	
	public Animator(AnimateUpdateListener listener)
	{
		this.listener = listener;
	}
		
	public synchronized void startAnimation(ConcurrentLinkedQueue<AnimationInfo> animators)
	{
		AnimationInfo aniInfo;
		while ((aniInfo = animators.poll()) != null) {
			toAnimate.add(aniInfo);
		}

		//If there is animators to animate, start animationTimer
		if (toAnimate.size()  > 0 && !animationTimer.isRunning()) {
			animationTimer.start();
			System.out.println("started animation");
		}
	}
	
	private synchronized void updateAnimators()
	{
		//adjust maxMovementPerFrame according to the number of tiles that needs to be animated. 
		//Many tiles = faster movement. Fewer tiles = slower movement. 
		final double extraMovementPerAnimation = 0.00002;
		final double movementovementPerFrame = MIN_MOVEMENT_PER_FRAME.x + toAnimate.size() * extraMovementPerAnimation;
		
		//need to do it this way so items can be removed while iterating
		for (Iterator<AnimationInfo> i = toAnimate.iterator(); i.hasNext();) {
			final AnimationInfo animationInfo = i.next();
			final Point2D.Double position = animationInfo.getGoingTowardsPosition();
			final Point2D.Double previousPosition = animationInfo.getCurrentPosition();

			//If the current animationInfo is close enough to the final position, then stop the animation.
			//else move the animationInfo and keep it
			if (Math.abs(position.x - previousPosition.x) < movementovementPerFrame + EPSILON &&
				Math.abs(position.y - previousPosition.y) < movementovementPerFrame + EPSILON) {
				previousPosition.setLocation(Math.round(position.x), Math.round(position.y));
				animationInfo.finishedMoving();
				i.remove();
			}
			else {
				updatePosition(position, previousPosition, movementovementPerFrame);
			}
		}
		//Stop animating when all tiles are positioned correctly.
		if (toAnimate.size() == 0) {
			animationTimer.stop();
		}

		listener.animateUpdate();
	}

	private void updatePosition(Point2D.Double position, Point2D.Double previousPosition, double movementovementPerFrame)
	{
		final double dx = position.x - previousPosition.x;
		final double dy = position.y - previousPosition.y;
		final double vectorLength = Math.sqrt(dx * dx + dy * dy);

		final double moveX = dx * (movementovementPerFrame / vectorLength);
		final double moveY = dy * (movementovementPerFrame / vectorLength);

		previousPosition.setLocation(previousPosition.x + moveX, previousPosition.y + moveY);
	}
}
