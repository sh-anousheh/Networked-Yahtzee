package MyYahtzee.Yahtzee;

import java.io.*;
import java.net.*;

public class GameServer {

	private ServerSocket ss;

	private int numPlayers;

	private ServerSideConnection player1;

	private ServerSideConnection player2;

	private ServerSideConnection player3;

	public GameServer() {

		System.out.println("Yahtzee Game Engine has been starting.");

		numPlayers = 0;

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

		private String turn;

		private String name = "";

		ServerSideConnection(Socket s, int id) {

			socket = s;

			palayerID = id;

			turn = "1";

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

				name = dataIn.readUTF();

				System.out.println(name + " has entered the game lobby!");

				if (numPlayers == 3) {

					System.out.println("The game is starting!");

				}

				Boolean IsEnd = false;

				while (!IsEnd) {

					if (player1 != null && player2 != null && player3 != null) {

						String chart1 = player1.dataIn.readUTF();

						String chart2 = player2.dataIn.readUTF();

						String chart3 = player3.dataIn.readUTF();

						player1.sendToClient(chart2);

						player1.sendToClient(chart3);

						player2.sendToClient(chart1);

						player2.sendToClient(chart3);

						player3.sendToClient(chart1);

						player3.sendToClient(chart2);

						if (Integer.parseInt(turn) == 1) {

							turn = player1.dataIn.readUTF();

						} else if (Integer.parseInt(turn) == 2) {

							turn = player2.dataIn.readUTF();

						} else {

							IsEnd = Boolean.valueOf(player3.dataIn.readUTF());

							turn = player3.dataIn.readUTF();

						}

						player1.sendToClient(turn);

						player2.sendToClient(turn);

						player3.sendToClient(turn);

					}
				}

				int score1 = Integer.parseInt(player1.dataIn.readUTF());

				int score2 = Integer.parseInt(player1.dataIn.readUTF());

				int score3 = Integer.parseInt(player1.dataIn.readUTF());

				String res = "";

				if (score1 > score2 && score1 > score3) {
					res = "congratulations, " + player1.name + " has won the game with a score of " + score1
							+ " points";
				} else if (score2 > score1 && score2 > score3) {
					res = "congratulations, " + player2.name + " has won the game with a score of " + score1
							+ " points";
				} else {
					res = "congratulations, " + player3.name + " has won the game with a score of " + score1
							+ " points";
				}

				res += "\nGreat game everyone, and thanks for playing. Goodbye.";

				player1.sendToClient(res);

				player1.sendToClient(res);

				player1.sendToClient(res);

			} catch (IOException e) {

				System.out.println("IOException from run() SSC");
			}
		}

		public void sendToClient(String chart) {

			try {

				dataOut.writeUTF(chart);

				dataOut.flush();

			} catch (IOException e) {

				System.out.println("IOException from sendcharts() SSC");
			}

		}
	}
}
