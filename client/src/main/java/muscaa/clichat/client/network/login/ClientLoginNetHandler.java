package muscaa.clichat.client.network.login;

import muscaa.clichat.client.network.ClientContexts;
import muscaa.clichat.client.network.chat.ClientChatNetHandler;
import muscaa.clichat.client.network.common.ClientCommonNetHandler;
import muscaa.clichat.shared.network.login.packets.PacketProfile;

public class ClientLoginNetHandler extends ClientCommonNetHandler implements IClientLoginNetHandler {
	
	@Override
	public void onDisconnect() {
		super.onDisconnect();
		
		client.setName(null);
	}
	
	@Override
	public void onPacketProfile(PacketProfile packet) {
		client.setName(packet.getName());
		
		client.setContext(ClientContexts.CHAT, new ClientChatNetHandler());
	}
}
