package muscaa.clichat.client.network;

import fluff.network.packet.PacketContext;
import muscaa.clichat.client.network.cli.IClientChatNetHandler;
import muscaa.clichat.client.network.common.IClientCommonNetHandler;
import muscaa.clichat.client.network.login.IClientLoginNetHandler;
import muscaa.clichat.shared.network.SharedContexts;
import muscaa.clichat.shared.network.cli.packets.PacketChatLine;
import muscaa.clichat.shared.network.cli.packets.PacketChatMessage;
import muscaa.clichat.shared.network.cli.packets.PacketCommand;
import muscaa.clichat.shared.network.cli.packets.PacketCommandError;
import muscaa.clichat.shared.network.cli.packets.PacketCommandExitCode;
import muscaa.clichat.shared.network.cli.packets.PacketCommandOutput;
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
	// CLI
	//
	public static final PacketContext<IClientChatNetHandler> CLI =
			new PacketContext<IClientChatNetHandler>(SharedContexts.CLI)
					.extend(COMMON)
					.registerOutbound(200, PacketChatMessage.class)
					.registerInbound(201, PacketChatLine::new, IClientChatNetHandler::onPacketChatLine)
					.registerOutbound(202, PacketCommand.class)
					.registerInbound(203, PacketCommandOutput::new, IClientChatNetHandler::onPacketCommandOutput)
					.registerInbound(204, PacketCommandExitCode::new, IClientChatNetHandler::onPacketCommandExitCode)
					.registerInbound(205, PacketCommandError::new, IClientChatNetHandler::onPacketCommandError)
			;
}
