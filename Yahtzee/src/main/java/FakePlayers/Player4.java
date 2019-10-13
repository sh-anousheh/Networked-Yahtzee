package FakePlayers;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import MyYahtzee.Yahtzee.Rules;
import Scenarios.Level3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Player4 {

	private BufferedReader reader;

	private int maxRound;

	private String name = "";

	private ClientSideConnection csc;

	int[][] categories = new int[3][];

	private int playerID;

	String[] cat;

	Map<Integer, String> res;

	public Player4() {

		res = new HashMap<Integer, String>();

		categories[0] = new int[] { 13, 6, 8, 3, 9, 1, 10, 11, 5, 4, 7, 12, 2 };

		categories[1] = new int[] { 7, 4, 3, 12, 13, 8, 9, 1, 10, 5, 11, 2, 6 };

		categories[2] = new int[] { 9, 13, 5, 8, 2, 4, 3, 1, 10, 7, 12, 11, 6 };

		cat = "Aces,Twos,Threes,Fours,Fives,Sixes,LargeStraight,SmallStraight,FullHouse,ThreeOfAKind,FourOfAKind,Chance,Yahtzee"
				.split(",");

		maxRound = 13;

		reader = new BufferedReader(new InputStreamReader(System.in));

	}

	public static void main(String[] args) {

		Player4 app = new Player4();

		try {

			app.name = args[0];

		} catch (Exception e) {

		}

		app.connectToServer();

		app.startRecivingInfo();

	}

	public void startRecivingInfo() {

		Thread T = new Thread(new Runnable() {

			public void run() {

				Rules game = new Rules();

				int round = 1;

				int turn = 1;

				boolean conti = true;

				while (true) {

					if (conti) {

						if (turn == playerID) {

							readnWriteChart(name, game, round);
							if (round < maxRound) {

								round++;

								play(game, round);

								turn++;

								if (turn == 4) {

									turn = 1;
								}

								csc.sendToServer(String.valueOf(conti));

								csc.sendToServer(String.valueOf(turn));

								// csc.sendToServer("Player " + playerID + " has completed the game.");

							} else if (round == maxRound) {

								round++;

								play(game, round);

								turn++;

								if (turn == 4) {

									turn = 1;
								}

								conti = false;

								csc.sendToServer(String.valueOf(conti));

								csc.sendToServer(String.valueOf(turn));

								// csc.sendToServer("Player " + playerID + " has completed the game.");

							}

						}

						turn = Integer.parseInt(csc.recieveFromServer());

					}

					else {

						if (turn == 2) {

							turn = Integer.parseInt(csc.recieveFromServer());

							turn = Integer.parseInt(csc.recieveFromServer());

						} else if (turn == 3) {

							turn = Integer.parseInt(csc.recieveFromServer());

						}

						readnWriteChart(name, game, round);

						csc.sendToServer(String.valueOf(game.getScore()));

					}

				}

			}
		});

		T.start();
	}

	private void connectToServer() {

		csc = new ClientSideConnection();
	}

	private class ClientSideConnection {

		private Socket socket;

		private DataInputStream dataIn;

		private DataOutputStream dataOut;

		public ClientSideConnection() {

			try {

				socket = new Socket("localhost", 51734);

				dataIn = new DataInputStream(socket.getInputStream());

				dataOut = new DataOutputStream(socket.getOutputStream());

				playerID = dataIn.readInt();

				if (name.isEmpty()) {

					name = returnStringFor("Welcome player " + playerID + ", please enter your name:");
				}

				sendToServer(name);

			} catch (IOException ex) {

				System.out.println("IO Exception from CSC construction");
			}

		}

		public void sendToServer(String res) {

			try {

				dataOut.writeUTF(res);

				dataOut.flush();

			} catch (IOException e) {

			}
		}

		public String recieveFromServer() {

			String res = "";

			try {

				res = dataIn.readUTF();

			} catch (IOException e) {

				System.out.println("IOException from recieveFromServer() CSC");
			}

			return res;
		}
	}

	public void play(Rules game, int round) {

		int[] dice = game.rolling(5);

		int action = 0;

		int holdCount = 1;

		do {

			Arrays.sort(dice);

			action = 3;

			switch (action) {

			case 1:

				String line = returnStringFor("Please enter in the dice position that you want to hold."
						+ " Please seperate each number with a <<SPACE>>:");

				dice = holdSomeDice(line, dice, game);

				holdCount++;

				break;

			case 2:

				dice = game.rolling(5);

				holdCount++;

				break;

			}

		} while (action <= 2 && holdCount < 3);

		while (true) {

			int ChoosenCat = categories[playerID - 1][round - 2];

			boolean isvalid = game.play(dice, ChoosenCat);

			if (isvalid) {
				break;
			}

			else {
				System.out.println("This category is already scored!");
			}
		}

	}

	public int[] holdSomeDice(String line, int[] dice, Rules game) {

		String[] position = line.split(" ");

		int[] reRoled = game.rolling(5 - position.length);

		int[] newDice = new int[5];

		for (int i = 0; i < position.length; i++) {

			newDice[i] = dice[Integer.parseInt(position[i]) - 1];
		}

		for (int i = 0; i < reRoled.length; i++) {

			newDice[4 - i] = reRoled[i];
		}

		return newDice;
	}

	public String returnStringFor(String input) {

		System.out.println(input);

		String res = "";

		try {

			res = reader.readLine();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return res;
	}

	public int returnIntFor(String input, int max) {

		System.out.println(input);

		int res = 0;

		try {

			res = Integer.parseInt(reader.readLine());

			if (res < 1 || res > max) {

				throw new NumberFormatException();
			}

		} catch (NumberFormatException e) {

			System.out.println("Input is not valid!");

			return returnIntFor(input, max);

		} catch (IOException e) {

		}

		return res;
	}

	public void readnWriteChart(String name, Rules game, int round) {

		String chart = "";

		if (round >= 2) {
			chart = String.valueOf(playerID) + "," + name + "," + (round - 1) + ","
					+ String.valueOf(categories[playerID - 1][round - 2]) + ","
					+ cat[categories[playerID - 1][round - 2] - 1] + ","
					+ game.getFinalDic().get(cat[categories[playerID - 1][round - 2] - 1]) + "," + game.getScore();

			if (!res.containsKey(round - 1)) {

				res.put(round - 1, chart);

				// System.out.println(String.valueOf(chart));

				Level3.writer.println(String.format("%02d", ++Level3.rowNum) + "," + chart);

			}

		}

	}

}