package muscaa.clichat.shared.network.chat.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketCommand implements IPacket {
	
	private boolean direct;
	private String command;
	
	public PacketCommand(boolean direct, String command) {
		this.direct = direct;
		this.command = command;
	}
	
	public PacketCommand() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		direct = in.Boolean();
		command = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Boolean(direct);
		out.LenString(command);
	}
	
	public boolean isDirect() {
		return direct;
	}
	
	public String getCommand() {
		return command;
	}
}
