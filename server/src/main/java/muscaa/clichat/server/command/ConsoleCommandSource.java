package muscaa.clichat.server.command;

import muscaa.clichat.shared.utils.Utils;

public class ConsoleCommandSource implements ICommandSource {
	
	@Override
	public void addChatLine(String line) {
		Utils.print(line);
	}
	
	@Override
	public String getName() {
		return "CONSOLE";
	}
	
	@Override
	public boolean isOp() {
		return true;
	}
}
