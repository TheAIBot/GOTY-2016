package Log;
import java.io.PrintStream;

public class Log {
	public static void writeln(String logLine)
	{
		System.out.println(logLine);
	}
	
	public static void writeError(Exception e)
	{
		if (e == null) {
			throw new IllegalArgumentException("error is null");
		}
		writeln(e.getMessage());
		e.printStackTrace(getLogStream());
	}
	
	private static PrintStream getLogStream()
	{
		return System.out;
	}
}