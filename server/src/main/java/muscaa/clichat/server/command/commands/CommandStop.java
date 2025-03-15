package muscaa.clichat.server.command.commands;

import fluff.commander.command.AbstractCommand;
import fluff.commander.command.CommandArguments;
import fluff.commander.command.CommandException;
import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.command.IServerCommandSource;
import muscaa.clichat.server.command.ServerCommander;

public class CommandStop extends AbstractCommand<ServerCommander, IServerCommandSource> {
	
	public CommandStop() {
		super("stop");
	}
	
	@Override
	public int onAction(ServerCommander c, IServerCommandSource source, CommandArguments args) throws CommandException {
		if (!source.isOp()) {
			source.error("No permission!");
			return FAIL;
		}
		
		CLIChatServer.INSTANCE.stop();
		
		return SUCCESS;
	}
}
