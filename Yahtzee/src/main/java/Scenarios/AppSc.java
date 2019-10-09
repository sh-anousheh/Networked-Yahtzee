package Scenarios;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import MyYahtzee.Yahtzee.App;
import MyYahtzee.Yahtzee.Rules;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class AppSc extends TestCase {

	private Rules ruleClass;

	private App app;

	private int[] dice;

	private int[] newDice;

	private String holds;

	private boolean pass;

	public AppSc() {

		app = new App();

		ruleClass = new Rules();
	}

	@Given("Define the default return value")
	public void define_the_default_return_value() {

		pass = true;
	}

	@Then("Validate the outcomes")
	public void validate_the_outcomes() {

		assertEquals(pass, true);

	}

	// __________________________________________________________________________

	@Given("The Dice  {int}, {int}, {int}, {int}, {int}")
	public void the_Dice(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {

		dice = new int[] { int1, int2, int3, int4, int5 };

	}

	@Given("The dice positions to hold are {string}")
	public void the_dice_positions_to_hold_are(String string) {

		holds = string;

		if (holds.isEmpty()) {

			holds = " ";

		}

	}

	@When("Re-rolle the dice")
	public void re_rolle_the_dice() {

		newDice = app.holdSomeDice(holds, dice, ruleClass);
	}

	@When("Check if the new Dice contains {string}")
	public void check_if_the_new_Dice_contains(String string) {

		int i = 0;

		for (char c : string.toCharArray()) {

			if (newDice[i] != dice[Integer.parseInt(String.valueOf(c)) - 1]) {

				pass = false;

				break;

			}

			i++;
		}

	}

}
