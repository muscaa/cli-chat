package muscaa.clichat.client.network.chat;

import muscaa.clichat.client.network.common.IClientCommonNetHandler;
import muscaa.clichat.shared.network.chat.IChatNetHandler;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;

public interface IClientChatNetHandler extends IClientCommonNetHandler, IChatNetHandler {
	
	void onPacketChatLine(PacketChatLine packet);
}
