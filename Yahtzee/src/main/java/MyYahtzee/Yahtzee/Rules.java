package MyYahtzee.Yahtzee;

import java.util.Random;

public class Rules {

	public int[] rolling(int diceNum) {

		int[] dice = new int[diceNum];

		Random rand = new Random();

		for (int i = 0; i < diceNum; i++) {

			dice[i] = 1 + rand.nextInt(6);

		}

		return dice;
	}
}
