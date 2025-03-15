package muscaa.clichat.client;

import org.jline.jansi.Ansi;

import muscaa.clichat.client.command.ClientCommander;
import muscaa.clichat.client.command.ClientConsoleCommandSource;
import muscaa.clichat.client.command.IClientCommandSource;
import muscaa.clichat.client.network.NetworkClient;
import muscaa.clichat.shared.network.chat.packets.PacketMessage;
import muscaa.clichat.shared.network.login.packets.PacketLogin;
import muscaa.clichat.shared.utils.Utils;

public class CLIChatClient {
	
	public static final CLIChatClient INSTANCE = new CLIChatClient();
	
	private Thread mainThread;
	public boolean inChat;
	public ClientCommander commander;
	public IClientCommandSource console;
	public NetworkClient network;
	
	public void start() throws Exception {
		mainThread = Thread.currentThread();
		
		String host = Utils.read(Ansi.ansi()
				.fgBrightBlue().a("Host: ")
				.reset());
		int port = Integer.parseInt(Utils.read(Ansi.ansi()
				.fgBrightBlue().a("Port: ")
				.reset()));
		
		inChat = true;
		commander = new ClientCommander();
		console = new ClientConsoleCommandSource();
		
		network = new NetworkClient();
		network.connect(host, port);
		
		String name = Utils.read(Ansi.ansi()
				.fgBrightBlue().a("Name: ")
				.reset());
		
		network.send(new PacketLogin(name));
		if (!waitForProfile()) return;
		
		Utils.print(Ansi.ansi()
				.fgBlue().a("Connected to ")
				.fgBrightBlue().a(host + ":" + port)
				.fgBlue().a(" as ")
				.fgBrightBlue().a(network.getName())
				.reset());
		Utils.print(Ansi.ansi()
				.fgBlue().a("Type '")
				.fgBrightBlue().a("/dc")
				.fgBlue().a("' to disconnect")
				.reset());
        
		try {
			while (!mainThread.isInterrupted() && network.isConnected()) {
				String line = Utils.read(Ansi.ansi()
						.fgBrightBlack().a(">> ")
						.reset());
				if (line == null) break;
				
				if (!inChat) {
					commander.execute(console, line);
					continue;
				}
				
				String command = commander.chatCommand(line);
				if (command != null) {
					commander.execute(console, command);
					continue;
				}
				
				network.send(new PacketMessage(line));
			}
		} catch (IllegalStateException e) {}
		
		network.disconnect();
	}
	
	public void stop() {
		mainThread.interrupt();
	}
	
	private boolean waitForProfile() {
		long connectionTime = System.currentTimeMillis();
		
		// TODO switch to wait-notify for better performance
		
    	while (true) {
    		if (!network.isConnected()) break;
    		if (network.getName() != null) break;
    		if (System.currentTimeMillis() > connectionTime + 3000) break;
    		
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
    	}
    	
		if (!network.isConnected()) return false;
		
		if (network.getName() == null) {
			network.disconnect();
			
			Utils.print(Utils.error("Timed out while waiting for profile."));
			
			return false;
		}
		
		return true;
	}
}
