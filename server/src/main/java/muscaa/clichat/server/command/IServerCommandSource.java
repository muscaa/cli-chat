package muscaa.clichat.server.command;

import muscaa.clichat.shared.command.IBasicCommandSource;

public interface IServerCommandSource extends IBasicCommandSource {
	
	void addChatLine(String line);
	
	String getName();
	
	boolean isOp();
}
