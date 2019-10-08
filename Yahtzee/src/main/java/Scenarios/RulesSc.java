package Scenarios;

import java.util.HashMap;
import java.util.Map;

import MyYahtzee.Yahtzee.Rules;
import MyYahtzee.Yahtzee.Rules.box;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RulesSc {

	private Rules ruleClass;

	private boolean pass;

	private int[] dice;

	private Map<Integer, Integer> counter;

	private String category;

	private Map<String, Integer> scores;

	@Given("For All tests")
	public void for_All_tests() {

		ruleClass = new Rules();

		pass = true;

	}

	@Then("Verify if the result for {string} is correct")
	public void verify_if_the_result_for_is_correct(String string) {

		if (pass) {

			System.out.println("Test for " + string + " Passed!");

		} else {

			System.out.println("Test for " + string + " did not Pass!");

		}
	}

	// __________________________________________________________________________

	@Given("Roll a Die")
	public void roll_a_Die() {

		dice = ruleClass.rolling(1);

	}

	@When("Check if the result is between {int} and {int}")
	public void check_if_the_result_is_between_and(Integer int1, Integer int2) {

		if (!(dice[0] <= int2 && dice[0] >= int1)) {

			pass = false;

		}

	}

	// __________________________________________________________________________

	@When("Calculate count of numbers")
	public void calculate_count_of_numbers() {

		counter = ruleClass.countDice(dice);

	}

	@When("Check with the result with {int}, {int}, {int}, {int}, {int}, {int}")
	public void check_with_the_result_with(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5,
			Integer int6) {

		Integer[] expected = new Integer[] { int1, int2, int3, int4, int5, int6 };

		for (int i = 0; i < 6; i++) {

			if (counter.get(i + 1) != (Integer) expected[i]) {

				pass = false;

				break;
			}

		}

	}

	// __________________________________________________________________________

	@Given("Have the Dice with numbers  {int}, {int}, {int}, {int}, {int}")
	public void have_the_Dice_with_numbers(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {

		dice = new int[] { int1, int2, int3, int4, int5 };

	}

	@When("Choose the category number {int} to score")
	public void choose_the_category_number_to_score(Integer int1) {

		ruleClass.play(dice, int1);

		switch (int1) {

		case 1:

			category = box.Aces.name();

			break;

		case 2:

			category = box.Twos.name();

			break;

		case 3:

			category = box.Threes.name();

			break;

		case 4:

			category = box.Fours.name();

			break;

		case 5:

			category = box.Fives.name();

			break;

		case 6:

			category = box.Sixes.name();

			break;

		case 7:

			category = box.LargeStraight.name();

			break;

		case 8:

			category = box.SmallStraight.name();

			break;

		case 9:

			category = box.FullHouse.name();

			break;

		case 10:

			category = box.ThreeOfAKind.name();

			break;

		case 11:

			category = box.FourOfAKind.name();

			break;

		case 12:

			category = box.Chance.name();

			break;

		default:

			category = box.Yahtzee.name();

			break;

		}

	}

	@When("Check if the results is {int}")
	public void check_if_the_results_is(Integer int1) {

		if (ruleClass.getFinalDic().get(category) != int1) {

			pass = false;

		}
	}

	// __________________________________________________________________________

	@Given("Get the scores of the upper section {int}, {int}, {int}, {int}, {int}, {int}")
	public void get_the_scores_of_the_upper_section(Integer int1, Integer int2, Integer int3, Integer int4,
			Integer int5, Integer int6) {

		scores = new HashMap<String, Integer>();
		scores.put(box.Aces.name(), int1);
		scores.put(box.Twos.name(), int2);
		scores.put(box.Threes.name(), int3);
		scores.put(box.Fours.name(), int4);
		scores.put(box.Fives.name(), int5);
		scores.put(box.Sixes.name(), int6);

	}

	@When("Check if there is a {int}")
	public void check_if_there_is_a(Integer int1) {

		if (!int1.equals(ruleClass.calculateBonus(scores))) {

			pass = false;

		}
	}

//__________________________________________________________________________

}
