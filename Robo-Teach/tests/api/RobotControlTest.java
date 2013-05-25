package api;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class RobotControlTest {

	static RobotControl robot = null;
	
	@BeforeClass
	public static void createRobotControlBeforeClass() {
		
		robot = new RobotControl(null);
	}
	
	@Test
	public void setSpeedTestAbove() {
		robot.setSpeed(501);
		assertEquals("Speed => 501", 500, robot.getSpeed());
	}
	
	@Test
	public void setSpeedTestBelow() {
		robot.setSpeed(0);
		assertEquals("Speed => 0", 1, robot.getSpeed());
	}
	
	@Test
	public void setSpeedTestValid() {
		robot.setSpeed(250);
		assertEquals("Speed => 250", 250, robot.getSpeed());
	}
	
	@Test
	public void setTurningRadiusTestAbove() {
		robot.setTurningRadius(100000000);
		assertEquals("Radius => 100000000", 100000000, robot.getTurningRadius());
	}
	
	@Test
	public void setTurningRadiusTestBelow() {
		robot.setTurningRadius(0);
		assertEquals("Radius => -1", 0, robot.getTurningRadius());
	}
	
	@Test
	public void setTurningRadiusTestValid() {
		robot.setTurningRadius(250);
		assertEquals("Radius => 250", 250, robot.getTurningRadius());
	}
	
}
