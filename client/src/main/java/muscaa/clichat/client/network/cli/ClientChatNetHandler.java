package muscaa.clichat.client.network.cli;

import org.jline.jansi.Ansi;

import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.client.network.common.ClientCommonNetHandler;
import muscaa.clichat.shared.network.cli.packets.PacketChatLine;
import muscaa.clichat.shared.network.cli.packets.PacketCommandError;
import muscaa.clichat.shared.network.cli.packets.PacketCommandExitCode;
import muscaa.clichat.shared.network.cli.packets.PacketCommandOutput;
import muscaa.clichat.shared.utils.Utils;

public class ClientChatNetHandler extends ClientCommonNetHandler implements IClientChatNetHandler {
	
	@Override
	public void onPacketChatLine(PacketChatLine packet) {
		Utils.print(Ansi.ansi().fgBrightBlack().a("[CHAT] ") + packet.getLine());
	}
	
	@Override
	public void onPacketCommandOutput(PacketCommandOutput packet) {
		Utils.print(packet.getOutput());
	}
	
	@Override
	public void onPacketCommandExitCode(PacketCommandExitCode packet) {
		CLIChatClient.INSTANCE.consoleCommander.completeServer(packet.getExitCode());
	}
	
	@Override
	public void onPacketCommandError(PacketCommandError packet) {
		CLIChatClient.INSTANCE.consoleCommander.completeServer(packet.getError());
	}
}
