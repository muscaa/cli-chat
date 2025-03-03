package muscaa.clichat.server.main;

import java.util.Scanner;

import muscaa.clichat.server.CLIChatServer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		
        CLIChatServer.INSTANCE.start(s);
		
		s.close();
	}
}
