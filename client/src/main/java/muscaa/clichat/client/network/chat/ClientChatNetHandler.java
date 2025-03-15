package muscaa.clichat.client.network.chat;

import org.jline.jansi.Ansi;

import muscaa.clichat.client.network.common.ClientCommonNetHandler;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;
import muscaa.clichat.shared.network.chat.packets.PacketCommandResponse;
import muscaa.clichat.shared.utils.Utils;

public class ClientChatNetHandler extends ClientCommonNetHandler implements IClientChatNetHandler {
	
	@Override
	public void onPacketChatLine(PacketChatLine packet) {
		Utils.print(Ansi.ansi().fgBrightBlack().a("[CHAT] ") + packet.getLine());
	}
	
	@Override
	public void onPacketCommandResponse(PacketCommandResponse packet) {
		Utils.print(packet.getResponse());
	}
}
