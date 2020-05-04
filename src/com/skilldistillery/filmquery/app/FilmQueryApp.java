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
                mainMenu();
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
            int filmId = input.nextInt();
            Film film = db.findFilmById(filmId);

            if (film != null) {
                System.out.println("\n" + film);
                subMenu(input, filmId);
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
        } catch (SQLException e) {
//            System.out.println("Invalid entry please try again.");
//            input.next();
//            startUserInterface(input);
            e.printStackTrace();
        }
    }

    private void mainMenu() {
        System.out.println("What would you like to do?");
        System.out.println(" ");
        System.out.println("1) Look up a film by it's ID");
        System.out.println("2) Look up a film by a search keyword");
        System.out.println("3) Exit application");
        System.out.print("Selection: ");
    }

    private void subMenu(Scanner input, int filmId) throws SQLException {
        try {
            System.out.println("1) View all film details.");
            System.out.println("2) Return to main menu.");
            System.out.print("Selection: ");
            subMenuLogic(input, filmId);
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            input.next();
            subMenu(input, filmId);
        }
    }

    private void subMenuLogic(Scanner input, int filmId) throws SQLException {
        try {
            switch (input.nextInt()) {
                case 1:
                    db.totalFilmDescription(filmId);
                    break;
                case 2:
                    startUserInterface(input);
                    input.next();
                    break;
                default:
                    System.out.println("invalid input. Please try again\n");
                    subMenu(input, filmId);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
