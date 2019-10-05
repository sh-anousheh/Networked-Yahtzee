package Scenarios;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import MyYahtzee.Yahtzee.App;
import MyYahtzee.Yahtzee.Rules;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AppSc {

	private App app;

	private Random rand;

	private List<Object> holds;

	private String input;

	private boolean pass;

	public AppSc() {
		app = new App();
	}

	@Given("I want to write a step with precondition")
	public void i_want_to_write_a_step_with_precondition() {

		rand = new Random();

		input = "";

		pass = true;
	}

	@Given("some other precondition")
	public void some_other_precondition() {

		holds = rand.ints(1, 6).distinct().limit(4).boxed().collect(Collectors.toList());

		for (Object o : holds) {

			input += o.toString() + " ";
		}
	}

	@When("I complete action")
	public void i_complete_action() {

		Rules game = new Rules();

		int[] dice = game.rolling(5);

		int[] newDice = app.holdSomeDice(input, dice, game);

		int i = 0;

		for (Object o : holds) {

			if (newDice[i] != dice[Integer.parseInt(o.toString()) - 1]) {

				pass = false;

				break;

			}

			i++;
		}
	}

	@Then("I validate the outcomes")
	public void i_validate_the_outcomes() {

		if (pass)

		{
			System.out.println("correct answer.");
		}
	}

}
