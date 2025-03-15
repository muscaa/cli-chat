package muscaa.clichat.client.network;

import fluff.network.packet.PacketContext;
import muscaa.clichat.client.network.chat.IClientChatNetHandler;
import muscaa.clichat.client.network.common.IClientCommonNetHandler;
import muscaa.clichat.client.network.login.IClientLoginNetHandler;
import muscaa.clichat.shared.network.SharedContexts;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;
import muscaa.clichat.shared.network.chat.packets.PacketCommand;
import muscaa.clichat.shared.network.chat.packets.PacketCommandResponse;
import muscaa.clichat.shared.network.chat.packets.PacketMessage;
import muscaa.clichat.shared.network.login.packets.PacketLogin;
import muscaa.clichat.shared.network.login.packets.PacketProfile;

public class ClientContexts {
	
	//
	// COMMON
	//
	public static final PacketContext<IClientCommonNetHandler> COMMON =
			new PacketContext<IClientCommonNetHandler>(SharedContexts.COMMON)
			;
	
	//
	// LOGIN
	//
	public static final PacketContext<IClientLoginNetHandler> LOGIN =
			new PacketContext<IClientLoginNetHandler>(SharedContexts.LOGIN)
					.extend(COMMON)
					.registerOutbound(100, PacketLogin.class)
					.registerInbound(101, PacketProfile::new, IClientLoginNetHandler::onPacketProfile)
			;
	
	//
	// CHAT
	//
	public static final PacketContext<IClientChatNetHandler> CHAT =
			new PacketContext<IClientChatNetHandler>(SharedContexts.CHAT)
					.extend(COMMON)
					.registerOutbound(200, PacketMessage.class)
					.registerInbound(201, PacketChatLine::new, IClientChatNetHandler::onPacketChatLine)
					.registerOutbound(202, PacketCommand.class)
					.registerInbound(203, PacketCommandResponse::new, IClientChatNetHandler::onPacketCommandResponse)
			;
}
