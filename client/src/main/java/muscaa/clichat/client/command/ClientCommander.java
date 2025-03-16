package muscaa.clichat.client.command;

import java.util.concurrent.CompletableFuture;

import fluff.commander.CommanderException;
import fluff.commander.argument.IArgumentInput;
import fluff.commander.argument.StringArgumentInput;
import fluff.commander.command.ICommand;
import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.client.command.commands.CommandDisconnect;
import muscaa.clichat.shared.command.AbstractCommander;
import muscaa.clichat.shared.command.CommandResult;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;

public class ClientCommander extends AbstractCommander<ClientCommander, IClientCommandSource> {
	
	private CompletableFuture<Integer> commandFuture;
	private int lastExitCode;
	private String lastError;
	
	@Override
	public void init() {
		super.init();
		
		command(new CommandDisconnect());
	}
	
	@Override
	public CommandResult execute(IClientCommandSource source, String input) {
		try {
			IArgumentInput in = new StringArgumentInput(input);
			ICommand command = commands.get(in.peek());
			
			if (command != null) {
				lastExitCode = execute(source, in);
			} else {
				commandFuture = new CompletableFuture<>();
				CLIChatClient.INSTANCE.network.send(new PacketCommand(source.isCommandMode(), input));
				lastExitCode = commandFuture.get();
			}
			
			lastError = null;
		} catch (Exception e) {
			lastExitCode = FAIL;
			lastError = e.getMessage();
			
			source.error(lastError);
		}
		
		commandFuture = null;
		
		return new CommandResult(lastExitCode, lastError);
	}
	
	public void complete(int exitCode) {
		commandFuture.complete(exitCode);
	}
	
	public void complete(String error) {
		commandFuture.completeExceptionally(new CommanderException(error));
	}
	
	public int getLastExitCode() {
		return lastExitCode;
	}
	
	public String getLastError() {
		return lastError;
	}
}
