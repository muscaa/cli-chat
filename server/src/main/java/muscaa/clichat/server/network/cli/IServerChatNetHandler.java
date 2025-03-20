package muscaa.clichat.server.network.cli;

import muscaa.clichat.server.network.common.IServerCommonNetHandler;
import muscaa.clichat.shared.network.cli.IChatNetHandler;
import muscaa.clichat.shared.network.cli.packets.PacketChatMessage;
import muscaa.clichat.shared.network.cli.packets.PacketCommand;

public interface IServerChatNetHandler extends IServerCommonNetHandler, IChatNetHandler {
	
	void onPacketChatMessage(PacketChatMessage packet);
	
	void onPacketCommand(PacketCommand packet);
}
