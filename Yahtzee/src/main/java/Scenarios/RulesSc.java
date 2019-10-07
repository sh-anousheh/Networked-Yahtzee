package Scenarios;

import java.util.*;
import java.util.Map;

import MyYahtzee.Yahtzee.Rules;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RulesSc {

	private Rules ruleClass;

	boolean pass;

	int[] dice;

	Map<Integer, Integer> counter;

	@Given("For All tests")
	public void for_All_tests() {

		ruleClass = new Rules();

	}

	// __________________________________________________________________________

	@Given("Define default answer")
	public void define_default_answer() {

		pass = true;

	}

	@When("Roll a Die")
	public void roll_a_Die() {

		dice = ruleClass.rolling(1);

	}

	@Then("Check if the result is between {int} and {int}")
	public void check_if_the_result_is_between_and(Integer int1, Integer int2) {

		if (!(dice[0] <= int2 && dice[0] >= int1)) {

			pass = false;

		}

		if (pass) {

			System.out.println("Test for 'Roll a Die' Passed!");

		} else {

			System.out.println("Test for 'Roll a Die' did not Passed!");

		}

	}

	// __________________________________________________________________________

	@Given("Roll the Dice")
	public void roll_the_Dice() {

		dice = new int[] { 3, 2, 6, 6, 2 };

		pass = true;

	}

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

	@Then("I verify if the result is correct")
	public void i_verify_if_the_result_is_correct() {

		if (pass) {

			System.out.println("Test for 'countDice' Passed!");

		} else {

			System.out.println("Test for 'countDice' did not Passed!");

		}

	}
	// __________________________________________________________________________

}
