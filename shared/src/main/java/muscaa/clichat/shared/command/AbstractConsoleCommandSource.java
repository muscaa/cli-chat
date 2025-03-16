package muscaa.clichat.shared.command;

import muscaa.clichat.shared.utils.Utils;

public abstract class AbstractConsoleCommandSource implements ISharedCommandSource {
	
	@Override
	public void log(Object o) {
		Utils.print(o);
	}
	
	@Override
	public void info(Object o) {
		Utils.print(Utils.info(o));
	}
	
	@Override
	public void warn(Object o) {
		Utils.print(Utils.warn(o));
	}
	
	@Override
	public void error(Object o) {
		Utils.print(Utils.error(o));
	}
}
