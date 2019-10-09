package MyYahtzee.Yahtzee;

import java.util.*;
import java.util.stream.Collectors;

import junit.framework.TestCase;

public class AppTest extends TestCase {

	private App app;

	public AppTest() {
		app = new App();
	}

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