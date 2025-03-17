package muscaa.clichat.client.command.commands;

import fluff.commander.command.AbstractCommand;
import fluff.commander.command.CommandArguments;
import fluff.commander.command.CommandException;
import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.client.command.ClientCommandSource;
import muscaa.clichat.client.command.ClientCommander;

public class CommandDisconnect extends AbstractCommand<ClientCommander, ClientCommandSource> {
	
	public CommandDisconnect() {
		super("disconnect", "dc");
	}
	
	@Override
	public int onAction(ClientCommander c, ClientCommandSource source, CommandArguments args) throws CommandException {
		CLIChatClient.INSTANCE.stop();
		
		return SUCCESS;
	}
}
