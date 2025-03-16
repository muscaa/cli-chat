package muscaa.clichat.shared.network.chat.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketChatMessage implements IPacket {
	
	private String message;
	
	public PacketChatMessage(String message) {
		this.message = message;
	}
	
	public PacketChatMessage() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		message = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(message);
	}
	
	public String getMessage() {
		return message;
	}
}
