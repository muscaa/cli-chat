package muscaa.clichat.server;

import java.util.regex.Pattern;

import org.jline.jansi.Ansi;

import muscaa.clichat.server.command.ConsoleCommander;
import muscaa.clichat.server.command.ServerCommander;
import muscaa.clichat.server.network.NetworkClientConnection;
import muscaa.clichat.server.network.NetworkServer;
import muscaa.clichat.server.utils.ChatUtils;
import muscaa.clichat.shared.utils.Utils;

public class CLIChatServer {
	
	public static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+$");
	public static final Pattern CHAT_PATTERN = Pattern.compile("^[\\p{L}\\p{N}\\p{P}\\p{Z}\\p{M}]+$");
	public static final CLIChatServer INSTANCE = new CLIChatServer();
	
	private Thread mainThread;
	public boolean inChat;
	public ServerCommander commander;
	public ConsoleCommander consoleCommander;
	public NetworkServer network;
	
	public void start() throws Exception {
		mainThread = Thread.currentThread();
		
		int port = Integer.parseInt(Utils.read(Ansi.ansi()
				.fgBrightBlue().a("Port: ")
				.reset()));
		
		inChat = true;
		commander = new ServerCommander();
		consoleCommander = new ConsoleCommander();
		
		network = new NetworkServer(port);
		network.start(true);
		
		Utils.print(Ansi.ansi()
				.fgBlue().a("Server started on port ")
				.fgBrightBlue().a(port)
				.reset());
		Utils.print(Ansi.ansi()
				.fgBlue().a("Type '")
				.fgBrightBlue().a("/stop")
				.fgBlue().a("' to stop the server")
				.reset());
		
		try {
			while (!mainThread.isInterrupted()) {
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
				
				ChatUtils.message(consoleCommander.getConsole().getName(), line);
			}
		} catch (IllegalStateException e) {}
		
		network.stop();
		
		Utils.print(Utils.error("Server stopped."));
	}
	
	public void stop() {
		ChatUtils.broadcast(Utils.error("Stopping server..."), NetworkClientConnection::isOp);
		
		mainThread.interrupt();
	}
}
