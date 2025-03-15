package muscaa.clichat.server.network.chat;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.network.common.ServerCommonNetHandler;
import muscaa.clichat.server.utils.ChatUtils;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;
import muscaa.clichat.shared.network.chat.packets.PacketMessage;

public class ServerChatNetHandler extends ServerCommonNetHandler implements IServerChatNetHandler {
	
	@Override
	public void onPacketMessage(PacketMessage packet) {
		ChatUtils.message(connection.getName(), packet.getMessage());
	}
	
	@Override
	public void onPacketCommand(PacketCommand packet) {
		connection.setDirect(packet.isDirect());
		
		CLIChatServer.INSTANCE.commander.execute(connection, packet.getCommand());
	}
}
