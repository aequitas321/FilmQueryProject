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

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean checkContinue = true;
		while (checkContinue) {
			System.out.println("What would you like to do?");
			System.out.println(" ");
			System.out.println("1) Look up a film by it's ID");
			System.out.println("2) Look up a film by a search keyword");
			System.out.println("3) Exit application");

			switch (input.nextInt()) {
			case 1:
				System.out.println("Enter film ID.");
				try {
					Film film = db.findFilmById(input.nextInt());
					if (film != null) {
						System.out.println(film);
					} else {
						System.out.println("No film found.");
					}
				} catch (Exception e) {

					System.out.println("Invalid entry please try again.");
					input.next();
					startUserInterface(input);
				}
				break;

			case 2:
				System.out.println("Enter keyword");
				try {
					List<Film> films = db.findFilmsByKeyword(input.next());
					if (films.size() == 0) {
						System.out.println("No films found.");
					} else {
						for (Film film : films) {
							System.out.println(film);
						}

					}
				} catch (Exception e) {
					
					
				}
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

	}
}
