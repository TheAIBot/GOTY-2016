package Game.Model.Board;

public enum GameState  implements java.io.Serializable {
	LOST("LOSER"), 
	WON("WINNER"),
	NOT_DECIDED_YET("");
		
	private final String text;
	
	private GameState(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}
}
