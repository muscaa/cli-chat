package muscaa.clichat.client.main;

import java.util.Scanner;

import muscaa.clichat.client.CLIChatClient;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		
		CLIChatClient.INSTANCE.start(s);
		
		s.close();
	}
}
