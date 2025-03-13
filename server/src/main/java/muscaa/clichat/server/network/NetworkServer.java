package muscaa.clichat.server.network;

import java.util.Set;
import java.util.UUID;

import fluff.network.NetworkException;
import fluff.network.packet.channels.DefaultPacketChannel;
import fluff.network.server.AbstractClientConnection;
import fluff.network.server.AbstractServer;
import fluff.network.server.modules.TimeoutModule;
import muscaa.clichat.server.network.login.ServerLoginNetHandler;
import muscaa.clichat.shared.network.common.packets.PacketDisconnect;

public class NetworkServer extends AbstractServer {
	
	public NetworkServer(int port) {
		super(port);
		
		addModule(new TimeoutModule(10000));
		
		setDefaultContext(ServerContexts.LOGIN, ServerLoginNetHandler::new);
		setDefaultChannel(DefaultPacketChannel::new);
	}
	
	@Override
	protected AbstractClientConnection createConnection() {
		return new NetworkClientConnection(this, defaultContext, defaultHandlerFunc.invoke(), defaultChannelFunc.invoke());
	}
	
	@Override
	public void onConnect(AbstractClientConnection connection) throws NetworkException {
		super.onConnect(connection);
	}
	
	@Override
	public void disconnectAll() {
		sendAll(new PacketDisconnect("Server stopped!"));
		super.disconnectAll();
	}
	
	@Override
	public Set<UUID> getUUIDKeys() {
		return super.getUUIDKeys();
	}
	
	public NetworkClientConnection getConnection(UUID uuid) {
		return (NetworkClientConnection) connections.get(uuid);
	}
	
	public NetworkClientConnection getConnection(String name) {
		UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
		
		return getConnection(uuid);
	}
	
	public boolean isConnected(UUID uuid) {
		return connections.containsKey(uuid);
	}
}
