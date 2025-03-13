package muscaa.clichat.server.command.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import muscaa.clichat.server.command.ICommandSource;
import muscaa.clichat.shared.utils.Utils;

public class PingCommand {
	
	public static LiteralArgumentBuilder<ICommandSource> create(LiteralArgumentBuilder<ICommandSource> builder) {
		return builder
				.executes(context -> {
					context.getSource().addChatLine(Utils.info("Pong!"));
					
					return Command.SINGLE_SUCCESS;
				});
	}
}
