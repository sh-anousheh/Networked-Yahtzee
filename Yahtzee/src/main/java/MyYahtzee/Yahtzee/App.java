package MyYahtzee.Yahtzee;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

import MyYahtzee.Yahtzee.Rules.box;

public class App {

	private BufferedReader reader;

	private String Name = "Anousheh";

	public App() {

		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public static void main(String[] args) {

		int round = 1;

		App app = new App();

		Rules game = new Rules();

		String enterKey = "";

		app.drawChart(app.Name, game.getScore(), game.getBonus(), round, game.getFinalDic());

		do {

			enterKey = app.returnStringFor("Press <<ENTER>> to roll the dice ...");

		} while (!enterKey.equals(""));

		int[] dice = game.rolling(5);

		int action = 0;

		int holdCount = 1;

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

	public void drawChart(String name, int score, int bonus, int round, Map<String, Integer> lastDic) {

		System.out.println(
				"_________________________________________________________________________________________________");

		System.out.println(
				String.format("| Name: %s\t\t| Current Score: %d\t\t| Current Round: %d\t\t|", name, score, round));

		System.out.println(
				"|_______________________________|_______________________________|_______________________________|");

		System.out.println(String.format("| (1) Ones: %d\t\t| (2) Twoes: %d\t\t| (3) Threes: %d\t\t|",
				lastDic.get(box.Aces.name()), lastDic.get(box.Twos.name()), lastDic.get(box.Threes.name())));

		System.out.println(
				"|_______________________________|_______________________________|_______________________________|");

		System.out.println(String.format("| (4) Fours: %d\t\t| (5) Fives: %d\t\t| (6) Sixes: %d\t\t|",
				lastDic.get(box.Fours.name()), lastDic.get(box.Fives.name()), lastDic.get(box.Sixes.name())));

		System.out.println(
				"|_______________________________|_______________________________|_______________________________|");

		System.out.println(String.format("| Bonus: %d\t\t\t| (7) Large Straight: %d\t| (8) Small Straight: %d\t|",
				bonus, lastDic.get(box.LargeStraight.name()), lastDic.get(box.SmallStraight.name())));

		System.out.println(
				"|_______________________________|_______________________________|_______________________________|");

		System.out.println(
				String.format("| (9) Full House: %d\t\t| (10) Three of a Kind: %d\t| (11) Four of a Kind: %d\t|",
						lastDic.get(box.FullHouse.name()), lastDic.get(box.ThreeOfAKind.name()),
						lastDic.get(box.FourOfAKind.name())));

		System.out.println(
				"|_______________________________|_______________________________|_______________________________|");

		System.out.println(String.format("| (12) Chance: %d\t\t| (13) Yahtzee: %d\t\t| \t\t\t\t|",
				lastDic.get(box.Chance.name()), lastDic.get(box.Yahtzee.name())));

		System.out.println(
				"|_______________________________|_______________________________|_______________________________|");

	}

}