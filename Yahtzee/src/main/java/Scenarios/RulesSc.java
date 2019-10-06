package Scenarios;

import MyYahtzee.Yahtzee.Rules;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RulesSc {

	private Rules ruleClass;

	boolean pass;

	int[] dices;

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

		dices = ruleClass.rolling(1);

	}

	@Then("Check if the result is between {int} and {int}")
	public void check_if_the_result_is_between_and(Integer int1, Integer int2) {

		if (!(dices[0] <= int2 && dices[0] >= int1)) {

			pass = false;

		}
	}

	// __________________________________________________________________________

}
