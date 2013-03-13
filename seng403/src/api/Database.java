package api;

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
	private static final String CONNECTION_LOCATION = "sql2.freemysqlhosting.net";
	private static final String CONNECTION_PASSWORD = "fB7%gH8*"; 
	private static final String CONNECTION_DATABASE = "sql24765"; // This is also the user name
	
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
	 */
	private void connect(){
		// TODO ITER 2: Connect to database at CONNECTION_LOCATION
		
		/*
		 * CURRENT INFORMATION:
		 * Host: 
		 * 
		 */
	}
	
	/**
	 * Query the database, returns the result.
	 * @param query the query String
	 * @return the resultant object
	 */
	public Object query(String query){
		
		// TODO ITER 2: Query the database with String (after cleaning input), and return the result.
		// NOTE: this does not necessarily need to return an object file. we can map it here as well. 
		
		return null;
	}
	
	public void close(){
		
		// TODO ITER 2: Disconnect the database, then destroy the instance.
		DB = null;
	}
}
