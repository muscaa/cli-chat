package muscaa.clichat.server;

import java.util.Scanner;
import java.util.regex.Pattern;

import muscaa.clichat.server.command.CommandManager;
import muscaa.clichat.server.command.ConsoleCommandSource;
import muscaa.clichat.server.command.ICommandSource;
import muscaa.clichat.server.network.NetworkClientConnection;
import muscaa.clichat.server.network.NetworkServer;
import muscaa.clichat.server.utils.ChatUtils;

public class CLIChatServer {
	
	public static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+$");
	public static final Pattern CHAT_PATTERN = Pattern.compile("^[\\p{L}\\p{N}\\p{P}\\p{Z}\\p{M}]+$");
	public static final CLIChatServer INSTANCE = new CLIChatServer();
	
	private boolean running;
	private Scanner scanner;
	public CommandManager commands;
	public ICommandSource console;
	public NetworkServer network;
	
	public void start() throws Exception {
		running = true;
		scanner = new Scanner(System.in);
		
		System.out.print("Port: ");
		int port = scanner.nextInt();
		scanner.nextLine();
		
		commands = new CommandManager();
		console = new ConsoleCommandSource();
		
		network = new NetworkServer(port);
		network.start(true);
		
		System.out.println("Server started on port " + port);
		System.out.println("Type '/stop' to stop the server.");
		
		try {
			while (running) {
				String line = scanner.nextLine();
				
				String command = CLIChatServer.INSTANCE.commands.parseCommand(line);
				if (command != null) {
					CLIChatServer.INSTANCE.commands.execute(console, command);
					continue;
				}
				
				ChatUtils.message(console.getName(), line);
			}
		} catch (IllegalStateException e) {}
		
		network.stop();
		
		System.out.println("Server stopped.");
	}
	
	public void stop() {
		ChatUtils.broadcast("Stopping server...", NetworkClientConnection::isOp);
		
		running = false;
	}
}
