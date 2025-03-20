package muscaa.clichat.server.network;

import fluff.network.packet.PacketContext;
import muscaa.clichat.server.network.cli.IServerChatNetHandler;
import muscaa.clichat.server.network.common.IServerCommonNetHandler;
import muscaa.clichat.server.network.login.IServerLoginNetHandler;
import muscaa.clichat.shared.network.SharedContexts;
import muscaa.clichat.shared.network.cli.packets.PacketChatLine;
import muscaa.clichat.shared.network.cli.packets.PacketChatMessage;
import muscaa.clichat.shared.network.cli.packets.PacketCommand;
import muscaa.clichat.shared.network.cli.packets.PacketCommandError;
import muscaa.clichat.shared.network.cli.packets.PacketCommandExitCode;
import muscaa.clichat.shared.network.cli.packets.PacketCommandOutput;
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
	// CLI
	//
	public static final PacketContext<IServerChatNetHandler> CLI =
			new PacketContext<IServerChatNetHandler>(SharedContexts.CLI)
					.extend(COMMON)
					.registerInbound(200, PacketChatMessage::new, IServerChatNetHandler::onPacketChatMessage)
					.registerOutbound(201, PacketChatLine.class)
					.registerInbound(202, PacketCommand::new, IServerChatNetHandler::onPacketCommand)
					.registerOutbound(203, PacketCommandOutput.class)
					.registerOutbound(204, PacketCommandExitCode.class)
					.registerOutbound(205, PacketCommandError.class)
			;
}
