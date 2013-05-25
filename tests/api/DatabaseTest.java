package api;

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseTest {

	static Database DB = null;
	// practice expecting exceptions
	/*@Test (expected=Exception.class)
	public void getInstancetest() throws Exception {
		Database DB = Database.getInstance();
	}*/
	
	@BeforeClass
	public static void getInstanceBeforeClass() {
		try{
			DB = Database.getInstance();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@AfterClass
	public static void closeAfterClass(){
		DB.close();
	}

	/// test for getInstance function of database
	@Test
	public void getInstancetest(){
		
		try{
			DB = Database.getInstance();
		}
		catch (Exception e){
			Assert.fail();
		}
		Assert.assertNotNull(DB); 
	}

	// Query Tests
	
	/// Null String
	@Test (expected=SQLException.class)
	public void NullStringQuery1() throws SQLException{
		DB.query(null);
	}
	/// Blank String
	@Test (expected=SQLException.class)
	public void EmptyStringQuery1() throws SQLException{
		DB.query("");
	}
	/// Random String
	@Test (expected=SQLException.class)
	public void RandomStringQuery1() throws SQLException{
		
		DB.query("adblksd soreo");
		
	}

	/// Select a table that doesn't exist
	@Test (expected=SQLException.class)
	public void TableDoesntExistQuery() throws SQLException{

		DB.query("SELECT Name FROM Country");

	}
	/// Select a table that does exist
	// This test will pass as long as there is an entry in the table
	@Test (expected=SQLException.class)
	public void TableDoesExistQuery() throws SQLException{
		
		DB.query("SELECT Name FROM XYZ");
		
	}
	
	// Execute Tests
	
	/// Null String
	@Test (expected=SQLException.class)
	public void NullStringexecute1() throws SQLException{
		
		DB.execute(null);
	
	}
	
	/// Blank String
	@Test (expected=SQLException.class)
	public void EmptyStringexecute1() throws SQLException{
		
		DB.execute("");

	}
	/// Random String
	@Test (expected=SQLException.class)
	public void RandomStringexecute1() throws SQLException{
		
		DB.execute("adblksd soreo");
	
	}

	/// Select a table that doesn't exist
	@Test (expected=SQLException.class)
	public void TableDoesntExistexecute() throws SQLException{
		
		DB.execute("SELECT Name FROM Country");
		
	}
	/// Select a table that does exist
	// This test will pass as long as there is an entry in the table
	@Test (expected=SQLException.class)
	public void TableDoesExistexecute() throws SQLException{
		
		DB.execute("SELECT Name FROM XYZ");
		
	}
}
