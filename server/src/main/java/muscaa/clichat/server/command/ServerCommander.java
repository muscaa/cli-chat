package muscaa.clichat.server.command;

import muscaa.clichat.server.command.commands.CommandDeop;
import muscaa.clichat.server.command.commands.CommandKick;
import muscaa.clichat.server.command.commands.CommandOp;
import muscaa.clichat.server.command.commands.CommandPing;
import muscaa.clichat.server.command.commands.CommandStop;
import muscaa.clichat.server.command.commands.CommandWhisper;
import muscaa.clichat.shared.command.AbstractCommander;

public class ServerCommander extends AbstractCommander<ServerCommander, IServerCommandSource> {
	
	@Override
	public void init() {
		super.init();
		
		command(new CommandStop());
		command(new CommandPing());
		command(new CommandOp());
		command(new CommandDeop());
		command(new CommandKick());
		command(new CommandWhisper());
	}
}
