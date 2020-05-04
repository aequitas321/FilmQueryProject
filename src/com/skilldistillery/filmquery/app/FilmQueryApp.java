package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean checkContinue = true;
		try {
			while (checkContinue) {
				System.out.println("What would you like to do?");
				System.out.println(" ");
				System.out.println("1) Look up a film by it's ID");
				System.out.println("2) Look up a film by a search keyword");
				System.out.println("3) Exit application");
				System.out.print("Selection: ");

				switch (input.nextInt()) {
				case 1:
					findFilmByIdSwitch(input);
					break;

				case 2:
					findFilmByKeywordSwitch(input);
					break;

				case 3:
					System.out.println("Thank you for using our app. Come back soon!");
					checkContinue = false;
					break;
				default: {
					System.out.println("Invalid input. Please try again.");
				}

				}
			}
		} catch (Exception e) {
			System.out.println("Invalid selection. Please try again.");
			input.next();
			startUserInterface(input);
		}
	}

	private void findFilmByIdSwitch(Scanner input) {
		System.out.print("\nEnter film ID: ");
		try {
			Film film = db.findFilmById(input.nextInt());
			if (film != null) {
				System.out.println("\n" + film);
			} else {
				System.out.println("\nNo film found.");
			}
		} catch (Exception e) {

			System.out.println("Invalid entry please try again.");
			input.next();
			startUserInterface(input);
		}
	}

	private void findFilmByKeywordSwitch(Scanner input) {
		System.out.println("\nEnter keyword");
		try {
			List<Film> films = db.findFilmsByKeyword(input.next());
			if (films.size() == 0) {
				System.out.println("\nNo films found.");
			} else {
				for (Film film : films) {
					System.out.println("\n" + film);
				}

			}
		} catch (Exception e) {

		}
	}
}
