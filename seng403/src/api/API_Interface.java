package api;

public interface API_Interface {
	
	/**
	 * Checks if the API has been initialized<br>
	 * If not, it will be initialized.
	 * @return true if the API is initialized, false if there is an error
	 */
	public boolean initialize();
	
	/**
	 * Checks a username / password combination against a database or external system.
	 * @param user_name login name
	 * @param password login password (preferably the password has already been encrypted)
	 * @return true is the user is valid, false otherwise.
	 */
	public boolean authenticate_user(String user_name, String password);
	
	/**
	 * Loads the code found at 'filepath' to the robot.
	 * @param filepath
	 */
	public String loadToRobot(String filepath);
	
	/**
	 * Loads the code found at 'filepath' to the simulator.
	 * @param filepath
	 */
	public String loadToSimulator(String filepath);
	
	/**
	 * Translates the code found at 'filepath', then loads it into the robot.
	 * @param filepath
	 * @return String containing any error messages related to the translation. Null if translation was successful.
	 */
	public String translateLoadToRobot(String filepath);
	
	/**
	 * Translates the code found at 'filepath', then loads it into the simulator.
	 * @param filepath
	 * @return String containing any error messages related to the translation. Null if translation was successful.
	 */
	public String translateLoadToSimulator(String filepath);

}
