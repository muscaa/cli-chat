package muscaa.clichat.client.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import fluff.network.NetworkException;
import fluff.network.client.AbstractClient;
import fluff.network.client.ClientErrorType;
import fluff.network.packet.IPacketOutbound;
import fluff.network.packet.PacketContext;
import fluff.network.packet.channels.DefaultPacketChannel;
import muscaa.clichat.client.network.login.ClientLoginNetHandler;

public class NetworkClient extends AbstractClient {
	
	private String name;
	
    @SuppressWarnings("resource")
	public void connect(String host, int port) throws UnknownHostException, IOException, NetworkException {
    	if (isConnected()) disconnect();
    	
    	setContext(ClientContexts.LOGIN, new ClientLoginNetHandler());
		setChannel(new DefaultPacketChannel());
		openConnection(new Socket(host, port));
		
		if (!isConnected()) {
			disconnect();
		}
    }
    
    public PacketContext<?> getContext() {
    	return context;
    }
    
	@Override
	public void send(IPacketOutbound packet) {
		if (!isConnected()) return;
		
		super.send(packet);
	}
	
	@Override
	public void disconnect() {
		if (!isConnected()) return;
		
		//super.send(new PacketDisconnect("disconnected"));
		super.disconnect();
	}
	
	@Override
	protected void onError(ClientErrorType type, Exception e) {
        switch (type) {
	        case CONNECTION:
	            super.disconnect();
	            break;
	        case READ:
	            disconnect();
	            break;
	        case WRITE:
	            // nothing
	            break;
	    }
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
