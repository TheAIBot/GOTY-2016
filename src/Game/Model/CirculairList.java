package Game.Model;

/**
 * A list with a fixed amount of items where it's possible to request  the next item in the list
 * and when it reaches the end then it loops back to the starts and takes from there
 *
 * @param <T>  The type this list will contain
 */
public class CirculairList<T> {
	private int index = 0;
	private final T[] items;
	
	/**
	 * 
	 * @param items the items to loop through
	 */
	public CirculairList(T[] items)
	{
		this.items = items;
	}
	
	/**
	 * Gets the next element in the list
	 * or loops back and returns the first item if the end of the list has passed
	 * @return
	 */
	public T getNext()
	{
		T toReturn = items[index];
		moveIndex();
		return toReturn;
	}
	
	/**
	 * moves the item index one forward
	 * or loops back if the end of the list has been passed
	 */
	private void moveIndex()
	{
		index++;
		//index % items.length
		if (index == items.length) {
			index = 0;
		}
	}
	
	/**
	 * get the array this list contains
	 * @return
	 */
	public T[] getArray()
	{
		return items;
	}
}
