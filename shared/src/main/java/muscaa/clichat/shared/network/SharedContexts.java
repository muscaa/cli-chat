package muscaa.clichat.shared.network;

import fluff.network.packet.PacketContext;
import muscaa.clichat.shared.network.cli.IChatNetHandler;
import muscaa.clichat.shared.network.common.ICommonNetHandler;
import muscaa.clichat.shared.network.common.packets.PacketDisconnect;
import muscaa.clichat.shared.network.login.ILoginNetHandler;

public class SharedContexts {
	
	//
	// COMMON
	//
	public static final PacketContext<ICommonNetHandler> COMMON =
			new PacketContext<ICommonNetHandler>("common_context")
					.register(0, PacketDisconnect.class, PacketDisconnect::new, ICommonNetHandler::onPacketDisconnect)
			;
	
	//
	// LOGIN
	//
	public static final PacketContext<ILoginNetHandler> LOGIN =
			new PacketContext<ILoginNetHandler>("login_context")
					.extend(COMMON)
			;
	
	//
	// CLI
	//
	public static final PacketContext<IChatNetHandler> CLI =
			new PacketContext<IChatNetHandler>("cli_context")
					.extend(COMMON)
			;
}
