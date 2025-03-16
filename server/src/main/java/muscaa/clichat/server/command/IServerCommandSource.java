package muscaa.clichat.server.command;

import muscaa.clichat.shared.command.ISharedCommandSource;

public interface IServerCommandSource extends ISharedCommandSource {
	
	void addChatLine(String line);
	
	String getName();
	
	boolean isOp();
}
