package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;

import casa.CASAProcess;
import casa.ProcessOptions;
import casa.TransientAgent;
import casa.ui.AgentUI;
import casa.ui.StandardOutAgentUI;

public class API implements API_Interface {

	/* 
	 * CASA Object variables
	 */
	private CASAProcess CASA = null;
	private TransientAgent Environment = null;
	private AbstractRobot Robot = null;
	private AgentUI UI = null;
		
	/*
	 * Option variables
	 */
	private static String tracetags ="info5,warning,msg,iRobot,-boundSymbols,-policies9,-commitments,-eventqueue,-conversations";
	private static String SerialLocation = "/dev/rfcomm0";
	private static int EnvironmentPort = 5780;
	private static int RobotPort = 5781;
	
	/*
	 *  TESTING MAIN... SHOULD NOT BE USED IN PRODUCTION
	 */
	public static void main(String[] args) {
		
		System.err.println("API runtime testing function called...");
		API api = new API();
		api.loadToSimulator("example.lisp");
		
	}
	
	/*
	 *****************************
	 * INITIALIZATION            *
	 *****************************
	 */
	
	/**
	 * Constructor. Calls initialize() on itself.
	 */
	public API() {
		this.initialize();
	}
	
	/**
	 * Checks if the API has been initialized<br>
	 * If not, it will be initialized.<br>
	 * The CASA process will be stared.
	 * @return true if the API is initialized, false if there is an error
	 */
	private boolean initialize()	{
		try {
			
			CASA = CASAProcess.getInstance();
			
			ProcessOptions options = new ProcessOptions(CASA);
			options.traceTags = tracetags;
			options.tracing = true;
			
			CASA.setOptions(options);
			
			UI = new StandardOutAgentUI();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 *****************************
	 * DATABASE / USER FUNCTIONS *
	 *****************************
	 */
	
	@Override
	public int authenticate_user(String user_name, String password) {
		return Authenticator.auth(user_name, password);
	}
	
	@Override
	public int getUserType(int UserID){
		
		return 0;
	}
	
	@Override
	public Map<String, String> getUserProgress(int UserID){
		
		return null;
	}
	
	@Override
	public Map<Integer, Map<String, String>> getAllUserProgress(){
		
		return null;
	}
	
	@Override
	public Vector<Integer> getAllUserIDs(){
		
		return null;
	}
	
	@Override
	public ImageIcon getLesson(int Chapter, int Lesson){
		return null;
	}
	
	public void setUserChapter(int UserID, int progress){
		
	}
	

	public void setUserChallenge(int UserID, int progress){
		
	}

	/*
	 *****************************
	 * ROBOT FUNCTIONS           *
	 *****************************
	 */
	
	@Override
	public String loadToRobot(String filepath) {
		
		loadRobotAgent();
		
		if(filepath != null && filepath != "")
			Robot.abclEval(fileRead(filepath), null);

		return null;		
	}

	@Override
	public String loadToSimulator(String filepath) {
		
		loadEnvironment();
		loadSimulatorAgent();		
		
		// Run code found in file at file path, if one exists.
		if(filepath != null && filepath != "")
			Robot.abclEval(fileRead(filepath), null);
				
		return null;		
	}	
	
	@Override
	public String translateLoadToRobot(String filepath) {
		
		loadRobotAgent();
		
		if(filepath != null && filepath != "")
			Robot.abclEval(fileRead(Translator.translateFile(filepath)), null);

		
		return "Translation not yet implemented";
	}

	@Override
	public String translateLoadToSimulator(String filepath) {

		loadEnvironment();
		loadSimulatorAgent();
		
		if(filepath != null && filepath != "")
			Robot.abclEval(fileRead(Translator.translateFile(filepath)), null);
		
		return "Translation not yet implemented";
	}
	
	@Override
	public RobotControl loadRobotController() {
		loadRobotAgent_WithConsole();
		return new RobotControl(Robot);
	}
	
	@Override
	public RobotControl loadSimulatorController() {
		loadEnvironment();
		loadSimulatorAgent();
		return new RobotControl(Robot);
	}
	
	
	/*
	 *****************************
	 * PACKAGE FUNCTIONS         *
	 *****************************
	 */
	
	
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

	
	/*
	 *****************************
	 * PRIVATE FUNCTIONS         *
	 *****************************
	 */
	
	/**
	 * Starts a robot agent with appropriate options.<br>
	 * Access to the robot is provided through the 'Robot' variable.
	 */
	private void loadRobotAgent(){
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
	private void loadRobotAgent_WithConsole(){
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
