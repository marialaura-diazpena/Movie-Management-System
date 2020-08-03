package sait.mms.application;

import java.sql.SQLException;

import sait.mms.managers.MovieManagementSystem;

/**
 * 
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version August 5, 2020
 *
 */
public class appDriver {
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		MovieManagementSystem mms = new MovieManagementSystem();
		
		mms.displayMenu();
	}
}
