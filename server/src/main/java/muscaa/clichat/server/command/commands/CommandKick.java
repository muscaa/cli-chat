package muscaa.clichat.server.command.commands;

import fluff.commander.argument.ArgumentBuilder;
import fluff.commander.argument.IArgument;
import fluff.commander.command.AbstractCommand;
import fluff.commander.command.CommandArguments;
import fluff.commander.command.CommandException;
import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.command.IServerCommandSource;
import muscaa.clichat.server.command.ServerCommander;
import muscaa.clichat.server.network.NetworkClientConnection;
import muscaa.clichat.server.utils.ChatUtils;
import muscaa.clichat.shared.utils.Utils;

public class CommandKick extends AbstractCommand<ServerCommander, IServerCommandSource> {
	
	private static final IArgument<String> ARG_USER = ArgumentBuilder
			.String("--user")
			.required()
			.inline()
			.build()
			;
	
	public CommandKick() {
		super("kick");
	}
	
	@Override
	public void init() {
		argument(ARG_USER);
	}
	
	@Override
	public int onAction(ServerCommander c, IServerCommandSource source, CommandArguments args) throws CommandException {
		if (!source.isOp()) {
			source.error("No permission!");
			return FAIL;
		}
		
		String user = args.get(ARG_USER);
		if (user.isBlank()) throw new CommandException("User name cannot be blank!");
		
		NetworkClientConnection connection = CLIChatServer.INSTANCE.network.getConnection(user);
		if (connection == null) throw new CommandException("User " + user + " not found!");
		
		connection.disconnect("Kicked from server!");
		ChatUtils.broadcast(Utils.warn(source.getName() + " kicked " + connection.getName()), NetworkClientConnection::isOp);
		
		return SUCCESS;
	}
}
