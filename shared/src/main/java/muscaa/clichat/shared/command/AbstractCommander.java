package muscaa.clichat.shared.command;

import fluff.commander.Commander;
import fluff.commander.argument.IArgumentInput;
import fluff.commander.argument.StringArgumentInput;

public abstract class AbstractCommander<C extends AbstractCommander<C, S>, S extends ISharedCommandSource> extends Commander<C, S> {
	
	protected final String chatCommandPrefix;
	
	public AbstractCommander(String chatCommandPrefix) {
		super(false, "cli");
		
		this.chatCommandPrefix = chatCommandPrefix;
	}
	
	public AbstractCommander() {
		this("/");
	}
	
	@Override
	public void init() {
		
	}
	
	public CommandResult execute(S source, String input) {
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
	
	public String getChatCommand(String input) {
		return input.startsWith(chatCommandPrefix) ? input.substring(chatCommandPrefix.length()) : null;
	}
}
