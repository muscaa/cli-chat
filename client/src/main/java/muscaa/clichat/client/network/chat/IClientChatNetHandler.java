package muscaa.clichat.client.network.chat;

import muscaa.clichat.client.network.common.IClientCommonNetHandler;
import muscaa.clichat.shared.network.chat.IChatNetHandler;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;
import muscaa.clichat.shared.network.chat.packets.PacketCommandResponse;

public interface IClientChatNetHandler extends IClientCommonNetHandler, IChatNetHandler {
	
	void onPacketChatLine(PacketChatLine packet);
	
	void onPacketCommandResponse(PacketCommandResponse packet);
}
