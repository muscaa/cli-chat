package muscaa.clichat.shared.network.common;

import fluff.network.INetHandler;
import muscaa.clichat.shared.network.common.packets.PacketDisconnect;

public interface ICommonNetHandler extends INetHandler {
	
	void onPacketDisconnect(PacketDisconnect packet);
}
