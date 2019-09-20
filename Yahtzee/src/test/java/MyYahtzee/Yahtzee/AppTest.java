package MyYahtzee.Yahtzee;

import java.util.*;
import java.util.stream.Collectors;

import junit.framework.TestCase;

public class AppTest extends TestCase {

	private App app;

	public AppTest() {
		app = new App();
	}

	/*
	 * public void testReturnIntFor() {
	 * 
	 * int res = app.returnIntFor("Write input between 1 and 6", 6);
	 * 
	 * assertTrue(res > 0 && res <= 6); }
	 */

	public void testHoldSomeDice() {

		Random rand = new Random();

		List<Object> randomNumbers = rand.ints(1, 6).distinct().limit(4).boxed().collect(Collectors.toList());

		forHoldSomeDice(randomNumbers);
	}

	public void forHoldSomeDice(List<Object> holds) {

		String input = "";

		for (Object o : holds) {

			input += o.toString() + " ";
		}

		Rules game = new Rules();

		int[] dice = game.rolling(5);

		int[] newDice = app.holdSomeDice(input, dice, game);

		boolean pass = true;

		/*
		 * for (int i = 0; i < 5; i++) {
		 * 
		 * System.out.print(dice[i]); }
		 * 
		 * System.out.println();
		 * 
		 * for (int i = 0; i < 5; i++) {
		 * 
		 * System.out.print(newDice[i]); }
		 * 
		 * System.out.println(holds.toString());
		 */
		int i = 0;

		for (Object o : holds) {

			if (newDice[i] != dice[Integer.parseInt(o.toString()) - 1]) {

				pass = false;

				break;

			}

			i++;
		}

		assertEquals(true, pass);
	}

}