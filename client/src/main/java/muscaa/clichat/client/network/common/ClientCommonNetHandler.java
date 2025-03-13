package muscaa.clichat.client.network.common;

import fluff.network.AbstractClientNetHandler;
import muscaa.clichat.client.network.NetworkClient;
import muscaa.clichat.shared.network.common.packets.PacketDisconnect;
import muscaa.clichat.shared.utils.Utils;

public abstract class ClientCommonNetHandler extends AbstractClientNetHandler<NetworkClient> implements IClientCommonNetHandler {
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		Utils.print(Utils.error(packet.getReason()));
		
		client.disconnect();
	}
}
