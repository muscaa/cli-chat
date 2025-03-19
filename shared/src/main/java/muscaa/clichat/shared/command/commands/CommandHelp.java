package muscaa.clichat.shared.command.commands;

import fluff.commander.command.AbstractCommand;
import fluff.commander.command.CommandArguments;
import fluff.commander.command.CommandException;
import muscaa.clichat.shared.command.AbstractCommander;
import muscaa.clichat.shared.command.ISharedCommandSource;

public class CommandHelp<C extends AbstractCommander<C, S>, S extends ISharedCommandSource> extends AbstractCommand<C, S> {
	
	public CommandHelp() {
		super("help");
	}
	
	@Override
	public int onAction(C c, S source, CommandArguments args) throws CommandException {
		return c.help(c, source);
	}
}
