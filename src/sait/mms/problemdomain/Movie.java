package sait.mms.problemdomain;

/**
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version August 5, 2020
 *
 */
public class Movie {
	
	private int duration;
	
	private String title;
	
	private String year;
	
	/**
	 * @param title 
	 * @param duration 
	 * @param year 
	 * 
	 */
	public Movie (int duration, String title, String year) {
		this.duration = duration;
		this.title = title;
		this.year = year;
		toString();
	}
	
	/**
	 * @return
	 */
	public int getDuration() {
		
		return duration;
		
	}
	
	/**
	 * @param duration
	 */
	public void setDuration(int duration) {
		
	}
	
	/**
	 * @return title
	 */
	public String getTitle() {
		
		return title;
	}
	
	/**
	 * @param title
	 */
	public void setTitle(String title) {
		
	}
	
	/**
	 * @return year
	 */
	public String getYear() {
		
		return year;
	}
	
	/**
	 * @param year
	 */
	public void setYear(String year) {
		
	}
	
	public String toString(){
		return String.format("%-10d %-48s %10s\n", duration, title, year);
	}
	
}
