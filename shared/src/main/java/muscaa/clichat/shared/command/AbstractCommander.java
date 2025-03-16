package muscaa.clichat.shared.command;

import fluff.commander.Commander;

public abstract class AbstractCommander<C extends AbstractCommander<C, S>, S extends ISharedCommandSource> extends Commander<C, S> {
	
	public AbstractCommander() {
		super(false, "cli");
	}
	
	@Override
	public void init() {
	}
	
	public abstract CommandResult execute(S source, String input);
	
	public String getChatCommand(String input) {
		return input.startsWith("/") ? input.substring(1) : null;
	}
}
