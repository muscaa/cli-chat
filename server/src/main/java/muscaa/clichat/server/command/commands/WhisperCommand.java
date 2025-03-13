package muscaa.clichat.server.command.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import muscaa.clichat.server.command.ICommandSource;
import muscaa.clichat.server.command.arguments.UserArgumentType;
import muscaa.clichat.server.network.NetworkClientConnection;
import muscaa.clichat.server.utils.ChatUtils;

public class WhisperCommand {
	
	public static LiteralArgumentBuilder<ICommandSource> create(LiteralArgumentBuilder<ICommandSource> builder) {
		return builder
				.then(RequiredArgumentBuilder.<ICommandSource, NetworkClientConnection>argument("user", UserArgumentType.user())
						.then(RequiredArgumentBuilder.<ICommandSource, String>argument("message", StringArgumentType.greedyString())
								.executes(context -> {
									NetworkClientConnection user = UserArgumentType.getUser(context, "user");
									String message = StringArgumentType.getString(context, "message");
									
									context.getSource().addChatLine(ChatUtils.getMessage("To " + user.getName(), message));
									user.addChatLine(ChatUtils.getMessage("From " + context.getSource().getName(), message));
									
									return Command.SINGLE_SUCCESS;
								})
						)
				);
	}
}
