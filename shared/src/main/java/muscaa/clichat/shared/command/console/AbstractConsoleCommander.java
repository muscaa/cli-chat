package muscaa.clichat.shared.command.console;

import muscaa.clichat.shared.command.AbstractCommander;
import muscaa.clichat.shared.command.CommandResult;

public abstract class AbstractConsoleCommander<C extends AbstractConsoleCommander<C, S>, S extends AbstractConsoleCommandSource> extends AbstractCommander<C, S> {
	
	protected final S console;
	
	protected int lastExitCode;
	protected String lastError;
	
	public AbstractConsoleCommander(S console) {
		super("//");
		
		this.console = console;
	}
	
	@Override
	public CommandResult execute(S source, String input) {
		CommandResult result = super.execute(source, input);
		lastExitCode = result.exitCode;
		lastError = result.error;
		return result;
	}
	
	public CommandResult execute(String input) {
		return execute(console, input);
	}
	
	public S getConsole() {
		return console;
	}
	
	public int getLastExitCode() {
		return lastExitCode;
	}
	
	public String getLastError() {
		return lastError;
	}
}
