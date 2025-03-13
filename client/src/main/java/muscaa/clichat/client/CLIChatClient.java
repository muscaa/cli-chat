package muscaa.clichat.client;

import java.util.Scanner;

import muscaa.clichat.client.network.NetworkClient;
import muscaa.clichat.shared.network.chat.packets.PacketMessage;
import muscaa.clichat.shared.network.login.packets.PacketLogin;

public class CLIChatClient {
	
	public static final CLIChatClient INSTANCE = new CLIChatClient();
	
	private boolean running;
	private Scanner scanner;
	public NetworkClient network;
	
	public void start() throws Exception {
		running = true;
		scanner = new Scanner(System.in);
		
		System.out.print("Host: ");
		String host = scanner.nextLine();
		
		System.out.print("Port: ");
		int port = scanner.nextInt();
		scanner.nextLine();
		
		network = new NetworkClient();
		network.connect(host, port);
		
		System.out.print("Name: ");
		String name = scanner.nextLine();
		
		network.send(new PacketLogin(name));
		if (!waitForProfile()) return;
		
		System.out.println("Connected to " + host + ":" + port + " as " + network.getName());
		System.out.println("Type '/dc' to disconnect.");
        
		try {
			while (running && network.isConnected()) {
				String line = scanner.nextLine();
				if (line.equals("/dc")) break;
				
				network.send(new PacketMessage(line));
			}
		} catch (IllegalStateException e) {}
		
		network.disconnect();
	}
	
	public void stop() {
		running = false;
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
