package muscaa.clichat.shared.network.chat.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketCommandOutput implements IPacket {
	
	private String response;
	
	public PacketCommandOutput(String response) {
		this.response = response;
	}
	
	public PacketCommandOutput() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		response = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(response);
	}
	
	public String getResponse() {
		return response;
	}
}
