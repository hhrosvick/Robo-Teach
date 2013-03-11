package api;

import casa.CASAProcess;
import casa.abcl.ParamsMap;
import casa.ui.AgentUI;
import iRobotCreate.iRobotCreate;

abstract public class AbstractRobot extends iRobotCreate {

	public AbstractRobot(ParamsMap params, AgentUI ui) throws Exception {
		super(params, ui);

	}
	
	/**
	 * Provides a public interface to the drive command.
	 * @param speed The speed to drive at. Must be within -500 to +500.
	 */
	public void drive(int speed){
		drive((short)speed);
	}
	
	/**
	 * Provides a public interface to the drive command.
	 * @param speed The speed to drive at. Must be within -500 to +500.
	 * @param radius The turning radius. Must be within -2000 to 2000 (mm).
	 */
	public void drive_turn(int speed, int radius){
		drive((short)speed, (short)radius);		
	}
	
}
