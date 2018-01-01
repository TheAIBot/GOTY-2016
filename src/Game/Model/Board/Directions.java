package Game.Model.Board;

public enum Directions {
	LEFT(-1, 0) {
		@Override
		public Directions getOppositeDirection() {
			return Directions.RIGHT;
		}
	},
	RIGHT(1, 0) {
		@Override
		public Directions getOppositeDirection() {
			return Directions.LEFT;
		}
	},
	UP(0, -1) {
		@Override
		public Directions getOppositeDirection() {
			return Directions.DOWN;
		}
	},
	DOWN(0, 1) {
		@Override
		public Directions getOppositeDirection() {
			return Directions.UP;
		}
	};
	
	public abstract Directions getOppositeDirection();
	public final int translateX;
	public final int translateY;

	private Directions(int x, int y)
	{
		translateX = x;
		translateY = y;
	}
}
