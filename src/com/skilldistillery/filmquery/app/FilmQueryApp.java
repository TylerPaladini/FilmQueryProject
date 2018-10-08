package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public FilmQueryApp() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();

	}

	private void test() throws SQLException {
		Film film = db.getFilmById(1);
		Actor actor = db.getActorById(1);
		System.out.println(film);
		System.out.println(actor);

	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		int choice = 0;

		while (choice != 3) {
			System.out.println();
			System.out.println("1. Seach by ID");
			System.out.println("2. Search by keyword");
			System.out.println("3. Exit");
			System.out.println("Please choose numerical option 1-3");
			choice = input.nextInt();

			if (choice == 1) {
				System.out.println("Enter ID of the film you would like to see");
				int filmID = input.nextInt();
				Film film = db.getFilmById(filmID);
				if (film != null) {
					System.out.println(film);
				} else {
					System.out.println("We are sorry, but we do not have a copy of the "
							+ "film you have requested. Please try again.");
				}

			} else if (choice == 2) {
				System.out.println("Enter keyword to search through films");
				String keyword = input.next();
				List<Film> filmChoice = db.getFilmByKeyword(keyword);
				if (filmChoice.size() != 0) {
					for (Film film : filmChoice) {
						System.out.println(film);
					}
				} else {
					System.out.println("There is no film with that name, please try again");
				}

			}

			else if (choice == 3){
				System.out.println("Thank you for visiting the Video Store. GoodBye");

			}
			else {
				System.out.println("That is an invalid option. Please choose again");
			}

		}
	}

}
