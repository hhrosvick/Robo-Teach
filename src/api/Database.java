package api;

import java.sql.*;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

/**
 * Singleton class to ensure only one database connection exists.
 * @author Henry
 *
 */
public class Database {

	private static Database DB;
	
	private static final String CONNECTION_LOCATION = "jdbc:mysql://localhost:";
	private static final String CONNECTION_PASSWORD = "roboteach"; 
	private static final String CONNECTION_DATABASE = "roboteach";
	
	private static final String sshUser = "henry";
	private static final String sshPassword = "1234";
	private static final String sshHost = "192.241.88.33";
	private static final int sshPort = 22;
	private static final int localPort = 3366;
	private static final int remotePort = 3306;
	
	private static Connection con = null;
	private Statement statement = null;
	private ResultSet res = null;
	
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
			
			System.out.print("Setting up SSH Tunnel...");
			Database.doSshTunnel(sshUser, sshPassword, sshHost, sshPort, sshHost, localPort, remotePort);
			System.out.println("Done");
			
			System.out.print("Connecting to database...");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION_LOCATION + localPort, CONNECTION_DATABASE, CONNECTION_PASSWORD);
			System.out.println("Done");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Database connection failed: " + e.getMessage());
		}
		
		return con;
		
	}
	
	/**
	 * Creates a new SSH Tunnel to the remote database.
	 * @param strSshUser
	 * @param strSshPassword
	 * @param strSshHost
	 * @param nSshPort
	 * @param strRemoteHost
	 * @param nLocalPort
	 * @param nRemotePort
	 * @throws JSchException
	 */
	 private static void doSshTunnel( String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort ) throws JSchException
	  {
	    final JSch jsch = new JSch();
	    Session session = jsch.getSession( strSshUser, strSshHost, 22 );
	    session.setPassword( strSshPassword );
	    localUserInfo lui = new localUserInfo();
	    session.setUserInfo(lui);
	    
	    
	    
	    final Properties config = new Properties();
	    config.put( "StrictHostKeyChecking", "no" );
	    session.setConfig( config );
	     
	    session.connect();
	    session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	  }
	
	
	/**
	 * Query the database, returns the result.
	 * @param query the query String
	 * @return the resultant object
	 */
	public ResultSet query(String query) throws SQLException {
		
		res = null;
		
		statement = con.prepareStatement(query);
		res = statement.executeQuery(query);

		return res;
	}
	
	public boolean execute(String query) throws SQLException {
		
		return statement.execute(query);
	}
	
	public void close(){
		
		// TODO ITER 2: Disconnect the database, then destroy the instance.
		DB = null;
	}
	
	static class localUserInfo implements UserInfo{

		String passwd;
		
		@Override
		public String getPassphrase() {
			return null;
		}

		@Override
		public String getPassword() {
			return passwd;
		}

		@Override
		public boolean promptPassphrase(String arg0) {
			return true;
		}

		@Override
		public boolean promptPassword(String arg0) {
			return true;
		}

		@Override
		public boolean promptYesNo(String arg0) {
			return true;
		}

		@Override
		public void showMessage(String arg0) {
			System.out.println(arg0);
		}
		
		
	}
}


