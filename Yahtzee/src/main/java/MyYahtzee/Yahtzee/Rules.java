package MyYahtzee.Yahtzee;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Rules {

	private Map<String, Integer> initDic;

	private Map<String, Integer> finalDic;

	private int bonus;

	private int score;

	public int getBonus() {

		return bonus;
	}

	public int getScore() {

		return score;

	}

	public Map<String, Integer> getFinalDic() {

		return finalDic;
	}

	public Rules() {

		score = 0;

		bonus = 0;

		initDic = new HashMap<String, Integer>();

		finalDic = new HashMap<String, Integer>();

		for (box b : box.values()) {

			initDic.put(b.name(), 0);
		}
	}

	public enum box {
		Aces, Twos, Threes, Fours, Fives, Sixes, ThreeOfAKind, FourOfAKind, FullHouse, SmallStraight, LargeStraight,
		Yahtzee, Chance
	}

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
