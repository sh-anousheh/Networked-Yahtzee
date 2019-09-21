package MyYahtzee.Yahtzee;

import java.util.HashMap;
import java.util.Map;
import MyYahtzee.Yahtzee.Rules.box;
import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;

public class RulesTest extends TestCase {

	private Rules ruleClass;

	public RulesTest() {

		ruleClass = new Rules();
	}

	public void testRolling() {

		boolean pass = true;

		for (int i = 0; i < 100; i++) {

			int[] dices = ruleClass.rolling(1);

			if (!(dices[0] <= 6 && dices[0] >= 1)) {

				pass = false;
			}
		}

		assertEquals(true, pass);
	}

	public void testCountDice() {

		int[] testDice = new int[] { 3, 2, 6, 6, 2 };

		Map<Integer, Integer> counter = ruleClass.countDice(testDice);

		assertArrayEquals(counter.values().toArray(), new Object[] { 0, 2, 1, 0, 0, 2 });
	}

	public void testOnes() {

		ruleClass.play(new int[] { 1, 4, 6, 1, 1 }, 1);

		assertEquals((Integer) 3, ruleClass.getFinalDic().get(box.Aces.name()));

	}

	public void testTwos() {

		ruleClass.play(new int[] { 1, 2, 6, 4, 1 }, 2);

		assertEquals((Integer) 1, ruleClass.getFinalDic().get(box.Twos.name()));

	}

	public void testThrees() {

		ruleClass.play(new int[] { 3, 2, 3, 3, 3 }, 3);

		assertEquals((Integer) 4, ruleClass.getFinalDic().get(box.Threes.name()));

	}

	public void testFours() {

		ruleClass.play(new int[] { 4, 2, 4, 3, 3 }, 4);

		assertEquals((Integer) 2, ruleClass.getFinalDic().get(box.Fours.name()));

	}

	public void testFives() {

		ruleClass.play(new int[] { 4, 5, 5, 5, 3 }, 5);

		assertEquals((Integer) 3, ruleClass.getFinalDic().get(box.Fives.name()));

	}

	public void testSixes() {

		ruleClass.play(new int[] { 6, 5, 5, 6, 3 }, 6);

		assertEquals((Integer) 2, ruleClass.getFinalDic().get(box.Sixes.name()));

	}

	public void testThreeOfAKind() {

		int[] Dice = new int[] { 6, 2, 3, 2, 2 };

		int sum = 0;

		for (int i : Dice) {

			sum += i;
		}

		ruleClass.play(Dice, 10);

		assertEquals((Integer) sum, ruleClass.getFinalDic().get(box.ThreeOfAKind.name()));

	}

	public void testFullHouse() {

		int[] Dice = new int[] { 5, 2, 5, 2, 5 };

		ruleClass.play(Dice, 9);

		assertEquals((Integer) 25, ruleClass.getFinalDic().get(box.FullHouse.name()));

	}

	public void testChance() {

		int[] Dice = new int[] { 3, 6, 3, 2, 5 };

		int sum = 0;

		for (int i : Dice) {

			sum += i;
		}

		ruleClass.play(Dice, 12);

		assertEquals((Integer) sum, ruleClass.getFinalDic().get(box.Chance.name()));

	}

	public void testFourOfAKind() {

		int[] Dice = new int[] { 5, 5, 5, 2, 5 };

		int sum = 0;

		for (int i : Dice) {

			sum += i;
		}

		ruleClass.play(Dice, 11);

		assertEquals((Integer) sum, ruleClass.getFinalDic().get(box.FourOfAKind.name()));

	}

	public void testYahtzee() {

		int[] Dice = new int[] { 4, 4, 4, 4, 4 };

		ruleClass.play(Dice, 13);

		assertEquals((Integer) 50, ruleClass.getFinalDic().get(box.Yahtzee.name()));

	}

	public void testSmallStraight() {

		int[] Dice = new int[] { 6, 2, 5, 4, 3 };

		ruleClass.play(Dice, 8);

		assertEquals((Integer) 30, ruleClass.getFinalDic().get(box.SmallStraight.name()));

	}

	public void testSmallStraight2() {

		int[] Dice = new int[] { 4, 2, 5, 4, 3 };

		ruleClass.play(Dice, 8);

		assertEquals((Integer) 30, ruleClass.getFinalDic().get(box.SmallStraight.name()));

	}

	public void testLargeStraight1() {

		int[] Dice = new int[] { 4, 2, 1, 5, 3 };

		ruleClass.play(Dice, 7);

		assertEquals((Integer) 40, ruleClass.getFinalDic().get(box.LargeStraight.name()));

	}

	public void testLargeStraight2() {

		int[] Dice = new int[] { 4, 2, 6, 5, 3 };

		ruleClass.play(Dice, 7);

		assertEquals((Integer) 40, ruleClass.getFinalDic().get(box.LargeStraight.name()));

	}

	public void testCalculateBonus() {

		Map<String, Integer> scores = new HashMap<String, Integer>() {
			{
				put(box.Aces.name(), 3);
				put(box.Twos.name(), 8);
				put(box.Threes.name(), 3);
				put(box.Fours.name(), 16);
				put(box.Fives.name(), 15);
				put(box.Sixes.name(), 30);
			}
		};

		assertEquals(35, ruleClass.calculateBonus(scores));

	}

	public void testCalculateBonus2() {

		Map<String, Integer> scores = new HashMap<String, Integer>() {
			{
				put(box.Aces.name(), 1);
				put(box.Twos.name(), 0);
				put(box.Threes.name(), 3);
				put(box.Fours.name(), 8);
				put(box.Fives.name(), 15);
				put(box.Sixes.name(), 6);
			}
		};

		assertEquals(0, ruleClass.calculateBonus(scores));

	}
}