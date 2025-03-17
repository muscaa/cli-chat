package muscaa.clichat.client.command;

import java.util.concurrent.CompletableFuture;

import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.client.command.commands.CommandDisconnect;
import muscaa.clichat.shared.command.CommandResult;
import muscaa.clichat.shared.command.console.AbstractConsoleCommander;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;

public class ClientCommander extends AbstractConsoleCommander<ClientCommander, ClientCommandSource> {
	
	protected CompletableFuture<CommandResult> commandFuture;
	
	public ClientCommander() {
		super(new ClientCommandSource());
	}
	
	@Override
	public void init() {
		super.init();
		
		command(new CommandDisconnect());
	}
	
	public CommandResult executeServer(String input) {
		commandFuture = new CompletableFuture<>();
		CLIChatClient.INSTANCE.network.send(new PacketCommand(console.isCommandMode(), input));
		
		try {
			CommandResult result = commandFuture.get();
			commandFuture = null;
			lastExitCode = result.exitCode;
			lastError = result.error;
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void completeServer(int exitCode) {
		commandFuture.complete(new CommandResult(exitCode, null));
	}
	
	public void completeServer(String error) {
		commandFuture.complete(new CommandResult(FAIL, error));
	}
	
	public String getChatCommandServer(String input) {
		return input.startsWith("/") && !input.startsWith(chatCommandPrefix) ? input.substring(1) : null;
	}
}
