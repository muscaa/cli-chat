package muscaa.clichat.client.command;

import fluff.commander.CommanderException;
import fluff.commander.arg.IArgumentInput;
import fluff.commander.arg.StringArgumentInput;
import fluff.commander.command.ICommand;
import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.client.command.commands.CommandDisconnect;
import muscaa.clichat.shared.command.BasicCommander;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;

public class ClientCommander extends BasicCommander<ClientCommander, IClientCommandSource> {
	
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
		
		CLIChatClient.INSTANCE.network.send(new PacketCommand(source.direct(), input));
	}
}
