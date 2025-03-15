package muscaa.clichat.server;

import java.util.regex.Pattern;

import org.jline.jansi.Ansi;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.StringArgumentInput;
import fluff.commander.command.CommandException;
import muscaa.clichat.server.command.IServerCommandSource;
import muscaa.clichat.server.command.ServerCommander;
import muscaa.clichat.server.command.ServerConsoleCommandSource;
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
	public IServerCommandSource console;
	public NetworkServer network;
	
	public void start() throws Exception {
		mainThread = Thread.currentThread();
		
		int port = Integer.parseInt(Utils.read(Ansi.ansi()
				.fgBrightBlue().a("Port: ")
				.reset()));
		
		inChat = true;
		commander = new ServerCommander();
		console = new ServerConsoleCommandSource();
		
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
					try {
						commander.execute(console, new StringArgumentInput(line));
					} catch (CommandException | ArgumentException e) {
						console.error(e.getMessage());
					}
					continue;
				}
				
				String command = commander.chatCommand(line);
				if (command != null) {
					try {
						commander.execute(console, new StringArgumentInput(command));
					} catch (CommandException | ArgumentException e) {
						console.error(e.getMessage());
					}
					continue;
				}
				
				ChatUtils.message(console.getName(), line);
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
