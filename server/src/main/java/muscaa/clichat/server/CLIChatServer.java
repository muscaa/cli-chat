package muscaa.clichat.server;

import java.util.Scanner;

import muscaa.clichat.server.network.NetworkServer;
import muscaa.clichat.shared.network.chat.packets.PacketChatLine;

public class CLIChatServer {
	
	public static final CLIChatServer INSTANCE = new CLIChatServer();
	
	public NetworkServer network;
	
	public void start(Scanner s) throws Exception {
		System.out.print("Port: ");
		int port = s.nextInt();
		s.nextLine();
		
		network = new NetworkServer(port);
		network.start(true);
		
		System.out.println("Server started on port " + port);
		System.out.println("Type 'stop' to stop the server.");
		
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.equals("stop")) break;
			
			chat("CONSOLE", line);
		}
		
		network.stop();
	}
	
	public void chat(String name, String message) {
		chat(name + " >> " + message);
	}
	
	public void chat(String line) {
		System.out.println(line);
		
		network.sendAll(new PacketChatLine(line));
	}
}
