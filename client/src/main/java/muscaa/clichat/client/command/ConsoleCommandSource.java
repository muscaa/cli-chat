package muscaa.clichat.client.command;

import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.shared.command.AbstractConsoleCommandSource;

public class ConsoleCommandSource extends AbstractConsoleCommandSource {
	
	@Override
	public boolean isCommandMode() {
		return !CLIChatClient.INSTANCE.inChat;
	}
}
