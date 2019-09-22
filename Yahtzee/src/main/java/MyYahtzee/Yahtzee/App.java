package MyYahtzee.Yahtzee;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

import MyYahtzee.Yahtzee.Rules.box;
import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.*;

public class App {

	private BufferedReader reader;

	private String name;

	private ClientSideConnection csc;

	private int playerID;

	// private int maxTurns;

	public App() {

		reader = new BufferedReader(new InputStreamReader(System.in));

	}

	public static void main(String[] args) {

		App app = new App();

		app.connectToServer();

		Rules game = new Rules();

		for (int round = 1; round <= 13; round++) {

			String enterKey = "";

			System.out.println(app.Chart(app.name, game.getScore(), game.getBonus(), round, game.getFinalDic()));

			do {

				enterKey = app.returnStringFor("Press <<ENTER>> to roll the dice ...");

			} while (!enterKey.equals(""));

			int[] dice = game.rolling(5);

			int action = 0;

			int holdCount = 1;

			do {

				Arrays.sort(dice);

				System.out.println(String.format("Your rolled:\t|%d|\t|%d|\t|%d|\t|%d|\t|%d|", dice[0], dice[1],
						dice[2], dice[3], dice[4]));

				action = app.returnIntFor("What action do you like to perform next?"
						+ "\n(1) Select dice to hold and then re-roll the other dice?" + "\n(2) Re-roll all the dice?"
						+ "\n(3) Score this round?", 3);

				switch (action) {

				case 1:

					String line = app.returnStringFor("Please enter in the dice position that you want to hold."
							+ " Please seperate each number with a <<SPACE>>:");

					dice = app.holdSomeDice(line, dice, game);

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

				int ChoosenCat = app.returnIntFor("What category do you want to score this round againts?", 13);

				boolean isvalid = game.play(dice, ChoosenCat);

				if (isvalid) {
					break;
				}

				else {
					System.out.println("This category is already scored!");
				}
			}
		}
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
				System.out.println("Welcome player " + playerID + ", please enter your name:");
				name = reader.readLine();
				dataOut.writeUTF(name);

			} catch (IOException ex) {
				System.out.println("IO Exception from CSC construction");
			}

		}

		public void sendResult(String res) {
			try {
				dataOut.writeUTF(res);
				dataOut.flush();
			} catch (IOException e) {
				System.out.println("IOException from sendResult() CSC");
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

	public String Chart(String name, int score, int bonus, int round, Map<String, Integer> lastDic) {

		String res = "\n_________________________________________________________________________________________________"
				+ String.format("\n| Name: %s\t\t| Current Score: %d\t\t| Current Round: %d\t\t|", name, score, round)
				+ "\n|_______________________________|_______________________________|_______________________________|"
				+ String.format("\n| (1) Ones: %d\t\t| (2) Twoes: %d\t\t| (3) Threes: %d\t\t|",
						lastDic.get(box.Aces.name()), lastDic.get(box.Twos.name()), lastDic.get(box.Threes.name()))
				+ "\n|_______________________________|_______________________________|_______________________________|"
				+ String.format("\n| (4) Fours: %d\t\t| (5) Fives: %d\t\t| (6) Sixes: %d\t\t|",
						lastDic.get(box.Fours.name()), lastDic.get(box.Fives.name()), lastDic.get(box.Sixes.name()))
				+ "\n|_______________________________|_______________________________|_______________________________|"
				+ String.format("\n| Bonus: %d\t\t\t| (7) Large Straight: %d\t| (8) Small Straight: %d\t|", bonus,
						lastDic.get(box.LargeStraight.name()), lastDic.get(box.SmallStraight.name()))
				+ "\n|_______________________________|_______________________________|_______________________________|"
				+ String.format("\n| (9) Full House: %d\t\t| (10) Three of a Kind: %d\t| (11) Four of a Kind: %d\t|",
						lastDic.get(box.FullHouse.name()), lastDic.get(box.ThreeOfAKind.name()),
						lastDic.get(box.FourOfAKind.name()))
				+ "\n|_______________________________|_______________________________|_______________________________|"
				+ String.format("\n| (12) Chance: %d\t\t| (13) Yahtzee: %d\t\t| \t\t\t\t|",
						lastDic.get(box.Chance.name()), lastDic.get(box.Yahtzee.name()))
				+ "\n|_______________________________|_______________________________|_______________________________|";

		return res;
	}

}