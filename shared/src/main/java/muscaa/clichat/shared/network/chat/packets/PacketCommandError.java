package muscaa.clichat.shared.network.chat.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketCommandError implements IPacket {
	
	public PacketCommandError() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {}
}
