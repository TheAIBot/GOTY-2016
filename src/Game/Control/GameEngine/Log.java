package Game.Control.GameEngine;

import java.io.PrintStream;

public class Log {
	public static void writeln(String logLine)
	{
		System.out.println(logLine);
	}
	
	public static void writeError(Exception e)
	{
		if (e == null) {
			Log.writeln("Log could not write error as it was null");
		}
		writeln(e.getMessage());
		e.printStackTrace(getLogStream());
	}
	
	private static PrintStream getLogStream()
	{
		return System.out;
	}
}