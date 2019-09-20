package MyYahtzee.Yahtzee;

import java.util.HashMap;
import java.util.Map;
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

	public Map<Integer, Integer> countDice(int[] Dice) {

		Map<Integer, Integer> countDic = new HashMap<Integer, Integer>();

		for (int i = 1; i <= 6; i++) {

			countDic.put(i, 0);
		}

		for (int i = 0; i < 5; i++) {

			countDic.replace(Dice[i], countDic.get(Dice[i]) + 1);
		}

		return countDic;
	}

}
