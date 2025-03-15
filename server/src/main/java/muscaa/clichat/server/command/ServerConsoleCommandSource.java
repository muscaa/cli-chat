package muscaa.clichat.server.command;

import org.jline.jansi.Ansi;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.shared.command.BasicConsoleCommandSource;
import muscaa.clichat.shared.utils.Utils;

public class ServerConsoleCommandSource extends BasicConsoleCommandSource implements IServerCommandSource {
	
	@Override
	public boolean direct() {
		return !CLIChatServer.INSTANCE.inChat;
	}
	
	@Override
	public void addChatLine(String line) {
		Utils.print(Ansi.ansi().fgBrightBlack().a("[CHAT] ") + line);
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
