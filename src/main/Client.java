package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	static int port = 0;
	static boolean running = true;
	public static Socket socket;
	public static PrintWriter out;
	public static BufferedReader in;
	public static BufferedReader sysIn;

	public static void main(String[] args) {

		try {

			sysIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Host IP: ");
			String hostIP = sysIn.readLine();
			if (hostIP == "" || hostIP == null) {
				hostIP = "192.168.0.16";
			}
			System.out.print("Port: ");
			port = Integer.parseInt(sysIn.readLine());
			socket = new Socket(hostIP, port);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine = "";

			while (running) {

				// SEND
				if (sysIn.ready()) {
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
			e.printStackTrace();
		}

	}

	public static void quit() throws IOException {
		running = false;
		socket.close();
		out.close();
		in.close();
		sysIn.close();
	}

}
