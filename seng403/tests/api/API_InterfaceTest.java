package api;


import static org.junit.Assert.*;

import org.junit.Test;

import api.API;
import api.API_Interface;

public class API_InterfaceTest {

	/**
	 * authenticate_user negative tests
	 * @throws Exception 
	 */
	@Test
	public void authenticate_userTestArg1Null() throws Exception {
		API_Interface api = new API();
		assertSame(api.authenticate_user(null, "abcd"), false);
	}
	@Test
	public void authenticate_userTestArg1Empty() throws Exception {
		API_Interface api = new API();
		assertSame(api.authenticate_user("", "abcd"), false);
	}
	@Test
	public void authenticate_userTestArg2Null() throws Exception {
		API_Interface api = new API();
		assertSame(api.authenticate_user("abcd", null), false);
	}
	@Test
	public void authenticate_userTestArg2Empty() throws Exception {
		API_Interface api = new API();
		assertSame(api.authenticate_user("abcd",""), false);
	}
	@Test
	public void authenticate_userTestArgbothNull() throws Exception {
		API_Interface api = new API();
		assertSame(api.authenticate_user(null, null), false);
	}
	@Test
	public void authenticate_userTestArgbothEmpty() throws Exception {
		API_Interface api = new API();
		assertSame(api.authenticate_user("",""), false);
	}
	@Test
	public void authenticate_userTestArgNullEmpty() throws Exception {
		API_Interface api = new API();
		assertSame(api.authenticate_user(null, ""), false);
	}
	@Test
	public void authenticate_userTestArgEmptyNull() throws Exception {
		API_Interface api = new API();
		assertSame(api.authenticate_user("", null), false);
	}
	/**
	 * loadToRobot negative tests
	 */
	@Test
	public void loadToRobotTestArgNull() throws Exception {
		API_Interface api = new API();
		assertSame(api.loadToRobot(null), "Error");
	}
	@Test
	public void loadToRobotTestArgEmpty() throws Exception {
		API_Interface api = new API();
		assertSame(api.loadToRobot(""), "Error");
	}
	/**
	 * loadToSimulator negative tests
	 */
	@Test
	public void loadToSimulatorTestArgNull() throws Exception {
		API_Interface api = new API();
		assertSame(api.loadToSimulator(null), "Error");
	}
	@Test
	public void loadToSimulatorTestArgEmpty() throws Exception {
		API_Interface api = new API();
		assertSame(api.loadToSimulator(""), "Error");
	}
	/**
	 * translateLoadToRobot negative tests
	 */
	@Test
	public void translateLoadToRobotTestArgNull() throws Exception {
		API_Interface api = new API();
		assertSame(api.translateLoadToRobot(null), "Error");
	}
	@Test
	public void trasnlateloadToRobotTestArgEmpty() throws Exception {
		API_Interface api = new API();
		assertSame(api.translateLoadToRobot(""), "Error");
	}
}
