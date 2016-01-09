package Model;

public enum DifficultyLevel {
	EASY
	{
		private static final double MIN_PERCENT = 0.00;
		private static final double MAX_PERCENT = 0.50;
		
		@Override
		protected boolean isPercentageEqualToThisDifficulty(double percent) {
			return percent >= MIN_PERCENT && 
				   percent <  MAX_PERCENT;
		}
	}, 
	NORMAL
	{
		private static final double MIN_PERCENT = 0.50;
		private static final double MAX_PERCENT = 0.55;
		
		@Override
		protected boolean isPercentageEqualToThisDifficulty(double percent) {
			return percent >= MIN_PERCENT && 
				   percent <  MAX_PERCENT;
		}
	}, 
	INTERMEDIATE
	{
		private static final double MIN_PERCENT = 0.55;
		private static final double MAX_PERCENT = 0.60;
		
		@Override
		protected boolean isPercentageEqualToThisDifficulty(double percent) {
			return percent >= MIN_PERCENT && 
				   percent <  MAX_PERCENT;
		}
	}, 
	HARD
	{
		private static final double MIN_PERCENT = 0.60;
		private static final double MAX_PERCENT = 1.00;
		
		@Override
		protected boolean isPercentageEqualToThisDifficulty(double percent) {
			return percent >= MIN_PERCENT && 
				   percent <  MAX_PERCENT;
		}
	};
	

	
	protected abstract boolean isPercentageEqualToThisDifficulty(double percent);
	
	public static DifficultyLevel getDifficultylevelFromPercentDifficult(double percent)
	{
		if (HARD.isPercentageEqualToThisDifficulty(percent)) {
			return HARD;
		} else if (INTERMEDIATE.isPercentageEqualToThisDifficulty(percent)) {
			return INTERMEDIATE;
		} else if (NORMAL.isPercentageEqualToThisDifficulty(percent)) {
			return NORMAL;
		} else {
			return EASY;
		}
	}
}
