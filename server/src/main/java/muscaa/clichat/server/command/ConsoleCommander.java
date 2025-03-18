package muscaa.clichat.server.command;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.shared.command.AbstractConsoleCommander;
import muscaa.clichat.shared.command.CommandResult;

public class ConsoleCommander extends AbstractConsoleCommander<ConsoleCommander, ConsoleCommandSource> {
	
	public ConsoleCommander() {
		super(new ConsoleCommandSource());
	}
	
	@Override
	public CommandResult executeServer(String input) {
		return result(CLIChatServer.INSTANCE.commander.execute(console, input));
	}
}
