package muscaa.clichat.server.network.login;

import muscaa.clichat.server.network.common.IServerCommonNetHandler;
import muscaa.clichat.shared.network.login.ILoginNetHandler;
import muscaa.clichat.shared.network.login.packets.PacketLogin;

public interface IServerLoginNetHandler extends IServerCommonNetHandler, ILoginNetHandler {
	
	void onPacketLogin(PacketLogin packet);
}
