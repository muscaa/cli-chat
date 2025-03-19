package muscaa.clichat.client;

import java.util.concurrent.TimeUnit;

import org.jline.jansi.Ansi;

import muscaa.clichat.client.command.ConsoleCommander;
import muscaa.clichat.client.network.NetworkClient;
import muscaa.clichat.shared.network.chat.packets.PacketChatMessage;
import muscaa.clichat.shared.network.login.packets.PacketLogin;
import muscaa.clichat.shared.utils.Utils;

public class CLIChatClient {
	
	public static final CLIChatClient INSTANCE = new CLIChatClient();
	
	private Thread mainThread;
	public boolean inChat;
	public ConsoleCommander consoleCommander;
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
		consoleCommander = new ConsoleCommander();
		
		network = new NetworkClient();
		network.connect(host, port);
		
		String name = Utils.read(Ansi.ansi()
				.fgBrightBlue().a("Name: ")
				.reset());
		
		network.send(new PacketLogin(name));
		
		try {
			String profile = network.getNameFuture().get(3, TimeUnit.SECONDS);
			
			if (profile == null) return;
		} catch (Exception e) {
			network.disconnect();
			
			Utils.print(Utils.error("Timed out while waiting for profile."));
			return;
		}
		
		Utils.print(Ansi.ansi()
				.fgBlue().a("Connected to ")
				.fgBrightBlue().a(host + ":" + port)
				.fgBlue().a(" as ")
				.fgBrightBlue().a(network.getName())
				.reset());
		Utils.print(Ansi.ansi()
				.fgBlue().a("Type '")
				.fgBrightBlue().a("//dc")
				.fgBlue().a("' to disconnect")
				.reset());
        
		try {
			while (!mainThread.isInterrupted() && network.isConnected()) {
				String line = Utils.read(Ansi.ansi()
						.fgBrightBlack().a(">> ")
						.reset());
				if (line == null) break;
				
				if (!inChat) {
					consoleCommander.executeClient(line);
					continue;
				}
				
				String command = consoleCommander.getChatCommandClient(line);
				if (command != null) {
					consoleCommander.executeClient(command);
					continue;
				} else {
					command = consoleCommander.getChatCommandServer(line);
					if (command != null) {
						consoleCommander.executeServer(command);
						continue;
					}
				}
				
				network.send(new PacketChatMessage(line));
			}
		} catch (IllegalStateException e) {}
		
		network.disconnect();
	}
	
	public void stop() {
		mainThread.interrupt();
	}
}
