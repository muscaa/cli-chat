package muscaa.clichat.client.command;

import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.shared.command.BasicConsoleCommandSource;

public class ClientConsoleCommandSource extends BasicConsoleCommandSource implements IClientCommandSource {
	
	@Override
	public boolean direct() {
		return !CLIChatClient.INSTANCE.inChat;
	}
}
