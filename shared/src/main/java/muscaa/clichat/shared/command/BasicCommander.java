package muscaa.clichat.shared.command;

import fluff.commander.Commander;

public abstract class BasicCommander<C extends BasicCommander<C, S>, S extends IBasicCommandSource> extends Commander<C, S> {
	
	public BasicCommander() {
		super(false, "cli");
	}
	
	@Override
	public void init() {
	}
	
	public String chatCommand(String input) {
		return input.startsWith("/") ? input.substring(1) : null;
	}
}
