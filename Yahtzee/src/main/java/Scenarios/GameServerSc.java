package Scenarios;

import MyYahtzee.Yahtzee.App;
import MyYahtzee.Yahtzee.GameServer;
import MyYahtzee.Yahtzee.Rules;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GameServerSc {

	@Given("I want to write a step with precondition")
	public void i_want_to_write_a_step_with_precondition() {

		Thread T = new Thread(new Runnable() {

			public void run() {
				GameServer.main(null);

			}
		});

		T.start();
		App.main(null);
		// App.this.holdSomeDice(
		App.main(null);
		App.main(null);

	}

	@When("I complete action")
	public void i_complete_action() {

	}

	@Then("I validate the outcomes")
	public void i_validate_the_outcomes() {

	}

}
