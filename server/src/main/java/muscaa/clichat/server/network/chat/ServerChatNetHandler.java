package muscaa.clichat.server.network.chat;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.network.common.ServerCommonNetHandler;
import muscaa.clichat.server.utils.ChatUtils;
import muscaa.clichat.shared.network.chat.packets.PacketMessage;

public class ServerChatNetHandler extends ServerCommonNetHandler implements IServerChatNetHandler {
	
	@Override
	public void onPacketMessage(PacketMessage packet) {
		String command = CLIChatServer.INSTANCE.commands.parseCommand(packet.getMessage());
		if (command != null) {
			CLIChatServer.INSTANCE.commands.execute(connection, command);
			return;
		}
		
		ChatUtils.message(connection.getName(), packet.getMessage());
	}
}
