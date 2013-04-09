package api;

import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;

public class DatabaseTest {

	Database DB = null;
	// practice expecting exceptions
	/*@Test (expected=Exception.class)
	public void getInstancetest() throws Exception {
		Database DB = Database.getInstance();
	}*/

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
	@Test (expected=IllegalArgumentException.class)
	public void NullStringQuery1(){
		try {
			DB.query(null);
		} catch (SQLException e) {
			Assert.fail();
		}
	}
	/// Blank String
	@Test (expected=IllegalArgumentException.class)
	public void EmptyStringQuery1(){
		try {
			DB.query("");
		} catch (SQLException e) {
			Assert.fail();
		}
	}
	/// Random String
	@Test (expected=IllegalArgumentException.class)
	public void RandomStringQuery1(){
		try {
			DB.query("adblksd soreo");
		} catch (SQLException e) {
			Assert.fail();
		}
	}

	/// Select a table that doesn't exist
	@Test
	public void TableDoesntExistQuery(){
		try {
			ResultSet rs = DB.query("SELECT Name FROM Country");
			if(rs.next())
				Assert.fail();
		} catch (SQLException e) {
			Assert.fail();
		}
		Assert.assertTrue(true);
		
	}
	/// Select a table that does exist
	// This test will pass as long as there is an entry in the table
	@Test
	public void TableDoesExistQuery(){
		try {
			ResultSet rs = DB.query("SELECT Name FROM XYZ");
			if(!rs.next())
				Assert.fail();
		} catch (SQLException e) {
			Assert.fail();
		}
		Assert.assertTrue(true);
		
	}
	/// Test for close database
	@Test
	public void closetest(){
		DB.close();
		Assert.assertNull(DB);
	}
	
	// Execute Tests
	
	/// Null String
	@Test (expected=IllegalArgumentException.class)
	public void NullStringexecute1(){
		try {
			DB.execute(null);
		} catch (SQLException e) {
			Assert.fail();
		}
	}
	/// Blank String
	@Test (expected=IllegalArgumentException.class)
	public void EmptyStringexecute1(){
		try {
			DB.execute("");
		} catch (SQLException e) {
			Assert.fail();
		}
	}
	/// Random String
	@Test (expected=IllegalArgumentException.class)
	public void RandomStringexecute1(){
		try {
			DB.execute("adblksd soreo");
		} catch (SQLException e) {
			Assert.fail();
		}
	}

	/// Select a table that doesn't exist
	@Test
	public void TableDoesntExistexecute(){
		try {
			DB.execute("SELECT Name FROM Country");

		} catch (SQLException e) {
			Assert.fail();
		}
		Assert.assertTrue(true);
		
	}
	/// Select a table that does exist
	// This test will pass as long as there is an entry in the table
	@Test
	public void TableDoesExistexecute(){
		try {
			DB.execute("SELECT Name FROM XYZ");

		} catch (SQLException e) {
			Assert.fail();
		}
		Assert.assertTrue(true);
		
	}
}
