package muscaa.clichat.shared.network.chat.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketCommand implements IPacket {
	
	private boolean commandMode;
	private String command;
	
	public PacketCommand(boolean commandMode, String command) {
		this.commandMode = commandMode;
		this.command = command;
	}
	
	public PacketCommand() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		commandMode = in.Boolean();
		command = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Boolean(commandMode);
		out.LenString(command);
	}
	
	public boolean isCommandMode() {
		return commandMode;
	}
	
	public String getCommand() {
		return command;
	}
}
