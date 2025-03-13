package muscaa.clichat.server.command.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.network.NetworkClientConnection;

public class UserArgumentType implements ArgumentType<NetworkClientConnection> {
	
	public static UserArgumentType user() {
        return new UserArgumentType();
	}
	
	public static NetworkClientConnection getUser(CommandContext<?> context, String name) {
		return context.getArgument(name, NetworkClientConnection.class);
	}
	
	@Override
	public NetworkClientConnection parse(StringReader reader) throws CommandSyntaxException {
		String name = reader.readUnquotedString();
		if (name.isBlank()) throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().createWithContext(reader);
		
		NetworkClientConnection user = CLIChatServer.INSTANCE.network.getConnection(name);
		if (user == null) throw new CommandSyntaxException(null, () -> "Unknown user: " + name);
		
		return user;
	}
}
