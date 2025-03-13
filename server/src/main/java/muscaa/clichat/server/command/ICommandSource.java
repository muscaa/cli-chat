package muscaa.clichat.server.command;

public interface ICommandSource {
	
	void addChatLine(String line);
	
	String getName();
	
	boolean isOp();
}
