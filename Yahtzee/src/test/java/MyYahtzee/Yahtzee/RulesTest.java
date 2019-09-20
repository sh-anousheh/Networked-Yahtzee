package MyYahtzee.Yahtzee;

import static org.junit.Assert.assertArrayEquals;

import java.util.Map;

import Game1.YahtzeeGame.Rules.box;
import junit.framework.TestCase;

public class RulesTests extends TestCase {

	private Rules ruleClass;

	public RulesTests() {
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
}