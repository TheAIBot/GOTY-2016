package Game.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.Timer;

import Game.Control.GameEngine.GraphicsManager;

public class Animate  {
	private HashSet<AnimationInfo> toAnimate = new HashSet<AnimationInfo>();
	private static final Point2D.Double MAX_MOVEMENT_PER_FRAME = new Point2D.Double(0.03,0.03);
	private static final double EPSILON = 2;
	private AnimateUpdateListener listener;
	private Timer animationTimer = new Timer(16, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateAnimators();	
		}
	});
	
	public Animate(AnimateUpdateListener listener)
	{
		this.listener = listener;
	}
		
	public void startAnimation(HashSet<AnimationInfo> animators)
	{
		for (AnimationInfo animationInfo : animators) {
			toAnimate.add(animationInfo);
		}
		if (toAnimate.size() > 0) {
			animationTimer.start();
		}
	}
	
	public void updateAnimators()
	{
		HashSet<AnimationInfo> toKeep = new HashSet<AnimationInfo>();
		for (AnimationInfo animationInfo : toAnimate) {
			Point2D.Double position = animationInfo.getPosition();
			Point2D.Double previousPosition = animationInfo.getPreviousPosition();
			if (Math.abs(position.x - previousPosition.x) < MAX_MOVEMENT_PER_FRAME.x * EPSILON &&
				Math.abs(position.y - previousPosition.y) < MAX_MOVEMENT_PER_FRAME.y * EPSILON) {
				previousPosition.setLocation(position.x, position.y);
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
		if (Math.abs(abDistance.x) < MAX_MOVEMENT_PER_FRAME.x) {
			abDistance.x = 0;
		}
		if (Math.abs(abDistance.y) < MAX_MOVEMENT_PER_FRAME.y) {
			abDistance.y = 0;
		}
		double xyDifference = Math.abs(abDistance.x / abDistance.y);
		xyDifference = (Double.isInfinite(xyDifference)) ? 1 : xyDifference;
		xyDifference = (Double.isNaN(xyDifference)) ? 0 : xyDifference;
		
		Point2D.Double signVector = getSignVector(abDistance);
		
		return new Point2D.Double(MAX_MOVEMENT_PER_FRAME.x * xyDifference * signVector.x, 
								  MAX_MOVEMENT_PER_FRAME.y * signVector.y);
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
