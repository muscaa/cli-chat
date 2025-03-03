package muscaa.clichat.shared.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketProfile implements IPacket {
	
	private String name;
	
	public PacketProfile(String name) {
		this.name = name;
	}
	
	public PacketProfile() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		name = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(name);
	}
	
	public String getName() {
		return name;
	}
}
