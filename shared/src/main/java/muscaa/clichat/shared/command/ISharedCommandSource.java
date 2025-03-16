package muscaa.clichat.shared.command;

import fluff.commander.command.ICommandSource;

public interface ISharedCommandSource extends ICommandSource {
	
	void log(Object o);
	
	void info(Object o);
	
	void warn(Object o);
	
	void error(Object o);
	
	boolean isCommandMode();
}
