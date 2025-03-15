package muscaa.clichat.server.network.chat;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.StringArgumentInput;
import fluff.commander.command.CommandException;
import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.network.common.ServerCommonNetHandler;
import muscaa.clichat.server.utils.ChatUtils;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;
import muscaa.clichat.shared.network.chat.packets.PacketCommandError;
import muscaa.clichat.shared.network.chat.packets.PacketCommandResult;
import muscaa.clichat.shared.network.chat.packets.PacketMessage;

public class ServerChatNetHandler extends ServerCommonNetHandler implements IServerChatNetHandler {
	
	@Override
	public void onPacketMessage(PacketMessage packet) {
		ChatUtils.message(connection.getName(), packet.getMessage());
	}
	
	@Override
	public void onPacketCommand(PacketCommand packet) {
		connection.setDirect(packet.isDirect());
		
		try {
			int exitCode = CLIChatServer.INSTANCE.commander.execute(connection, new StringArgumentInput(packet.getCommand()));
			
			connection.send(new PacketCommandResult(exitCode));
		} catch (CommandException | ArgumentException e) {
			connection.error(e.getMessage());
			
			connection.send(new PacketCommandError());
		}
	}
}
