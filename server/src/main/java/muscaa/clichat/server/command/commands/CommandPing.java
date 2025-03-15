package muscaa.clichat.server.command.commands;

import fluff.commander.command.AbstractCommand;
import fluff.commander.command.CommandArguments;
import fluff.commander.command.CommandException;
import muscaa.clichat.server.command.IServerCommandSource;
import muscaa.clichat.server.command.ServerCommander;

public class CommandPing extends AbstractCommand<ServerCommander, IServerCommandSource> {
	
	public CommandPing() {
		super("ping");
	}
	
	@Override
	public int onAction(ServerCommander c, IServerCommandSource source, CommandArguments args) throws CommandException {
		source.info("Pong!");
		
		return SUCCESS;
	}
}
