package muscaa.clichat.client.main;

import muscaa.clichat.client.CLIChatClient;
import muscaa.clichat.shared.utils.Utils;

public class Main {
	
	public static void main(String[] args) throws Exception {
        CLIChatClient.INSTANCE.start();
        Utils.TERMINAL.close();
	}
}
