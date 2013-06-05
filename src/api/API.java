package api;

import java.io.BufferedReader;
import java.io.FileReader;

import casa.CASAProcess;
import casa.ProcessOptions;
import casa.TransientAgent;
import casa.ui.AgentUI;
import casa.ui.StandardOutAgentUI;

public class API {

	/*
	 ******************************************
	 * Singleton Methods and Variables        *
	 ******************************************
	 */
	
	private CASAProcess CASA = null;
	private TransientAgent Environment = null;
	private AbstractRobot Robot = null;
	private AgentUI UI = null;
	
	private static API Instance = null;
	private static final String tracetags ="info5,warning,msg,iRobot,-boundSymbols,-policies9,-commitments,-eventqueue,-conversations";
	
	/**
	 * Constructor. Calls initialize() on itself.
	 * @throws Exception 
	 */
	private API() throws Exception {
		this.initialize();
	}
	
	/**
	 * Checks if the API has been initialized<br>
	 * If not, it will be initialized.<br>
	 * The CASA process will be stared.
	 * @throws Exception 
	 */
	private void initialize() throws Exception{
		try {
			if(Instance != null) throw new Exception("API Instance already exisits");
		
			CASA = CASAProcess.getInstance();
			
			ProcessOptions options = new ProcessOptions(CASA);
			options.traceTags = tracetags;
			options.tracing = true;
			
			CASA.setOptions(options);
			
			UI = new StandardOutAgentUI();
		
			Database.getInstance();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw new Exception("API Initialization Failed.");
		}
		
		
	}
	
	public static API getInstance(){
		if(Instance == null){
			try {
				Instance = new API();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Instance;
	}

	/*
	 ******************************************
	 *   ROBOT FUNCTIONS                      *
	 ******************************************
	 */
	
	private static final String SerialLocation = "/dev/rfcomm0";
	private static final int EnvironmentPort = 5780;
	private static final int RobotPort = 5781;
	
	/**
	 * Reads the file at the file path and returns the contents as a string<br>
	 * Accessible at the package level.
	 * @param filepath
	 * @return a String of the file contents
	 */
	static String fileRead(String filepath){
				
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			StringBuilder builder = new StringBuilder();
			String line = null;
			
			while((line = reader.readLine()) != null) { 
				builder.append(line); 
			}
			
			reader.close();
			
			return builder.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
		
	}
	
	/**
	 * Loads the code found at file path to the robot.
	 * @param filepath
	 */
	public String loadToRobot(String filepath) {
		
		if(System.getProperty("os.name").startsWith("Windows"))
			return null;
		
		loadRobotAgent();
		
		if(filepath != null && filepath != "")
			Robot.abclEval(fileRead(filepath), null);

		return null;		
	}

	/**
	 * Loads the code found at file path to the simulator.
	 * @param filepath
	 */
	public String loadToSimulator(String filepath) {
		
		loadEnvironment();
		loadSimulatorAgent();		
		
		// Run code found in file at file path, if one exists.
		if(filepath != null && filepath != "")
		{
			if(!Robot.ABbreathing()){
				System.out.println("Robot not Breathing");
				Robot.stopMotor();
			}
			
			Robot.abclEval(fileRead(filepath), null);

		}
			
		return null;		
	}	
	
	/**
	 * Loads the robot control interface, and returns it's instance
	 * @return a RobotControl instance
	 */
	public RobotControl loadRobotController() {
		
		if(System.getProperty("os.name").startsWith("Windows"))
			return loadSimulatorController();
		
		loadRobotAgent();
		return new RobotControl(Robot);
	}
	
	/**
	 * Loads the simulator control interface, and returns it's instance
	 * @return a RobotControl instance
	 */
	public RobotControl loadSimulatorController() {
		loadEnvironment();
		loadSimulatorAgent();
		return new RobotControl(Robot);
	}
	
	/**
	 * Starts a robot agent with appropriate options.<br>
	 * Access to the robot is provided through the 'Robot' variable.
	 */
	private void loadRobotAgent(){
		
		if(System.getProperty("os.name").startsWith("Windows"))
			return;
		
		if(Robot == null)
			Robot = (Robot) CASAProcess.startAgent(UI, Robot.class,
				"Dayton",
				RobotPort,
				"LAC", "9000",
				"PROCESS", "CURRENT",
				"TRACETAGS", tracetags,
				"TRACE", "10",
				"MARKUP", "KQML",
				"OUTSTREAM",SerialLocation, 
                "INSTREAM", SerialLocation,
                "INTERFACE", "NONE"
				);
	}
	
	/**
	 * Starts a robot agent with appropriate options.<br>
	 * Access to the robot is provided through the 'Robot' variable.
	 */
	@SuppressWarnings("unused")
	private void loadRobotAgent_WithConsole(){
		
		if(System.getProperty("os.name").startsWith("Windows"))
			return;
		
		if(Robot == null)
			Robot = (Robot) CASAProcess.startAgent(UI, Robot.class,
				"Dayton",
				RobotPort,
				"LAC", "9000",
				"PROCESS", "CURRENT",
				"TRACETAGS", tracetags,
				"TRACE", "10",
				"MARKUP", "KQML",
				"OUTSTREAM",SerialLocation, 
                "INSTREAM", SerialLocation
				);
	}
	
	/**
	 * Starts a robot agent with appropriate options.<br>
	 * Access to the robot is provided through the 'Robot' variable.
	 */
	private void loadSimulatorAgent(){
		if(Robot == null)
			Robot = (Simulator) CASAProcess.startAgent(UI, Simulator.class,
				"Cutesy",
				RobotPort,
				"LAC", "9000",
				"PROCESS", "CURRENT",
				"TRACETAGS", tracetags,
				"TRACE", "10",
				"MARKUP", "KQML",
				"OUTSTREAM","sim.out", 
		        "INSTREAM", "sim.in",
		        "INTERFACE", "NONE"
				);
	}
	
	/**
	 * Starts a environment agent with appropriate options.<br>
	 * Access to the robot is provided through the 'Environment' variable.
	 */
	private void loadEnvironment(){
		
		if(Environment == null)
			Environment = CASAProcess.startAgent(UI, SimEnvironment.class,
				"SimEnvironment",
				EnvironmentPort,
				"LAC", "9000",
				"PROCESS", "CURRENT",
				"TRACETAGS", tracetags,
				"TRACE", "10",
				"MARKUP", "KQML"
				);
	}
	
}
