package sait.mms.managers;

import java.util.*;
import java.sql.*;
import sait.mms.contracts.*;
import sait.mms.drivers.*;
import sait.mms.problemdomain.*;

/**
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version August 5, 2020
 *
 */
public class MovieManagementSystem {

	
	Scanner input = new Scanner (System.in);
	private Connection connection = null;
	MariaDBDriver mdbd = new MariaDBDriver();
	
	/**
	 * User defined Constructor of MovieManagementSystem
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * 
	 */
	public MovieManagementSystem() throws ClassNotFoundException, SQLException {
		mdbd.connect();
	}
	/**
	 * The displayMenu method, where we are introducing the other methods that make our 
	 * program work.
	 * @throws SQLException 
	 * 
	 */
	public void displayMenu() throws SQLException {
		int option = -1;

		while (option != 4) {
			mdbd.connect();

			System.out.println("Movie Management System");
			System.out.println("1     Add a New Movie");
			System.out.println("2     Get a list of Movies by Year");
			System.out.println("3     Get a List of Random Movies");
			System.out.println("4     Delete a Movie");
			System.out.println("5     Exit");
			System.out.print("Enter an option: ");
			option = input.nextInt();
			System.out.println("");

			switch (option) {
			case 1:
				addMovie();
				mdbd.disconnect();
				break;
			case 2:
				printMoviesInYear();
				mdbd.disconnect();
				break;
			case 3:
				printRandomMovies();
				mdbd.disconnect();
				break;
			case 4:
				deleteMovie();
				mdbd.disconnect();
				break;
			case 5:
				System.out.println("Closing...");
				mdbd.disconnect();
				break;
			default:
				System.out.println("Invalid option!");
			}

		}
	}
	

	/**
	 * the AddMovie method where we add a new movie, duration, title and year, to our txt file.
	 * This method also saves the addition to the database.
	 * @throws SQLException 
	 * 
	 */
	private void addMovie() throws SQLException {
		
		System.out.println("Selected: Add a New Movie");
		System.out.print("Enter duration of the movie: ");
		int duration = input.nextInt();
		input.nextLine();
		System.out.print("Enter movie title: ");
		String title = input.nextLine();
		
		System.out.print("Enter year it was released: ");
		String year = input.nextLine();
		
		String query = "INSERT INTO movies (duration, title, year) VALUES ("+ duration +", '"+ title +"', '"+ year +"')";
		
		mdbd.update(query);
		
		System.out.println("Movie Added!");
		System.out.println("");
		
		}
	
	/**
	 * printMoviesInYear method we input the year and we get a list of all the movies
	 * made that year and adds up the duration at the end.
	 * @throws SQLException 
	 * 
	 */
	private void printMoviesInYear() throws SQLException {
		System.out.println("Selected: Get a list of Movies by Year");
		System.out.println("");
		System.out.print("Enter year: ");
		int year = input.nextInt();
		
		String query = "SELECT * FROM movies WHERE year = '" + year + "'";

		ResultSet results = mdbd.get(query);

		System.out.printf("%-10s %-10s %48s\n", "Duration", "Title", "Year");
		
		while (results.next()) {
			int movieDuration = results.getInt("duration");
			String movieTitle = results.getString("title");
			int movieYear = results.getInt("year");
			
			System.out.printf("%-10d %-48s %10s\n", movieDuration, movieTitle, movieYear);
 		}
		
		results = mdbd.get("SELECT SUM(duration) FROM movies WHERE year = '" + year + "'");
		while (results.next()) {
			int totalDuration = results.getInt("SUM(duration)");
			System.out.println("Total Duration is " + totalDuration + " minutes");
		}
		System.out.println("");
		
	}
	
	/**
	 * printRandomMovies method gets a list of random  movies, as many as you specify.
	 * @throws SQLException 
	 */
	private void printRandomMovies() throws SQLException {
		System.out.println("Selected: Get a List of Random Movies");
		System.out.println("");
		System.out.print("Enter Number of Movies: ");
		int numMovies = input.nextInt();
		
		String query = "SELECT * FROM movies ORDER BY RAND() LIMIT " + numMovies + "";
		
		ResultSet results = mdbd.get(query);

		System.out.printf("%-10s %-10s %48s\n", "Duration", "Title", "Year");
		int totalDuration = 0;
		
		while (results.next()) {
			int movieDuration = results.getInt("duration");
			String movieTitle = results.getString("title");
			int movieYear = results.getInt("year");
			System.out.printf("%-10d %-48s %10s\n", movieDuration, movieTitle, movieYear);
			totalDuration += results.getInt("duration");

		}
		System.out.println("Total Duration is " + totalDuration + " minutes");
		
		
		System.out.println("");
		
	}
	
	/**
	 * deleteMovie method deletes one record from the database, based on the record's ID. 
	 * @throws SQLException 
	 * 
	 */
	private void deleteMovie() throws SQLException {
		System.out.println("Selected: Delete a Movie");
		System.out.println("");
		System.out.print("Enter Movie ID: ");
		int id = input.nextInt();
		
		String query = "DELETE FROM movies WHERE id = '" + id + "'";
		
		mdbd.update(query);
		
		System.out.println("Movie Deleted!");
		System.out.println("");
		
	}
	
	
}
