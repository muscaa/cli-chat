package muscaa.clichat.server.network.chat;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.network.common.ServerCommonNetHandler;
import muscaa.clichat.shared.network.chat.packets.PacketMessage;

public class ServerChatNetHandler extends ServerCommonNetHandler implements IServerChatNetHandler {
	
	@Override
	public void onPacketMessage(PacketMessage packet) {
		CLIChatServer.INSTANCE.chat(connection.getName(), packet.getMessage());
	}
}
