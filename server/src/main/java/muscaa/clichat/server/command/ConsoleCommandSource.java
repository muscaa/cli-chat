package muscaa.clichat.server.command;

public class ConsoleCommandSource implements ICommandSource {
	
	@Override
	public void addChatLine(String line) {
		System.out.println(line);
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
