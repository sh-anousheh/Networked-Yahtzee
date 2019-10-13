package FakeServers;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server4 {

	private ServerSocket ss;

	private int numPlayers;

	private ServerSideConnection player1;

	private ServerSideConnection player2;

	private ServerSideConnection player3;

	private List<String> playersList;

	public List<String> getPlayers() {

		return playersList;

	}

	public Server4() {

		playersList = new ArrayList<String>();

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

			System.out.println("\nWaiting for players to join ...");

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

			ss.close();

		} catch (IOException ex) {

			System.out.println("IOException from accepting connections()");

		}
	}

	public static void main(String[] args) {

		Server4 gs = new Server4();

		gs.acceptConnections();
	}

	private class ServerSideConnection implements Runnable {

		private Socket socket;

		private DataInputStream dataIn;

		private DataOutputStream dataOut;

		private int palayerID;

		private String turn;

		private String name;

		ServerSideConnection(Socket s, int id) {

			name = "";

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

				playersList.add(name);

				if (numPlayers == 3)

					if (!player1.name.equals("") && !player2.name.equals("") && !player3.name.equals("")) {

						System.out.println("\nThe game is starting!\n");

						Boolean conti1 = true;

						Boolean conti2 = true;

						Boolean conti3 = true;

						while (true) {

							if (player1 != null && player2 != null && player3 != null) {

								if (Integer.parseInt(turn) == 1) {

									conti1 = Boolean.valueOf(player1.dataIn.readUTF());

									turn = player1.dataIn.readUTF();

									// System.out.println(player1.dataIn.readUTF());

								} else if (Integer.parseInt(turn) == 2) {

									conti2 = Boolean.valueOf(player2.dataIn.readUTF());

									turn = player2.dataIn.readUTF();

									// System.out.println(player2.dataIn.readUTF());

								} else {

									conti3 = Boolean.valueOf(player3.dataIn.readUTF());

									turn = player3.dataIn.readUTF();

									// System.out.println(player3.dataIn.readUTF() + "\n");
								}

								player1.sendToClient(turn);

								player2.sendToClient(turn);

								player3.sendToClient(turn);

								if (!conti1 && !conti2 && !conti3) {

									System.out.println("\nGame Compplete.");

									int score1 = Integer.parseInt(player1.dataIn.readUTF());

									int score2 = Integer.parseInt(player2.dataIn.readUTF());

									int score3 = Integer.parseInt(player3.dataIn.readUTF());

									String res = "";

									if (score1 >= score2 && score1 >= score3) {
										res = "\ncongratulations, " + player1.name
												+ " has won the game with a score of " + score1 + " points";
									} else if (score2 >= score1 && score2 >= score3) {
										res = "\ncongratulations, " + player2.name
												+ " has won the game with a score of " + score2 + " points";
									} else {
										res = "\ncongratulations, " + player3.name
												+ " has won the game with a score of " + score3 + " points";
									}

									res += "\nGreat game everyone, and thanks for playing. Goodbye.";

									System.out.println(res);

									break;
								}
							}
						}
					}
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
