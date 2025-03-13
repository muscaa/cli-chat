package muscaa.clichat.server.command.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import muscaa.clichat.server.command.ICommandSource;
import muscaa.clichat.server.command.arguments.UserArgumentType;
import muscaa.clichat.server.network.NetworkClientConnection;
import muscaa.clichat.server.utils.ChatUtils;

public class DeopCommand {
	
	public static LiteralArgumentBuilder<ICommandSource> create(LiteralArgumentBuilder<ICommandSource> builder) {
		return builder
				.requires(ICommandSource::isOp)
				.then(RequiredArgumentBuilder.<ICommandSource, NetworkClientConnection>argument("user", UserArgumentType.user())
						.executes(context -> {
							NetworkClientConnection user = UserArgumentType.getUser(context, "user");
							
							user.setOp(false);
							ChatUtils.broadcast(context.getSource().getName() + " deopped " + user.getName(), NetworkClientConnection::isOp);
							
							return Command.SINGLE_SUCCESS;
						})
				);
	}
}
