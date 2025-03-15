package muscaa.clichat.server.command;

import fluff.commander.CommanderException;
import fluff.commander.arg.IArgumentInput;
import fluff.commander.arg.StringArgumentInput;
import muscaa.clichat.server.command.commands.CommandDeop;
import muscaa.clichat.server.command.commands.CommandKick;
import muscaa.clichat.server.command.commands.CommandOp;
import muscaa.clichat.server.command.commands.CommandPing;
import muscaa.clichat.server.command.commands.CommandStop;
import muscaa.clichat.server.command.commands.CommandWhisper;
import muscaa.clichat.shared.command.BasicCommander;

public class ServerCommander extends BasicCommander<ServerCommander, IServerCommandSource> {
	
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
	
	public void execute(IServerCommandSource source, String input) {
		try {
			IArgumentInput in = new StringArgumentInput(input);
			
			super.execute(source, in);
		} catch (CommanderException e) {
			source.error(e.getMessage());
		}
	}
}
