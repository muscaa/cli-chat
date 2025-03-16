package muscaa.clichat.server.network;

import java.util.Objects;
import java.util.UUID;

import org.jline.jansi.Ansi;

import fluff.network.INetHandler;
import fluff.network.NetworkException;
import fluff.network.packet.IPacketChannel;
import fluff.network.packet.PacketContext;
import fluff.network.server.AbstractClientConnection;
import fluff.network.server.AbstractServer;
import fluff.network.server.modules.TimeoutModule;
import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.command.IServerCommandSource;
import muscaa.clichat.server.network.chat.ServerChatNetHandler;
import muscaa.clichat.server.utils.ChatUtils;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;
import muscaa.clichat.shared.network.chat.packets.PacketCommandOutput;
import muscaa.clichat.shared.network.common.packets.PacketDisconnect;
import muscaa.clichat.shared.network.login.packets.PacketProfile;
import muscaa.clichat.shared.utils.Utils;

public class NetworkClientConnection extends AbstractClientConnection implements TimeoutModule.TimeoutListener, IServerCommandSource {
	
	private UUID uuid;
	private String name;
	private boolean op;
	private boolean commandMode;
	
	public NetworkClientConnection(AbstractServer server, PacketContext<?> context, INetHandler handler, IPacketChannel channel) {
		super(server);
		
		setContextUnsafe(context, handler);
        setChannel(channel);
	}
	
	public void login(String name) {
		if (this.name != null) return;
		
		if (name.equalsIgnoreCase(CLIChatServer.INSTANCE.console.getName()) || !CLIChatServer.NAME_PATTERN.matcher(name).matches()) {
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
		
		ChatUtils.broadcast(Utils.info(name + " has joined."));
		
		setContext(ServerContexts.CHAT, new ServerChatNetHandler());
	}
	
	@Override
	public void onConnect() throws NetworkException {
		ChatUtils.broadcast(Ansi.ansi()
				.fgBlue().a("Connection from ")
				.fgBrightBlue().a(socket.getInetAddress() + ":" + socket.getPort())
				.fgBlue().a(" - ")
				.fgBrightBlue().a(socket.getLocalAddress() + ":" + socket.getLocalPort())
				.reset(), NetworkClientConnection::isOp);
		
		super.onConnect();
	}
	
	@Override
	public void onDisconnect() {
		super.onDisconnect();
		
		if (name != null) {
			ChatUtils.broadcast(Utils.warn(name + " has left."));
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
	
	@Override
	public void log(Object o) {
		send(new PacketCommandOutput(Objects.toString(o)));
	}
	
	@Override
	public void info(Object o) {
		send(new PacketCommandOutput(Utils.info(o)));
	}
	
	@Override
	public void warn(Object o) {
		send(new PacketCommandOutput(Utils.warn(o)));
	}
	
	@Override
	public void error(Object o) {
		send(new PacketCommandOutput(Utils.error(o)));
	}
	
	@Override
	public boolean isCommandMode() {
		return commandMode;
	}
	
	public void setCommandMode(boolean commandMode) {
		this.commandMode = commandMode;
	}
	
	@Override
	public void addChatLine(String line) {
		send(new PacketChatLine(line));
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean isOp() {
		return op;
	}
	
	public void setOp(boolean op) {
		this.op = op;
	}
}
