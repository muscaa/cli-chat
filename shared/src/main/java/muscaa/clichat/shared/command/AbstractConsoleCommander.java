package muscaa.clichat.shared.command;

public abstract class AbstractConsoleCommander<C extends AbstractConsoleCommander<C, S>, S extends AbstractConsoleCommandSource> extends AbstractCommander<C, S> {
	
	protected final S console;
	
	protected int lastExitCode;
	protected String lastError;
	
	public AbstractConsoleCommander(S console) {
		this.console = console;
	}
	
	protected CommandResult result(CommandResult result) {
		lastExitCode = result.exitCode;
		lastError = result.error;
		return result;
	}
	
	public CommandResult executeClient(String input) {
		return result(execute(console, input));
	}
	
	public abstract CommandResult executeServer(String input);
	
	public String getChatCommandClient(String input) {
		return input.startsWith("//") ? input.substring(2) : null;
	}
	
	public String getChatCommandServer(String input) {
		return input.startsWith("/") && !input.startsWith("//") ? input.substring(1) : null;
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
