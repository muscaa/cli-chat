package muscaa.clichat.server.network;

import fluff.network.packet.PacketContext;
import muscaa.clichat.server.network.chat.IServerChatNetHandler;
import muscaa.clichat.server.network.common.IServerCommonNetHandler;
import muscaa.clichat.server.network.login.IServerLoginNetHandler;
import muscaa.clichat.shared.network.SharedContexts;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;
import muscaa.clichat.shared.network.chat.packets.PacketCommandError;
import muscaa.clichat.shared.network.chat.packets.PacketCommandOutput;
import muscaa.clichat.shared.network.chat.packets.PacketCommandExitCode;
import muscaa.clichat.shared.network.chat.packets.PacketChatMessage;
import muscaa.clichat.shared.network.login.packets.PacketLogin;
import muscaa.clichat.shared.network.login.packets.PacketProfile;

public class ServerContexts {
	
	//
	// COMMON
	//
	public static final PacketContext<IServerCommonNetHandler> COMMON =
			new PacketContext<IServerCommonNetHandler>(SharedContexts.COMMON)
			;
	
	//
	// LOGIN
	//
	public static final PacketContext<IServerLoginNetHandler> LOGIN =
			new PacketContext<IServerLoginNetHandler>(SharedContexts.LOGIN)
					.extend(COMMON)
					.registerInbound(100, PacketLogin::new, IServerLoginNetHandler::onPacketLogin)
					.registerOutbound(101, PacketProfile.class)
			;
	
	//
	// CHAT
	//
	public static final PacketContext<IServerChatNetHandler> CHAT =
			new PacketContext<IServerChatNetHandler>(SharedContexts.CHAT)
					.extend(COMMON)
					.registerInbound(200, PacketChatMessage::new, IServerChatNetHandler::onPacketChatMessage)
					.registerOutbound(201, PacketChatLine.class)
					.registerInbound(202, PacketCommand::new, IServerChatNetHandler::onPacketCommand)
					.registerOutbound(203, PacketCommandOutput.class)
					.registerOutbound(204, PacketCommandExitCode.class)
					.registerOutbound(205, PacketCommandError.class)
			;
}
