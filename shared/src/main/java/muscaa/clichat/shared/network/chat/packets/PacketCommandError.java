package muscaa.clichat.shared.network.chat.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketCommandError implements IPacket {
	
	private String error;
	
	public PacketCommandError(String error) {
		this.error = error;
	}
	
	public PacketCommandError() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		error = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(error);
	}
	
	public String getError() {
		return error;
	}
}
