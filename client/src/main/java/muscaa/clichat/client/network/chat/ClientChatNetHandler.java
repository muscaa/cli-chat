package muscaa.clichat.client.network.chat;

import org.jline.jansi.Ansi;

import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.client.network.common.ClientCommonNetHandler;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;
import muscaa.clichat.shared.network.chat.packets.PacketCommandError;
import muscaa.clichat.shared.network.chat.packets.PacketCommandOutput;
import muscaa.clichat.shared.network.chat.packets.PacketCommandResult;
import muscaa.clichat.shared.utils.Utils;

public class ClientChatNetHandler extends ClientCommonNetHandler implements IClientChatNetHandler {
	
	@Override
	public void onPacketChatLine(PacketChatLine packet) {
		Utils.print(Ansi.ansi().fgBrightBlack().a("[CHAT] ") + packet.getLine());
	}
	
	@Override
	public void onPacketCommandOutput(PacketCommandOutput packet) {
		Utils.print(packet.getResponse());
	}
	
	@Override
	public void onPacketCommandResult(PacketCommandResult packet) {
		Utils.print(packet.getExitCode());
		
		CLIChatClient.INSTANCE.commander.commandFuture.complete(true);
	}
	
	@Override
	public void onPacketCommandError(PacketCommandError packet) {
		Utils.print("error");
		
		CLIChatClient.INSTANCE.commander.commandFuture.complete(false);
	}
}
