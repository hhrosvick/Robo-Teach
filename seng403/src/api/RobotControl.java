package api;

/**
 * The robot control interface.<br>
 * Provides basic commands to control a robot of the AbstractRobot class.
 *  
 * @author Henry Rosvick
 */
public class RobotControl {

	private AbstractRobot robot = null;
	private int Speed;
	private int TurningRadius;
	
	/**
	 * Creates a RobotContorl instance with an attached AbstractRobot to control.
	 * @param robot
	 */
	public RobotControl(AbstractRobot robot){
		this.robot = robot;
		Speed = 50;
		TurningRadius = 500; // in millimeters
	}
	
	/**
	 * Just to ensure the robot stops if the controller is garbage collected.
	 */
	@Override
	public void finalize(){
		this.stop();
	}
	
	/**
	 * Drive the robot forward at the set Speed.
	 */
	public void forward(){
		robot.drive(Speed);
	}
	
	/**
	 * Drive the robot backward at the set Speed.
	 */
	public void backward(){
		robot.drive(-Speed);
	}
	
	/**
	 * Stop the robot
	 */
	public void stop(){
		robot.drive(0);
	}
	
	/**
	 * Turn the robot to the left at the set Speed and TurningRadius
	 */
	public void turn_LEFT(){
		robot.drive_turn(Speed, TurningRadius);
	}
	
	/**
	 * Turn the robot to the right at the set Speed and TurningRadius
	 */
	public void turn_RIGHT(){
		robot.drive_turn(Speed, -TurningRadius);
	}
	
	/**
	 * Rotate the Robot to the left at the set Speed.
	 */
	public void rotate_LEFT(){
		robot.drive_turn(Speed, 0);
	}
	
	/**
	 * Rotate the Robot to the right at the set Speed.
	 */
	public void rotate_RIGHT(){
		robot.drive_turn(-Speed, 0);
	}
	
	/**
	 * Loads the file to the robot, if the file path exists.	
	 * @param filepath
	 */
	public void loadLispFile(String filepath){
		if(filepath != null && filepath != "")
			robot.abclEval(API.fileRead(filepath), null);
	}

	/**
	 * Returns the currently set Speed.
	 * @return the current speed of the robot.
	 */
	public int getSpeed() {
		return Speed;
	}

	/**
	 * Sets the Speed of the robot.
	 * @param speed the new speed value
	 */
	public void setSpeed(int speed) {
		if(speed < 1)		
			Speed = 1;
		else if(speed > 500)
			Speed = 500;
		else
			Speed = speed;
	}

	/**
	 * Returns the currently set TurningRadius in millimeters.
	 * @return the current turning radius of the robot.
	 */
	public int getTurningRadius() {
		return TurningRadius;
	}

	/**
	 * Sets the TurningRadius of the robot.
	 * @param radius the new turning radius in millimeters
	 */
	public void setTurningRadius(int radius) {
		
		if(radius < 0)
			TurningRadius = 0;
		else
			TurningRadius = radius;
	}
	
	
}
