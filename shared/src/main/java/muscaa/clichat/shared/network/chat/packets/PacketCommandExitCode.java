package muscaa.clichat.shared.network.chat.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketCommandExitCode implements IPacket {
	
	private int exitCode;
	
	public PacketCommandExitCode(int exitCode) {
		this.exitCode = exitCode;
	}
	
	public PacketCommandExitCode() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		exitCode = in.Int();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(exitCode);
	}
	
	public int getExitCode() {
		return exitCode;
	}
}
