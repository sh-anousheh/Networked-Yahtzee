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
		Aces, Twos, Threes, Fours, Fives, Sixes, LargeStraight, SmallStraight, FullHouse, ThreeOfAKind, FourOfAKind,
		Chance, Yahtzee
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

	public boolean play(int[] dice, int choosen) {

		boolean res = false;

		Map<Integer, Integer> numCount = countDice(dice);

		upperSection(numCount);

		lowerSection(numCount);

		for (box b : box.values()) {

			if (b.ordinal() + 1 == choosen) {

				finalDic.put(b.name(), initDic.get(b.name()));

				score +=

						initDic.remove(b.name());

				return true;
			}
		}
		return res;

	}

	private void upperSection(Map<Integer, Integer> numCount) {

		initDic.replace(box.Aces.name(), numCount.get(1));

		initDic.replace(box.Twos.name(), numCount.get(2));

		initDic.replace(box.Threes.name(), numCount.get(3));

		initDic.replace(box.Fours.name(), numCount.get(4));

		initDic.replace(box.Fives.name(), numCount.get(5));

		initDic.replace(box.Sixes.name(), numCount.get(6));
	}

	private void lowerSection(Map<Integer, Integer> numCount) {

		int sum = 0;

		for (int i = 1; i <= numCount.size(); i++) {

			sum += i * numCount.get(i);
		}

		initDic.replace(box.Chance.name(), sum);

		for (Integer c : numCount.values()) {

			if (c == 3) {

				initDic.replace(box.ThreeOfAKind.name(), sum);

				for (Integer cc : numCount.values()) {

					if (cc == 2) {

						initDic.replace(box.FullHouse.name(), 25);

						break;
					}
				}
			}

			else if (c >= 4) {

				initDic.replace(box.ThreeOfAKind.name(), sum);

				initDic.replace(box.FourOfAKind.name(), sum);

				if (c == 5) {

					if (initDic.get(box.Yahtzee.name()) == 0) {

						initDic.replace(box.Yahtzee.name(), 50);

					}

					else {

						initDic.replace(box.Yahtzee.name(), initDic.get(box.Yahtzee.name() + 100));

					}

				}

			}
			if (numCount.get(1) > 0 && numCount.get(2) > 0 && numCount.get(3) > 0 && numCount.get(4) > 0) {

				initDic.replace(box.SmallStraight.name(), 30);

				if (numCount.get(5) == 1) {

					initDic.replace(box.LargeStraight.name(), 40);
				}
			}

			else if (numCount.get(2) > 0 && numCount.get(3) > 0 && numCount.get(4) > 0 && numCount.get(5) > 0) {

				initDic.replace(box.SmallStraight.name(), 30);

				if (numCount.get(6) == 1) {

					initDic.replace(box.LargeStraight.name(), 40);
				}

			}

		}

	}
}
