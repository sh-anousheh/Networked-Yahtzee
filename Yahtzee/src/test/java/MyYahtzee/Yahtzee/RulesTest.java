package MyYahtzee.Yahtzee;

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
}