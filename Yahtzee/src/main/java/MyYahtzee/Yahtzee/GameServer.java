package MyYahtzee.Yahtzee;

import java.io.*;
import java.net.*;

public class GameServer {

	private ServerSocket ss;
	private int numPlayers;
	private ServerSideConnection player1;
	private ServerSideConnection player2;
	private ServerSideConnection player3;
	private int turnsMade;
	private int maxTurns;

	public GameServer() {
		System.out.println("Yahtzee Game Engine has been starting.");
		numPlayers = 0;
		turnsMade = 0;
		maxTurns = 4;

		try {
			ss = new ServerSocket(51734);
		} catch (IOException e) {
			System.out.println("IOException from GameServer constructor");

		}
	}

	public void acceptConnections() {
		try {

			System.out.println("\nWatting for players to join ...");

			while (numPlayers < 3) {
				Socket s = ss.accept();

				numPlayers++;
				ServerSideConnection SSC = new ServerSideConnection(s, numPlayers);
				if (numPlayers == 1) {
					player1 = SSC;
				} else if (numPlayers == 2) {
					player2 = SSC;
				} else {
					player3 = SSC;
				}
				Thread t = new Thread(SSC);
				t.start();
			}

		} catch (IOException ex) {
			System.out.println("IOException from accepting connections()");
		}
	}

	public static void main(String[] args) {
		GameServer gs = new GameServer();
		gs.acceptConnections();
	}

	private class ServerSideConnection implements Runnable {
		private Socket socket;
		private DataInputStream dataIn;
		private DataOutputStream dataOut;
		private int palayerID;

		ServerSideConnection(Socket s, int id) {
			socket = s;
			palayerID = id;
			try {
				dataIn = new DataInputStream(socket.getInputStream());
				dataOut = new DataOutputStream(socket.getOutputStream());

			} catch (IOException e) {
				System.out.println("IOException from run() SSC construction.");
			}
		}

		public void run() {
			try {
				dataOut.writeInt(palayerID);
				dataOut.flush();

				String name = dataIn.readUTF();
				System.out.println(name + " has entered the game lobby!");
				if (numPlayers == 3) {
					System.out.println("The game is starting!");

				}

				while (true) {
					if (player1 != null && player2 != null && player3 != null) {
						if (palayerID == 1) {
							String chart = dataIn.readUTF();
							player2.sendchart(chart);
							player3.sendchart(chart);
						} else if (palayerID == 2) {
							String chart = dataIn.readUTF();
							player1.sendchart(chart);
							player3.sendchart(chart);
						} else {
							String chart = dataIn.readUTF();
							player2.sendchart(chart);
							player1.sendchart(chart);
						}
					}
				}
			} catch (IOException e) {
				System.out.println("IOException from run() SSC");
			}
		}

		public void sendchart(String chart) {
			try {
				dataOut.writeUTF(chart);
				dataOut.flush();
			} catch (IOException e) {
				System.out.println("IOException from sendcharts() SSC");
			}

		}
	}
}
