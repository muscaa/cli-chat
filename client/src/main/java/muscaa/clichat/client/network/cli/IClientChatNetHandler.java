package muscaa.clichat.client.network.cli;

import muscaa.clichat.client.network.common.IClientCommonNetHandler;
import muscaa.clichat.shared.network.cli.IChatNetHandler;
import muscaa.clichat.shared.network.cli.packets.PacketChatLine;
import muscaa.clichat.shared.network.cli.packets.PacketCommandError;
import muscaa.clichat.shared.network.cli.packets.PacketCommandExitCode;
import muscaa.clichat.shared.network.cli.packets.PacketCommandOutput;

public interface IClientChatNetHandler extends IClientCommonNetHandler, IChatNetHandler {
	
	void onPacketChatLine(PacketChatLine packet);
	
	void onPacketCommandOutput(PacketCommandOutput packet);
	
	void onPacketCommandExitCode(PacketCommandExitCode packet);
	
	void onPacketCommandError(PacketCommandError packet);
}
