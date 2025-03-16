package muscaa.clichat.server.network.chat;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.network.common.ServerCommonNetHandler;
import muscaa.clichat.server.utils.ChatUtils;
import muscaa.clichat.shared.command.CommandResult;
import muscaa.clichat.shared.network.chat.packets.PacketChatMessage;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;
import muscaa.clichat.shared.network.chat.packets.PacketCommandError;
import muscaa.clichat.shared.network.chat.packets.PacketCommandExitCode;

public class ServerChatNetHandler extends ServerCommonNetHandler implements IServerChatNetHandler {
	
	@Override
	public void onPacketChatMessage(PacketChatMessage packet) {
		ChatUtils.message(connection.getName(), packet.getMessage());
	}
	
	@Override
	public void onPacketCommand(PacketCommand packet) {
		connection.setCommandMode(packet.isCommandMode());
		
		CommandResult result = CLIChatServer.INSTANCE.commander.execute(connection, packet.getCommand());
		if (result.error == null) {
			connection.send(new PacketCommandExitCode(result.exitCode));
		} else {
			connection.send(new PacketCommandError(result.error));
		}
	}
}
