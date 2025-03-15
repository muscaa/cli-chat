package muscaa.clichat.client.network.chat;

import muscaa.clichat.client.network.common.IClientCommonNetHandler;
import muscaa.clichat.shared.network.chat.IChatNetHandler;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;
import muscaa.clichat.shared.network.chat.packets.PacketCommandError;
import muscaa.clichat.shared.network.chat.packets.PacketCommandOutput;
import muscaa.clichat.shared.network.chat.packets.PacketCommandResult;

public interface IClientChatNetHandler extends IClientCommonNetHandler, IChatNetHandler {
	
	void onPacketChatLine(PacketChatLine packet);
	
	void onPacketCommandOutput(PacketCommandOutput packet);
	
	void onPacketCommandResult(PacketCommandResult packet);
	
	void onPacketCommandError(PacketCommandError packet);
}
