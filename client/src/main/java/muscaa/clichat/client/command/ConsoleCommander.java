package muscaa.clichat.client.command;

import java.util.concurrent.CompletableFuture;

import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.client.command.commands.CommandDisconnect;
import muscaa.clichat.shared.command.AbstractConsoleCommander;
import muscaa.clichat.shared.command.CommandResult;
import muscaa.clichat.shared.network.cli.packets.PacketCommand;

public class ConsoleCommander extends AbstractConsoleCommander<ConsoleCommander, ConsoleCommandSource> {
	
	protected CompletableFuture<CommandResult> commandFuture;
	
	public ConsoleCommander() {
		super(new ConsoleCommandSource());
	}
	
	@Override
	public void init() {
		super.init();
		
		command(new CommandDisconnect());
	}
	
	@Override
	public CommandResult executeServer(String input) {
		commandFuture = new CompletableFuture<>();
		CLIChatClient.INSTANCE.network.send(new PacketCommand(console.isCommandMode(), input));
		
		try {
			CommandResult result = commandFuture.get();
			commandFuture = null;
			return result(result);
		} catch (Exception e) {
			commandFuture = null;
			throw new RuntimeException(e);
		}
	}
	
	public void completeServer(int exitCode) {
		commandFuture.complete(new CommandResult(exitCode, null));
	}
	
	public void completeServer(String error) {
		commandFuture.complete(new CommandResult(FAIL, error));
	}
}
