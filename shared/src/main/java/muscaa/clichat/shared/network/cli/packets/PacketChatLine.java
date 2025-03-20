package muscaa.clichat.shared.network.cli.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketChatLine implements IPacket {
	
	private String line;
	
	public PacketChatLine(String line) {
		this.line = line;
	}
	
	public PacketChatLine() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		line = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(line);
	}
	
	public String getLine() {
		return line;
	}
}
