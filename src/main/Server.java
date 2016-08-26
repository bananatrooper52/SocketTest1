package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	static ServerSocket serverSocket;
	static Socket clientSocket;
	static PrintWriter out;
	static BufferedReader in, sysIn;
	static boolean running = true;

	public static void main(String args[]) {

		int port = 0;

		try {

			sysIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Port: ");
			port = Integer.parseInt(sysIn.readLine());
			serverSocket = new ServerSocket(port);
			System.out.println("Connected on port " + port + ", waiting for client to join...");
			clientSocket = serverSocket.accept();
			System.out.println("Client joined");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String inputLine = "";

			while (running) {

				// SEND
				
				if(sysIn.ready()) {
					inputLine = sysIn.readLine();
					out.println(inputLine);
				}

				// RECEIVE
				if (in.ready())
					System.out.println(in.readLine());

				// DO COMMANDS
				if (inputLine.toLowerCase() == "/exit") {
					quit();
				}
				
			}

		} catch (IOException e) {
			System.err.println("Exception on port: " + port);
			e.printStackTrace();
		}

	}

	public static void quit() throws IOException {

		running = false;

		serverSocket.close();
		clientSocket.close();
		in.close();
		sysIn.close();
		out.close();

		System.exit(0);
	}

}
