package muscaa.clichat.client.network.common;

import fluff.network.AbstractClientNetHandler;
import muscaa.clichat.client.network.NetworkClient;
import muscaa.clichat.shared.network.common.packets.PacketDisconnect;

public abstract class ClientCommonNetHandler extends AbstractClientNetHandler<NetworkClient> implements IClientCommonNetHandler {
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		System.out.println("Disconnected: " + packet.getReason());
		
		client.disconnect();
	}
}
