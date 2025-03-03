package muscaa.clichat.server.network;

import java.util.UUID;
import java.util.regex.Pattern;

import fluff.network.INetHandler;
import fluff.network.NetworkException;
import fluff.network.packet.IPacketChannel;
import fluff.network.packet.PacketContext;
import fluff.network.server.AbstractClientConnection;
import fluff.network.server.AbstractServer;
import fluff.network.server.modules.TimeoutModule;
import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.network.chat.ServerChatNetHandler;
import muscaa.clichat.shared.network.common.packets.PacketDisconnect;
import muscaa.clichat.shared.network.login.packets.PacketProfile;

public class NetworkClientConnection extends AbstractClientConnection implements TimeoutModule.TimeoutListener {
	
	private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]+$");
	
	private UUID uuid;
	private String name;
	
	public NetworkClientConnection(AbstractServer server, PacketContext<?> context, INetHandler handler, IPacketChannel channel) {
		super(server);
		
		setContextUnsafe(context, handler);
        setChannel(channel);
	}
	
	public void login(String name) {
		if (this.name != null) return;
		
		if (name.equalsIgnoreCase("CONSOLE") || !NAME_PATTERN.matcher(name).matches()) {
			disconnect("Invalid name!");
			return;
		}
		
		NetworkServer ns = (NetworkServer) server;
		
		UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
		if (ns.isConnected(uuid)) {
			disconnect("Name already in use!");
            return;
		}
		
		this.uuid = uuid;
		this.name = name;
	}
	
	@Override
	public void onConnectionTimedOut() {
		disconnect("Connection timed out!");
	}
	
	@Override
	public void onConnectionEstablished() throws NetworkException {
		send(new PacketProfile(name));
		
		CLIChatServer.INSTANCE.chat(name + " has joined.");
		
		setContext(ServerContexts.CHAT, new ServerChatNetHandler());
	}
	
	@Override
	public void onConnect() throws NetworkException {
		System.out.println("Connection from " + socket.getInetAddress() + ":" + socket.getPort() + " - " + socket.getLocalAddress() + ":" + socket.getLocalPort());
		
		super.onConnect();
	}
	
	@Override
	public void onDisconnect() {
		super.onDisconnect();
		
		if (name != null) {
			CLIChatServer.INSTANCE.chat(name + " has left.");
		}
	}
	
	public void disconnect(String message) {
		send(new PacketDisconnect(message));
		disconnect();
	}
	
	@Override
	public UUID getUUID() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}
}
