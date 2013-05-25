package api;

import casa.LispAccessible;
import casa.abcl.ParamsMap;
import casa.ui.AgentUI;
import iRobotCreate.iRobotCommands;
import iRobotCreate.iRobotCreate;

abstract public class AbstractRobot extends iRobotCreate {

	static{
		  createCasaLispOperators(AbstractRobot.class);
	  }
	
	boolean error = false;
	
	public AbstractRobot(ParamsMap params, AgentUI ui) throws Exception {
		super(params, ui);
	}
	
	/**
	 * Provides a public interface to the drive command.
	 * @param speed The speed to drive at. Must be within -500 to +500.
	 */
	public void drive(int speed){
		drive((short)speed, iRobotCommands.P_Straight, true, true);
	}
	
	/**
	 * Provides a public interface to the drive command.
	 * @param speed The speed to drive at. Must be within -500 to +500.
	 * @param radius The turning radius. Must be within -2000 to 2000 (mm).
	 */
	public void drive_turn(int speed, int radius){
		drive((short)speed, (short)radius, true, true);		
	}
	
	public boolean ABbreathing(){
		System.out.println("Cheking breathing...");
		return this.breathing;
	}
	
	public void stopMotor() {
		
		this.stopMotors();
	}
	
	@LispAccessible(name="iRobot.error",help="returns true if there is a error")
	public boolean error() {
		
		error = getSensorBumps()!=0 || getSensorWheelOvercurrent()!=0;
		
		if(error){
			System.out.println("true");
			return true;
		}
		else{
			System.out.println("false");
			return false;
		}
	}
	
	@Override
	protected void checkSensors(){
		error = getSensorBumps()!=0 || getSensorWheelOvercurrent()!=0;
	}
	
}
