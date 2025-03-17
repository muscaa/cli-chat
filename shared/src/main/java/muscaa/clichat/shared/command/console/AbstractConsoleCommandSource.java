package muscaa.clichat.shared.command.console;

import muscaa.clichat.shared.command.ISharedCommandSource;
import muscaa.clichat.shared.utils.Utils;

public abstract class AbstractConsoleCommandSource implements ISharedCommandSource {
	
	@Override
	public void log(Object o) {
		Utils.print(o);
	}
	
	@Override
	public void info(Object o) {
		log(Utils.info(o));
	}
	
	@Override
	public void warn(Object o) {
		log(Utils.warn(o));
	}
	
	@Override
	public void error(Object o) {
		log(Utils.error(o));
	}
}
