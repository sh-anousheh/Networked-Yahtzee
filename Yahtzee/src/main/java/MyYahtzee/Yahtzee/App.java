package MyYahtzee.Yahtzee;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class App {

	private BufferedReader reader;

	private String Name = "Anousheh";

	public App() {

		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public static void main(String[] args) {

		int round = 1;

		App app = new App();

	}

	public int returnIntFor(String input, int max) {

		System.out.println(input);

		int res = 0;

		try {

			res = Integer.parseInt(reader.readLine());

			if (res < 1 || res > max) {

				throw new NumberFormatException();
			}

		} catch (NumberFormatException e) {

			System.out.println("Input is not valid!");

			return returnIntFor(input, max);

		} catch (IOException e) {

		}
		return res;
	}

}