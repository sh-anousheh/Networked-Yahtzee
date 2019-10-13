package Scenarios;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import FakePlayers.Player4;
import FakeServers.Server4;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Level3 {

	public static PrintWriter writer;

	public static int rowNum;

	boolean pass;

	public Level3() throws FileNotFoundException, UnsupportedEncodingException {

		writer = new PrintWriter("results.txt", "UTF-8");

		rowNum = 0;

		pass = true;

	}

	@Given("Start the Game")
	public void start_the_Game() {

		Thread T = new Thread(new Runnable() {

			public void run() {

				Server4.main(null);

			}
		});

		T.start();
	}

	@Given("Three players with the names: Anousheh, Ryan, and Brayana join the game")
	public void three_players_with_the_names_Anousheh_Ryan_and_Brayana_join_the_game() {

		Player4.main(new String[] { "Anousheh" });

		Player4.main(new String[] { "Ryan" });

		Player4.main(new String[] { "Brayana" });

	}

	@When("They finish the game")
	public void they_finish_the_game() {

		try {

			Thread.sleep(100);

		} catch (InterruptedException e) {

			e.printStackTrace();

		} finally {

			writer.close();
		}
	}

	@When("Check if the {int}th player was {string} in the {int} and chose the {string}")
	public void check_if_the_th_player_was_in_the_and_chose_the(Integer turn, String name, Integer round,
			String category) {

		Scanner scanner;
		try {
			scanner = new Scanner(new File("results.txt"));

			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();

				if (turn.compareTo(Integer.parseInt(line.substring(0, 2))) == 0) {

					String[] res = line.split(",");

					if (!res[2].equals(name) || !round.equals(Integer.parseInt(res[3])) || !category.equals(res[5])) {

						pass = false;
					}
				}
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	@Then("Check the outcomes")
	public void check_the_outcomes() {

		assertEquals(pass, true);
	}
}