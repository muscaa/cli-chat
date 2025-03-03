package muscaa.clichat.server.network.login;

import muscaa.clichat.server.network.common.ServerCommonNetHandler;
import muscaa.clichat.shared.network.login.packets.PacketLogin;

public class ServerLoginNetHandler extends ServerCommonNetHandler implements IServerLoginNetHandler {
	
	@Override
	public void onPacketLogin(PacketLogin packet) {
		connection.login(packet.getName());
	}
}
