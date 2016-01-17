package Game.View.Animation;

/**
 * Interface implementet by classes, which need to animate themselves (among others Tile) 
 */
public interface ToAnimateListener {
	public void toAnimate(AnimationInfo tile);
}
