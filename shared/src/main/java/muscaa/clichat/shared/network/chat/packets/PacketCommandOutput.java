package muscaa.clichat.shared.network.chat.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketCommandOutput implements IPacket {
	
	private String output;
	
	public PacketCommandOutput(String output) {
		this.output = output;
	}
	
	public PacketCommandOutput() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		output = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(output);
	}
	
	public String getOutput() {
		return output;
	}
}
