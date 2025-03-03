package muscaa.clichat.server.network.chat;

import muscaa.clichat.server.network.common.IServerCommonNetHandler;
import muscaa.clichat.shared.network.chat.IChatNetHandler;
import muscaa.clichat.shared.network.chat.packets.PacketMessage;

public interface IServerChatNetHandler extends IServerCommonNetHandler, IChatNetHandler {
	
	void onPacketMessage(PacketMessage packet);
}
