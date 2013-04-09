package api;

import static org.junit.Assert.*;

import org.junit.Test;

public class AuthenticatorTest {

	@Test
	public void testAuthValidUser_test() {
		
		assertEquals("User: test ", 1, Authenticator.auth("test", "1234"));
	}
	
	@Test
	public void testAuthValidUser_bob() {
		
		assertEquals("User: bob ", 111111, Authenticator.auth("bob", "1234"));
	}

	@Test
	public void testAuthValidUser_frank() {
	
		assertEquals("User: frank ", 222222, Authenticator.auth("frank", "1234"));
	}

	@Test
	public void testAuthValidUser_teach() {
	
		assertEquals("User: teach ", 999999, Authenticator.auth("teach", "1234"));
	}
	
	@Test
	public void testAuthInvalidUsers() {
		
		assertEquals("User: invalid ", 0, Authenticator.auth("invlaid_user", "invalid_password"));
	}

}
