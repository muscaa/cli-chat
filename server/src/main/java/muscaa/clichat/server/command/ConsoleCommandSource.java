package muscaa.clichat.server.command;

import org.jline.jansi.Ansi;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.shared.command.AbstractConsoleCommandSource;
import muscaa.clichat.shared.utils.Utils;

public class ConsoleCommandSource extends AbstractConsoleCommandSource implements IServerCommandSource {
	
	@Override
	public boolean isCommandMode() {
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
