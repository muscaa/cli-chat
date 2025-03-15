package muscaa.clichat.server.network;

import fluff.network.packet.PacketContext;
import muscaa.clichat.server.network.chat.IServerChatNetHandler;
import muscaa.clichat.server.network.common.IServerCommonNetHandler;
import muscaa.clichat.server.network.login.IServerLoginNetHandler;
import muscaa.clichat.shared.network.SharedContexts;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;
import muscaa.clichat.shared.network.chat.packets.PacketCommandResponse;
import muscaa.clichat.shared.network.chat.packets.PacketMessage;
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
					.registerInbound(200, PacketMessage::new, IServerChatNetHandler::onPacketMessage)
					.registerOutbound(201, PacketChatLine.class)
					.registerInbound(202, PacketCommand::new, IServerChatNetHandler::onPacketCommand)
					.registerOutbound(203, PacketCommandResponse.class)
			;
}
