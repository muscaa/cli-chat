package muscaa.clichat.client.command;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import fluff.commander.CommanderException;
import fluff.commander.arg.IArgumentInput;
import fluff.commander.arg.StringArgumentInput;
import fluff.commander.command.ICommand;
import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.client.command.commands.CommandDisconnect;
import muscaa.clichat.shared.command.BasicCommander;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;

public class ClientCommander extends BasicCommander<ClientCommander, IClientCommandSource> {
	
	public CompletableFuture<Boolean> commandFuture;
	
	@Override
	public void init() {
		super.init();
		
		command(new CommandDisconnect());
	}
	
	public void execute(IClientCommandSource source, String input) {
		try {
			IArgumentInput in = new StringArgumentInput(input);
			ICommand command = commands.get(in.peek());
			if (command != null) {
				super.execute(source, in);
				return;
			}
		} catch (CommanderException e) {
			source.error(e.getMessage());
			return;
		}
		
		commandFuture = new CompletableFuture<>();
		
		CLIChatClient.INSTANCE.network.send(new PacketCommand(source.direct(), input));
		
		try {
			commandFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			source.error(e.getMessage());
		}
		commandFuture = null;
	}
}
