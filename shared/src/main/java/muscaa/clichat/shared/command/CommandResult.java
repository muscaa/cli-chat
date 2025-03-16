package muscaa.clichat.shared.command;

public class CommandResult {
	
	public final int exitCode;
	public final String error;
	
	public CommandResult(int exitCode, String error) {
		this.exitCode = exitCode;
		this.error = error;
	}
}
