package Game.Board;

public enum Directions {
	LEFT {
		@Override
		public Directions getOppositeDirection() {
			return Directions.RIGHT;
		}
	},
	RIGHT {
		@Override
		public Directions getOppositeDirection() {
			return Directions.LEFT;
		}
	},
	UP {
		@Override
		public Directions getOppositeDirection() {
			return Directions.DOWN;
		}
	},
	DOWN {
		@Override
		public Directions getOppositeDirection() {
			return Directions.UP;
		}
	},
	A {
		@Override
		public Directions getOppositeDirection() {
			return Directions.D;
		}
	},
	D {
		@Override
		public Directions getOppositeDirection() {
			return Directions.A;
		}
	},
	W {
		@Override
		public Directions getOppositeDirection() {
			return Directions.S;
		}
	},
	S {
		@Override
		public Directions getOppositeDirection() {
			return Directions.W;
		}
	};
	public abstract Directions getOppositeDirection();
}
