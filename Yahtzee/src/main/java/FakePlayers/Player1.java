package FakePlayers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import MyYahtzee.Yahtzee.Rules;

public class Player1 {

	private BufferedReader reader;

	private int maxRound;

	private String name = "";

	private ClientSideConnection csc;

	private int playerID;

	public Player1() {

		reader = new BufferedReader(new InputStreamReader(System.in));

	}

	public static void main(String[] args) {

		Player1 app = new Player1();

		try {

			app.name = args[0];

		} catch (Exception e) {

			System.out.println("Erroe in Main");
		}

		app.connectToServer();

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

				socket = new Socket("localhost", 51731);

				dataIn = new DataInputStream(socket.getInputStream());

				dataOut = new DataOutputStream(socket.getOutputStream());

				playerID = dataIn.readInt();

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

				System.out.println("IOException from sendToServer() CSC");
			}
		}
	}

	public int play(Rules game, int round, List<Integer> actions, Map<Integer, String> holds) {

		String enterKey = "";

		/*
		 * do {
		 * 
		 * enterKey = returnStringFor("Press <<ENTER>> to roll the dice ...");
		 * 
		 * } while (!enterKey.equals(""));
		 */

		int[] dice = game.rolling(5);

		int action = 0;

		int holdCount = 1;

		int i = 0;

		int j = 1;

		do {

			Arrays.sort(dice);

			System.out.println(String.format("Your rolled:\t|%d|\t|%d|\t|%d|\t|%d|\t|%d|", dice[0], dice[1], dice[2],
					dice[3], dice[4]));

			/*
			 * action = returnIntFor("What action do you like to perform next?" +
			 * "\n(1) Select dice to hold and then re-roll the other dice?" +
			 * "\n(2) Re-roll all the dice?" + "\n(3) Score this round?", 3);
			 */

			action = actions.get(i);

			i++;

			switch (action) {

			case 1:

				/*
				 * String line =
				 * returnStringFor("Please enter in the dice position that you want to hold." +
				 * " Please seperate each number with a <<SPACE>>:");
				 */

				String line = holds.get(j);

				j++;

				dice = holdSomeDice(line, dice, game);

				holdCount++;

				break;

			case 2:

				dice = game.rolling(5);

				holdCount++;

				break;

			}

		} while (action <= 2 && holdCount < 3);

		Arrays.sort(dice);

		System.out.println(String.format("Your rolled:\t|%d|\t|%d|\t|%d|\t|%d|\t|%d|", dice[0], dice[1], dice[2],
				dice[3], dice[4]));

		while (true) {

			int ChoosenCat = 12;

			// ChoosenCat = returnIntFor("What category do you want to score this round
			// againts?", 13);

			boolean isvalid = game.play(dice, ChoosenCat);

			if (isvalid) {
				break;
			}

			else {
				System.out.println("This category is already scored!");
			}
		}

		return holdCount - 1;
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

}
