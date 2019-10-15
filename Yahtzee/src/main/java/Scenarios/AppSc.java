package Scenarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import FakePlayers.Player1;
import MyYahtzee.Yahtzee.App;
import MyYahtzee.Yahtzee.Rules;
import MyYahtzee.Yahtzee.Rules.box;
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

		if (!string.isEmpty())

		{
			for (String c : string.split(" ")) {

				if (newDice[i] != Integer.parseInt(String.valueOf(c))) {

					pass = false;

					break;

				}

				i++;
			}
		}

	}

	@Then("Validate the outcomes")
	public void validate_the_outcomes() {

		assertEquals(pass, true);

	}
	// __________________________________________________________________________

	private Player1 player;

	private List<Integer> actions;

	private Map<Integer, String> holdList;

	private int expectedHolds;

	String acceptance;

	@Given("A player start the game")
	public void a_player_start_the_game() {

		acceptance = "Accepted";

		actions = new ArrayList<Integer>();

		expectedHolds = 0;

		player = new Player1();

	}

	@Given("Choose the {string} to perform respectively")
	public void choose_the_to_perform_respectively(String string) {

		String[] input = string.split(",");

		for (String s : input) {

			int i = Integer.parseInt(s);

			actions.add(i);

			if (i == 1 || i == 2) {

				expectedHolds++;
			}
		}

	}

	@When("The dice positions to hold for the fist and second holds are {string} and {string} respectively")
	public void the_dice_positions_to_hold_for_the_fist_and_second_holds_are_and_respectively(String string,
			String string2) {

		holdList = new HashMap<Integer, String>();

		if (string != null) {

			holdList.put(1, string);

			if (string2 != null) {

				holdList.put(2, string2);
			}
		}

	}

	@When("Check if the the number of holds that have been make is correct")
	public void check_if_the_the_number_of_holds_that_have_been_make_is_correct() {

		int res = player.play(ruleClass, 1, actions, holdList);

		if (Integer.compare(res, expectedHolds) != 0) {

			acceptance = "Notaccepted";
		}

	}

	@When("Scoring in the chance catetory is have been made or not")
	public void scoring_in_the_chance_catetory_is_have_been_made_or_not() {

		if (ruleClass.getFinalDic().get(box.Chance.name()) == null) {

			acceptance = "Notaccepted";
		}

	}

	@Then("Check if the {string} of the test")
	public void check_if_the_of_the_test(String string) {

		if (!string.equals(acceptance)) {
			pass = false;
		}
	}

	@Then("Check the Correctness")
	public void check_the_Correctness() {

		assertEquals(pass, true);

	}
}
