package muscaa.clichat.server.network.chat;

import muscaa.clichat.server.network.common.IServerCommonNetHandler;
import muscaa.clichat.shared.network.chat.IChatNetHandler;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;
import muscaa.clichat.shared.network.chat.packets.PacketChatMessage;

public interface IServerChatNetHandler extends IServerCommonNetHandler, IChatNetHandler {
	
	void onPacketChatMessage(PacketChatMessage packet);
	
	void onPacketCommand(PacketCommand packet);
}
