package muscaa.clichat.server.network.common;

import fluff.network.AbstractServerNetHandler;
import muscaa.clichat.server.network.NetworkClientConnection;
import muscaa.clichat.server.network.NetworkServer;
import muscaa.clichat.shared.network.common.packets.PacketDisconnect;

public abstract class ServerCommonNetHandler extends AbstractServerNetHandler<NetworkServer, NetworkClientConnection> implements IServerCommonNetHandler {
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		connection.disconnect();
	}
}
