package muscaa.clichat.server.command.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.command.ICommandSource;

public class StopCommand {
	
	public static LiteralArgumentBuilder<ICommandSource> create(LiteralArgumentBuilder<ICommandSource> builder) {
		return builder
				.requires(ICommandSource::isOp)
				.executes(context -> {
					CLIChatServer.INSTANCE.stop();
					
					return Command.SINGLE_SUCCESS;
				});
	}
}
