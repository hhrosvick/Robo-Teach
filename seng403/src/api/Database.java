package api;

import java.sql.*;

/**
 * Singleton class to ensure only one database connection exists.
 * @author Henry
 *
 */
public class Database {

	private static Database DB;
	
	/*
	 * Access the database at www.phpmyadmin.co with the information below.
	 */
	private static final String CONNECTION_LOCATION = "jdbc:mysql://sql2.freemysqlhosting.net";
	private static final String CONNECTION_PASSWORD = "fB7%gH8*"; 
	private static final String CONNECTION_DATABASE = "sql24765"; // This is also the user name
	
	private static Connection con = null;
	private Statement statement = null;
	private ResultSet res = null;
	
	// TODO ITER 2: Setup database and create tables.
	
	/**
	 * Private constructor
	 */
	private Database(){}
	
	/**
	 * Returns the Database instance if it exists, creates and load the database otherwise.
	 * @return the Database instance
	 * @throws Exception 
	 */
	public static Database getInstance() throws Exception{
		
		if(DB == null){
			DB = new Database();
			DB.connect();
		}
		
		return DB;
	}
	
	/**
	 * Connects to the database at CONNECTION_LOCATION
	 * @throws Exception 
	 */
	private Connection connect() throws Exception{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION_LOCATION, CONNECTION_DATABASE, CONNECTION_PASSWORD);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Database connection failed: " + e.getMessage());
		}
		
		return con;
		
	}
	
	/**
	 * Query the database, returns the result.
	 * @param query the query String
	 * @return the resultant object
	 */
	public ResultSet query(String query) throws SQLException{
		
		res = null;
		
		statement = con.prepareStatement(query);
		
		res = statement.executeQuery(query);
		
		// Take a look at the 'java.sql.*' javadocs online.
		// Basically, create a PreparedStatement using con.prepareStatement("query")
		// and return the executeQuery() result

		return res;
	}
	
	public void close(){
		
		// TODO ITER 2: Disconnect the database, then destroy the instance.
		DB = null;
	}
}
