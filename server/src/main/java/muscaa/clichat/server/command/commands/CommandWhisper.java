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

public class CommandWhisper extends AbstractCommand<ServerCommander, IServerCommandSource> {
	
	private static final IArgument<String> ARG_USER = ArgumentBuilder
			.String("--user")
			.required()
			.inline()
			.build();
	
	private static final IArgument<String> ARG_MESSAGE = ArgumentBuilder
			.String("--message")
			.required()
			.inline()
			.build();
	
	public CommandWhisper() {
		super("whisper", "w");
	}
	
	@Override
	public void init() {
		argument(ARG_USER);
		argument(ARG_MESSAGE);
	}
	
	@Override
	public int onAction(ServerCommander c, IServerCommandSource source, CommandArguments args) throws CommandException {
		String user = args.get(ARG_USER);
		if (user.isBlank()) throw new CommandException("User name cannot be blank!");
		
		String message = args.get(ARG_MESSAGE);
		if (message.isBlank()) throw new CommandException("Message cannot be blank!");
		
		NetworkClientConnection connection = CLIChatServer.INSTANCE.network.getConnection(user);
		if (connection == null) throw new CommandException("User " + user + " not found!");
		
		source.addChatLine(ChatUtils.getMessage("To " + connection.getName(), message));
		connection.addChatLine(ChatUtils.getMessage("From " + source.getName(), message));
		
		return SUCCESS;
	}
}
