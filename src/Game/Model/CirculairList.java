package Game.Model;

public class CirculairList<T> {
	private int index = 0;
	private final T[] items;
	
	public CirculairList(T[] items)
	{
		this.items = items;
	}
	
	public T getNext()
	{
		T toReturn = items[index];
		moveIndex();
		return toReturn;
	}
	
	private void moveIndex()
	{
		index++;
		if (index == items.length) {
			index = 0;
		}
	}
	
	public T[] getArray()
	{
		return items;
	}
}
