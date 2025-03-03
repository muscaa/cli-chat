package muscaa.clichat.client.network.login;

import muscaa.clichat.client.network.common.IClientCommonNetHandler;
import muscaa.clichat.shared.network.login.ILoginNetHandler;
import muscaa.clichat.shared.network.login.packets.PacketProfile;

public interface IClientLoginNetHandler extends IClientCommonNetHandler, ILoginNetHandler {
	
	void onPacketProfile(PacketProfile packet);
}
