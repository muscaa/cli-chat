package muscaa.clichat.server.command.console;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.shared.command.CommandResult;
import muscaa.clichat.shared.command.console.AbstractConsoleCommander;

public class ConsoleCommander extends AbstractConsoleCommander<ConsoleCommander, ConsoleCommandSource> {
	
	public ConsoleCommander() {
		super(new ConsoleCommandSource());
	}
	
	public CommandResult executeServer(String input) {
		CommandResult result = CLIChatServer.INSTANCE.commander.execute(console, input);
		lastExitCode = result.exitCode;
		lastError = result.error;
		return result;
	}
	
	public String getChatCommandServer(String input) {
		return input.startsWith("/") && !input.startsWith(chatCommandPrefix) ? input.substring(1) : null;
	}
}
