package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.ImageIcon;

import casa.CASAProcess;
import casa.ProcessOptions;
import casa.TransientAgent;
import casa.ui.AgentUI;
import casa.ui.StandardOutAgentUI;

public class API {

	/*
	 * Static variables
	 */
	
	private static API Instance = null;
	
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
	 *****************************
	 * INITIALIZATION            *
	 *****************************
	 */
	
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
	
	public static  API getInstance(){
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
	 *****************************
	 * DATABASE / USER FUNCTIONS *
	 *****************************
	 */
	
	
	
	
	
	/**
	 * Returns an ImageIcon object for the specified chapter and lesson.
	 * @param Chapter The number of the chapter needed. Pass 0 (zero) into all other parameters if just the chapter imaged is needed.
	 * @param Lesson The number of the lesson needed. Pass 0 (zero) into the slide parameter if just the chapter/lesson imaged is needed.
	 * @param Slide The number of the slide needed. 
	 * @return the ImageIcon object
	 */
	public ImageIcon getLesson(int Chapter, int Lesson, int Slide){
				
		String imgStr = "Lessons/";
		
		if(Chapter == 0)
			return new ImageIcon("Usermanual/UM " + Slide + ".png");
		
		if(Lesson == 0)
			imgStr += "Chapter " + Chapter;
		else if(Slide == 0)
			imgStr += "Lesson " + Chapter + "-" + Lesson;
		else
			imgStr += "LessonSlides/Lesson "+ Chapter + "-" + Lesson + "-" + Slide;
		
		imgStr += ".png";
		
		File image = new File(imgStr);
		
		if(image.exists())
			return new ImageIcon(imgStr);
		else
			return null;
	}

	/**
	 * Returns an ImageIcon object for the user manual.
	 * @param Slide The number of the slide needed.
	 * @return the ImageIcon object
	 */
	public ImageIcon getUserManual(int Slide)
	{
		String imgStr = "UserManual/";
		
		
		if(Slide == 0)
			return null;
		else
			imgStr += "UserManual " + Slide;
		
		imgStr += ".png";

		File image = new File(imgStr);
		
		if(image.exists())
			return new ImageIcon(imgStr);
		else
			return null;		
	}

	/**
	 * Returns an ImageIcon object for the specified challenge.
	 * @param Tier The number of the Tier needed. Pass 0 (zero) or false into all other parameters if just the tier imaged is needed.
	 * @param Number The challenge number needed. Pass false into the slide parameter if just the Tier/Number imaged is needed.
	 * @param Slide true is the slide should be returned, false otherwise.
	 * @return the ImageIcon object
	 */
	public ImageIcon getChallenge(int tier, int number, boolean Slide){

		String imgStr = "Challenges/";
		
		if(tier == 0)
			return null;
		
		if(number == 0)
			imgStr += "Tier " + tier;
		else if(!Slide)
			imgStr += "Challenge " + tier + "-" + number;
		else
			imgStr += "ChallengeSlides/Challenge " + tier + "-" + number;
		
		imgStr += ".png";

		File image = new File(imgStr);
		
		if(image.exists())
			return new ImageIcon(imgStr);
		else
			return null;
	}
	

	/*
	 *****************************
	 * ROBOT FUNCTIONS           *
	 *****************************
	 */
	
	/**
	 * Loads the code found at 'filepath' to the robot.
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
	 * Loads the code found at 'filepath' to the simulator.
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
