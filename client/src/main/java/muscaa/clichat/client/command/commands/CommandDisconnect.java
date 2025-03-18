package muscaa.clichat.client.command.commands;

import fluff.commander.command.AbstractCommand;
import fluff.commander.command.CommandArguments;
import fluff.commander.command.CommandException;
import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.client.command.ConsoleCommandSource;
import muscaa.clichat.client.command.ConsoleCommander;

public class CommandDisconnect extends AbstractCommand<ConsoleCommander, ConsoleCommandSource> {
	
	public CommandDisconnect() {
		super("disconnect", "dc");
	}
	
	@Override
	public int onAction(ConsoleCommander c, ConsoleCommandSource source, CommandArguments args) throws CommandException {
		CLIChatClient.INSTANCE.stop();
		
		return SUCCESS;
	}
}
