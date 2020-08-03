package sait.mms.drivers;

import java.sql.*;

import sait.mms.contracts.DatabaseDriver;

/**
 * Class description: Driver for connecting to and accessing a MySQL or MariaDB database.
 *
 * @author Maria Laura Diaz Pena, Justin Van Groningen
 * @version August 5, 2020
 *
 */
public class MariaDBDriver implements DatabaseDriver {
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg251";
	private static final String USERNAME = "cprg251";
	private static final String PASSWORD = "password";
	
	private Connection connection = null;
	
	/**
	 * Initializes the newly created JdbcDriver.
	 * @throws ClassNotFoundException 
	 */
	public MariaDBDriver() throws ClassNotFoundException {
		Class.forName("org.mariadb.jdbc.Driver");
	}
	
	/* (non-Javadoc)
	 * @see drivers.DatabaseDriver#connect()
	 */
	@Override
	public void connect() throws SQLException
	{
		String dsn = this.getDsn();
		connection = DriverManager.getConnection(dsn);
	}
	
	/**
	 * Gets the data source name to connect to the database.
	 * @return DSN
	 */
	private String getDsn() {
		// Data source name is formatted as follows (for MariaDB): jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword
		String dataSourceName = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE, USERNAME, PASSWORD);
		
		return dataSourceName;
	}
	
	/* (non-Javadoc)
	 * @see drivers.DatabaseDriver#get(java.lang.String)
	 */
	@Override
	public ResultSet get(String query) throws SQLException
	{
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);
	
		return results;
	}
	
	/* (non-Javadoc)
	 * @see drivers.DatabaseDriver#update(java.lang.String)
	 */
	@Override
	public int update(String query) throws SQLException
	{
		Statement statement = connection.createStatement();
		int ret = statement.executeUpdate(query);
	
		return ret;
	}
	
	@Override
	public PreparedStatement prepareStatement(String query) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(query);
		
		return statement;
	}

	/* (non-Javadoc)
	 * @see drivers.DatabaseDriver#disconnect()
	 */
	@Override
	public void disconnect() throws SQLException
	{
		if (connection != null && !connection.isClosed())
			connection.close();
	}

}