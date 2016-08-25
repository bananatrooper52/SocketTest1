package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String args[]) {

		int port = 0;
		
		try {

			BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
			port = Integer.parseInt(sysIn.readLine());
			ServerSocket serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String inputLine;
			while((inputLine = in.readLine()) != null) {
				out.println(inputLine);
			}
			
			serverSocket.close();
			clientSocket.close();
			in.close();
			sysIn.close();
			out.close();

		} catch (IOException e) {
			System.err.println("Exception on port: " + port);
			e.printStackTrace();
		}

	}

}
