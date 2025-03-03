package muscaa.clichat.shared.network.common.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketDisconnect implements IPacket {
	
	private String reason;
	
	public PacketDisconnect(String reason) {
		this.reason = reason;
	}
	
	public PacketDisconnect() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		reason = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(reason);
	}
	
	public String getReason() {
		return reason;
	}
}
