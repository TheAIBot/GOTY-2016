
package Control;

public enum Directions {
	LEFT
	{
		@Override
		public Directions getOppositeDirection()
		{
			return Directions.RIGHT;
		}
	}, 
	RIGHT
	{
		@Override
		public Directions getOppositeDirection()
		{
			return Directions.LEFT;
		}
	}, 
	UP
	{
		@Override
		public Directions getOppositeDirection()
		{
			return Directions.DOWN;
		}
	}, 
	DOWN
	{
		@Override
		public Directions getOppositeDirection()
		{
			return Directions.UP;
		}
	};
	public abstract Directions getOppositeDirection();
}
