package muscaa.clichat.client.command;

import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.shared.command.console.AbstractConsoleCommandSource;

public class ClientCommandSource extends AbstractConsoleCommandSource {
	
	@Override
	public boolean isCommandMode() {
		return !CLIChatClient.INSTANCE.inChat;
	}
}
