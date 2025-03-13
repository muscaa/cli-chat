package muscaa.clichat.server.utils;

import java.util.Set;
import java.util.UUID;

import fluff.functions.gen.obj.BooleanFunc1;
import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.server.network.NetworkClientConnection;

public class ChatUtils {
	
	public static void broadcast(String line, BooleanFunc1<NetworkClientConnection> filterFunc) {
		CLIChatServer.INSTANCE.console.addChatLine(line);
		
    	Set<UUID> keys = CLIChatServer.INSTANCE.network.getUUIDKeys();
    	if (filterFunc != null) {
            for (UUID uuid : keys) {
            	NetworkClientConnection connection = CLIChatServer.INSTANCE.network.getConnection(uuid);
            	if (connection == null) continue;
            	if (!filterFunc.invoke(connection)) continue;
            	
                connection.addChatLine(line);
            }
    	} else {
            for (UUID uuid : keys) {
            	NetworkClientConnection connection = CLIChatServer.INSTANCE.network.getConnection(uuid);
            	if (connection == null) continue;
            	
                connection.addChatLine(line);
            }
    	}
	}
	
	public static void broadcast(String line) {
		broadcast(line, null);
	}
	
	public static void message(String name, String message) {
		if (!CLIChatServer.CHAT_PATTERN.matcher(message).matches()) return;
		
		broadcast(getMessage(name, message));
	}
	
	public static String getMessage(String prefix, String message) {
		return prefix + " >> " + message;
	}
}
