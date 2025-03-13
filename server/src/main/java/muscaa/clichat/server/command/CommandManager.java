package muscaa.clichat.server.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import fluff.functions.gen.obj.Func1;
import muscaa.clichat.server.command.commands.DeopCommand;
import muscaa.clichat.server.command.commands.KickCommand;
import muscaa.clichat.server.command.commands.OpCommand;
import muscaa.clichat.server.command.commands.PingCommand;
import muscaa.clichat.server.command.commands.StopCommand;
import muscaa.clichat.server.command.commands.WhisperCommand;
import muscaa.clichat.shared.utils.Utils;

public class CommandManager {
	
	private final CommandDispatcher<ICommandSource> dispatcher = new CommandDispatcher<>();
	
	public CommandManager() {
		register(PingCommand::create, "ping");
		register(StopCommand::create, "stop");
		register(OpCommand::create, "op");
		register(DeopCommand::create, "deop");
		register(KickCommand::create, "kick");
		register(WhisperCommand::create, "whisper", "w");
	}
	
	public void register(Func1<LiteralArgumentBuilder<ICommandSource>, LiteralArgumentBuilder<ICommandSource>> func, String... aliases) {
		for (String alias : aliases) {
			dispatcher.register(func.invoke(LiteralArgumentBuilder.<ICommandSource>literal(alias)));
		}
	}
	
	public boolean execute(ICommandSource source, String command) {
		try {
			int result = dispatcher.execute(command, source);
			if (result == Command.SINGLE_SUCCESS) return true;
			
			source.addChatLine(Utils.error("Invalid command."));
		} catch (CommandSyntaxException e) {
			source.addChatLine(Utils.error(e.getMessage()));
		}
		return false;
	}
	
	public String parseCommand(String input) {
		return input.startsWith("/") ? input.substring(1) : null;
	}
}
