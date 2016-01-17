package Game.Model.Difficulty;

public enum DifficultyLevel {
	EASY(0)
	{
		//These values are used to check wether a specific difficulty percentage
		//is equal to this difficultyLevel
		private static final double MIN_PERCENT = 0.00;
		private static final double MAX_PERCENT = 0.50;
		
		@Override
		protected boolean isPercentageEqualToThisDifficulty(double percent) {
			return percent >= MIN_PERCENT && 
				   percent <  MAX_PERCENT;
		}
	}, 
	NORMAL(1)
	{
		//These values are used to check wether a specific difficulty percentage
		//is equal to this difficultyLevel
		private static final double MIN_PERCENT = 0.50;
		private static final double MAX_PERCENT = 0.55;
		
		@Override
		protected boolean isPercentageEqualToThisDifficulty(double percent) {
			return percent >= MIN_PERCENT && 
				   percent <  MAX_PERCENT;
		}
	}, 
	INTERMEDIATE(2)
	{
		//These values are used to check wether a specific difficulty percentage
		//is equal to this difficultyLevel
		private static final double MIN_PERCENT = 0.55;
		private static final double MAX_PERCENT = 0.60;
		
		@Override
		protected boolean isPercentageEqualToThisDifficulty(double percent) {
			return percent >= MIN_PERCENT && 
				   percent <  MAX_PERCENT;
		}
	}, 
	HARD(3)
	{
		//These values are used to check wether a specific difficulty percentage
		//is equal to this difficultyLevel
		private static final double MIN_PERCENT = 0.60;
		private static final double MAX_PERCENT = 1.00;
		
		@Override
		protected boolean isPercentageEqualToThisDifficulty(double percent) {
			return percent >= MIN_PERCENT && 
				   percent <=  MAX_PERCENT;
		}
	};
	
	//This value indicates the difficulty as a number where 0 is easy and
	//3 is hard. This feature is often usefull when showing an enum in a GUI
	private final int value;

	private DifficultyLevel(int value)
	{
		this.value = value;
	}
	
	/**	Calculates if a given difficulty percentage answers to a given difficulty level
	 * @param percent The percentage of difficulty
	 * @return True/false
	 */
	protected abstract boolean isPercentageEqualToThisDifficulty(double percent);
	
	/** Returns the difficulty level 
	 * @param percent The difficulty percentage. It must be in the spectre 0 <= percent <= 100.
	 * @return
	 */
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
	
	public int getValue()
	{
		return value;
	}
}