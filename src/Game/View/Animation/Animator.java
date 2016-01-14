package Game.View.Animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.Timer;

public class Animator  {
	private HashSet<AnimationInfo> toAnimate = new HashSet<AnimationInfo>();
	private Point2D.Double maxMovementPerFrame = new Point2D.Double(0.06,0.06);
	private static final double EPSILON = 0.02;
	private AnimateUpdateListener listener;
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
		while ((aniInfo = animators.poll()) != null) {
			toAnimate.add(aniInfo);
		}
		if (toAnimate.size() > 0) {
			animationTimer.start();
		}
	}
	
	private void updateAnimators()
	{
		maxMovementPerFrame = new Point2D.Double(0.05 + toAnimate.size() * 0.00002, 0.05 + toAnimate.size() * 0.00002);
		//maxMovementPerFrame = new Point2D.Double(0.03, 0.03);
		HashSet<AnimationInfo> toKeep = new HashSet<AnimationInfo>();
		for (AnimationInfo animationInfo : toAnimate) {
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
		toAnimate = toKeep;
		if (toAnimate.size() == 0) {
			animationTimer.stop();
			System.out.println("animation stopped");
		}
		listener.animateUpdate();
	}
	
	private Point2D.Double getMoveVector(Point2D.Double prevPos, Point2D.Double nowPos)
	{
		Point2D.Double abDistance = new Point2D.Double(nowPos.x - prevPos.x, nowPos.y - prevPos.y);
		if (Math.abs(abDistance.x) < maxMovementPerFrame.x) {
			abDistance.x = 0;
		}
		if (Math.abs(abDistance.y) < maxMovementPerFrame.y) {
			abDistance.y = 0;
		}
		double xyDifference = Math.abs(abDistance.x / abDistance.y);
		xyDifference = (Double.isInfinite(xyDifference)) ? 1 : xyDifference;
		xyDifference = (Double.isNaN(xyDifference)) ? 0 : xyDifference;
		
		Point2D.Double signVector = getSignVector(abDistance);
		
		return new Point2D.Double(maxMovementPerFrame.x * xyDifference * signVector.x, 
								  maxMovementPerFrame.y * signVector.y);
	}
	
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
