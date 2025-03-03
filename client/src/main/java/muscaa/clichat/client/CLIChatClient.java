package muscaa.clichat.client;

import java.util.Scanner;

import muscaa.clichat.client.network.NetworkClient;
import muscaa.clichat.shared.network.chat.packets.PacketMessage;
import muscaa.clichat.shared.network.login.packets.PacketLogin;

public class CLIChatClient {
	
	public static final CLIChatClient INSTANCE = new CLIChatClient();
	
	public NetworkClient network;
	
	public void start(Scanner s) throws Exception {
		System.out.print("Host: ");
		String host = s.nextLine();
		
		System.out.print("Port: ");
		int port = s.nextInt();
		s.nextLine();
		
		network = new NetworkClient();
		network.connect(host, port);
		
		System.out.print("Name: ");
		String name = s.nextLine();
		
		network.send(new PacketLogin(name));
		if (!waitForProfile()) return;
		
		System.out.println("Connected to " + host + ":" + port + " as " + network.getName());
		System.out.println("Type 'stop' to disconnect.");
        
		while (s.hasNextLine() && network.isConnected()) {
			String line = s.nextLine();
			if (line.equals("stop")) break;
			
			network.send(new PacketMessage(line));
		}
		
		network.disconnect();
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
			
			System.out.println("Timed out while waiting for profile.");
			
			return false;
		}
		
		return true;
	}
}
