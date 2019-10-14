package Scenarios;

import java.util.ArrayList;
import java.util.List;

import FakePlayers.Player1;
import FakePlayers.Player2;
import FakePlayers.Player3;
import FakePlayers.Player5;
import FakeServers.Server1;
import FakeServers.Server2;
import FakeServers.Server3;
import FakeServers.Server5;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class GameServerSc extends TestCase {

	private boolean pass;

	Server1 gs1;

	Server2 gs2;

	Server3 gs3;

	private List<String> input;

	Thread T;

	@Given("Define the default val")
	public void define_the_default_val() {

		pass = true;

		input = new ArrayList<String>();

	}

	// _________________________________________________________________

	@Given("The firstGame is started")
	public void the_firstGame_is_started() {

		T = new Thread(new Runnable() {

			public void run() {

				gs1 = new Server1();

				gs1.acceptConnections();

			}
		});

		T.start();

	}

	@When("{string} would enter the firstGame")
	public void would_enter_the_firstGame(String string) {

		for (String s : string.split(","))

		{
			input.add(s);

			Player1.main(new String[] { s });
		}

	}

	@Then("Check if they could enter successfully to FirstGame")
	public void check_if_they_could_enter_successfully_to_FirstGame() {

		if (!input.toString().equals(gs1.getPlayers().toString())) {

			pass = false;

		}
		assertEquals(true, pass);
	}

	// ___________________________________________________________________

	@Given("The SecondGame is started")
	public void the_SecondGame_is_started() {
		T = new Thread(new Runnable() {

			public void run() {

				gs2 = new Server2();

				gs2.acceptConnections();

			}
		});

		T.start();
	}

	@When("{string} would enter the SecondGame")
	public void would_enter_the_SecondGame(String string) {

		for (String s : string.split(","))

		{
			input.add(s);

			Player2.main(new String[] { s });
		}
	}

	@Then("Check if they could enter successfully to SecondGame")
	public void check_if_they_could_enter_successfully_to_SecondGame() {

		if (!input.toString().equals(gs2.getPlayers().toString())) {

			pass = false;

		}
		assertEquals(true, pass);
	}

	// ___________________________________________________________________

	@Given("The ThirdGame is started")
	public void the_ThirdGame_is_started() {
		T = new Thread(new Runnable() {

			public void run() {

				gs3 = new Server3();

				gs3.acceptConnections();

			}
		});

		T.start();
	}

	@When("{string} would enter the ThirdGame")
	public void would_enter_the_ThirdGame(String string) {

		for (String s : string.split(","))

		{
			input.add(s);

			Player3.main(new String[] { s });
		}
	}

	@Then("Check if they could not enter successfully to ThirdGame")
	public void check_if_they_could_not_enter_successfully_to_ThirdGame() {

		if (input.toString().equals(gs3.getPlayers().toString())) {

			pass = false;

		}
		assertEquals(true, pass);
	}
	// _____________________________________________________________________

	Server5 gs;

	@Given("The game is started with the socket {int}")
	public void the_game_is_started_with_the_socket(Integer int1) {

		final int socket = int1;

		pass = true;

		Thread T = new Thread(new Runnable() {

			public void run() {

				gs = new Server5(socket);

				gs.acceptConnections();

			}
		});

		T.start();

	}

	@Given("Anousheh, Ryan, and Brayana join the game")
	public void anousheh_Ryan_and_Brayana_join_the_game() {

		Player5.main(new String[] { "Anousheh" });

		Player5.main(new String[] { "Ryan" });

		Player5.main(new String[] { "Brayana" });
	}

	@When("It is the end of the game")
	public void it_is_the_end_of_the_game() {

		try {

			Thread.sleep(80);

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

	}

	@When("Check if isFinish is <{string}>")
	public void check_if_isFinish_is(String string) {

		if (!Boolean.valueOf(string).equals(gs.isFinish())) {

			pass = false;
		}
	}

	@Then("Validate the result")
	public void validate_the_result() {

		assertEquals(true, pass);
	}

}
