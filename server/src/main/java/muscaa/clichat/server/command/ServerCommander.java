package muscaa.clichat.server.command;

import fluff.commander.argument.IArgumentInput;
import fluff.commander.argument.StringArgumentInput;
import muscaa.clichat.server.command.commands.CommandDeop;
import muscaa.clichat.server.command.commands.CommandKick;
import muscaa.clichat.server.command.commands.CommandOp;
import muscaa.clichat.server.command.commands.CommandPing;
import muscaa.clichat.server.command.commands.CommandStop;
import muscaa.clichat.server.command.commands.CommandWhisper;
import muscaa.clichat.shared.command.AbstractCommander;
import muscaa.clichat.shared.command.CommandResult;

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
	
	@Override
	public CommandResult execute(IServerCommandSource source, String input) {
		int exitCode;
		String error;
		
		try {
			IArgumentInput in = new StringArgumentInput(input);
			
			exitCode = execute(source, in);
			error = null;
		} catch (Exception e) {
			exitCode = FAIL;
			error = e.getMessage();
			
			source.error(error);
		}
		
		return new CommandResult(exitCode, error);
	}
}
