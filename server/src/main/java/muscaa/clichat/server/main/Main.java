package muscaa.clichat.server.main;

import muscaa.clichat.server.CLIChatServer;
import muscaa.clichat.shared.utils.Utils;

public class Main {
	
	public static void main(String[] args) throws Exception {
        CLIChatServer.INSTANCE.start();
        Utils.TERMINAL.close();
	}
}
