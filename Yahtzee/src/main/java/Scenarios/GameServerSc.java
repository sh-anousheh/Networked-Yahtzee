package Scenarios;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import MyYahtzee.Yahtzee.GameServer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class GameServerSc extends TestCase {

	private GameServer gs;

	private boolean pass;

	private List<String> input;

	Thread T;

	@Given("The game is started")
	public void the_game_is_started() {

		pass = true;

		input = new ArrayList<String>();

		T = new Thread(new Runnable() {

			public void run() {

				gs = new GameServer();

				gs.acceptConnections();

			}
		});

		T.start();

	}

	@When("{string} would enter the game")
	public void would_enter_the_game(String string) {

		for (String s : string.split(","))

		{
			input.add(s);

			FakePlayer.main(new String[] { s });
		}

	}

	@When("Check if {string}could enter successfully")
	public void check_if_could_enter_successfully(String string) {

		if (!input.toString().equals(gs.getPlayers().toString())) {

			pass = false;
		}
	}

	@Then("validate if the result is {string}")
	public void validate_if_the_result_is(String string) {

		assertEquals(string, String.valueOf(pass));

	}
}
