package muscaa.clichat.client.network.chat;

import muscaa.clichat.client.network.common.ClientCommonNetHandler;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;
import muscaa.clichat.shared.utils.Utils;

public class ClientChatNetHandler extends ClientCommonNetHandler implements IClientChatNetHandler {
	
	@Override
	public void onPacketChatLine(PacketChatLine packet) {
		Utils.print(packet.getLine());
	}
}
