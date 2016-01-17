package Game.Model.Animation;

/**
 * Interface implemented by classes, which need to do tell other classes to
 * update their animations (among others GraphicsManger)
 */
public interface AnimateUpdateListener {
	public void animateUpdate();
}
