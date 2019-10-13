package Scenarios;

import static org.junit.Assert.assertEquals;

import FakePlayers.Player1;
import FakePlayers.Player4;
import FakeServers.Server1;
import FakeServers.Server4;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Level3 {

	boolean pass = true;

	@Given("I want to write a step with precondition")
	public void i_want_to_write_a_step_with_precondition() {
		Thread T = new Thread(new Runnable() {

			public void run() {

				Server4.main(null);

			}
		});

		T.start();
	}

	@When("I complete action")
	public void i_complete_action() {

		Player4.main(new String[] { "Anousheh" });

		Player4.main(new String[] { "Zahra" });

		Player4.main(new String[] { "Behnaz" });

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("I validate the outcomes")
	public void i_validate_the_outcomes() {

		assertEquals(pass, true);
	}

}
