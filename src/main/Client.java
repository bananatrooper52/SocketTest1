package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

public static void main(String[] args) {
		
	int port = 0;
	
		try {
			
			BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
			String hostIP = sysIn.readLine();
			port = Integer.parseInt(sysIn.readLine());
			Socket socket = new Socket(hostIP, port);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String userInput;
			while ((userInput = sysIn.readLine()) != null) {
			    out.println(userInput);
			    System.out.println("echo: " + in.readLine());
			}
			
			socket.close();
			out.close();
			in.close();
			sysIn.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
