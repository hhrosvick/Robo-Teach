package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
	@SuppressWarnings("unused")
	private TransientAgent Environment = null;
	private AbstractRobot Robot = null;
	private AgentUI UI = null;
		
	/*
	 * User and Database Information
	 */
	private int UserID;
	private Database DB;
	
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
	public static void main(String[] args) throws Exception {
		
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
	 * @throws Exception 
	 */
	public API() throws Exception {
		this.initialize();
	}
	
	/**
	 * Checks if the API has been initialized<br>
	 * If not, it will be initialized.<br>
	 * The CASA process will be stared.
	 * @return true if the API is initialized, false if there is an error
	 * @throws Exception 
	 */
	public boolean initialize() throws Exception{
		try {
			
			CASA = CASAProcess.getInstance();
			
			ProcessOptions options = new ProcessOptions(CASA);
			options.traceTags = tracetags;
			options.tracing = true;
			
			CASA.setOptions(options);
			
			UI = new StandardOutAgentUI();
			
			DB = Database.getInstance();
						
			return true;
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw new Exception("API Initialization Failed.");
		}
		
	}

	/*
	 *****************************
	 * DATABASE / USER FUNCTIONS *
	 *****************************
	 */
	
	@Override
	public int authenticate_user(String user_name, String password) {
		
		UserID = Authenticator.auth(user_name, password);
		
		// Check is the user exists in the database, create an entry if not.
		
		return UserID;
	}
	
	@Override
	public int getUserType(int UserID){
		
		String query = "SELECT 'type' FROM sql24765.users WHERE 'student_ID'=" + String.valueOf(UserID);
		String type = "";
		
		try {
			ResultSet response = DB.query(query);
			if(response.next() && response.getInt(1) == 0)
				return 0;
			type = response.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(type.compareTo("teacher") == 0)
			return 1;
		else if(type.compareTo("student") == 0)
			return 2;
		else
			return 0;
	}
	
	@Override
	public Map<String, String> getUserProgress(int UserID){
		Map<String,String> m = new HashMap<String,String>();
		
		try {			
			String q = "SELECT * FROM sql24765.users";
			ResultSet r1 = DB.query(q);
			r1.next();
			String query = "SELECT * FROM sql24765.users WHERE 'student_ID' =" + String.valueOf(UserID);
			ResultSet response = DB.query(query);
			
		
			if(response.next() && response.getInt(1) == 0)
				return null;
			int chpt = 0;
			int cl = 0;
			int size = 0;
			
			while(!r1.isAfterLast()){
				
				chpt+=r1.getInt(5);
				cl+=r1.getInt(6);
				size++;
				r1.next();
			}
			
			double avgchpt = chpt/size;
			double avgcl = cl/size;
			
			
			m.put("id",String.valueOf(response.getInt(3)));
			m.put("name",response.getString(2));
			m.put("chapter", String.valueOf((int)response.getInt(5)));
			m.put("challenge", String.valueOf((int)response.getInt(6)));
			m.put("avgchapter", String.valueOf((double)avgchpt));
			m.put("avgchallenge", String.valueOf((double)avgcl));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m;
		
		
//		// FAKED OUTPUT
//		Map<String,String> m = new HashMap<String, String>();
//		
//		if(UserID == 111111){
//			m.put("id","111111");
//			m.put("name","Alice");
//			m.put("chapter","3");
//			m.put("challenge","1");
//			m.put("avgchapter","2.5");
//			m.put("avgchallenge","1.5");
//		}
//		else if(UserID == 222222){
//			m.put("id","222222");
//			m.put("name","Bob");
//			m.put("chapter","2");
//			m.put("challenge","2");
//			m.put("avgchapter","2.5");
//			m.put("avgchallenge","1.5");
//		}
//		else {
//			m.put("id","000000");
//			m.put("name","Class Average");
//			m.put("chapter","0");
//			m.put("challenge","0");
//			m.put("avgchapter","2.5");
//			m.put("avgchallenge","1.5");
//		}
//		
//		return m;
	}
	
	@Override
	public Map<Integer, Map<String, String>> getAllUserProgress(){
		
		Map<Integer, Map<String,String>> m = new HashMap<Integer, Map<String,String>>();
		Map<String,String> average = new HashMap<String, String>();
		
		String query = "SELECT * FROM sql24765.users";
		try {
			ResultSet response = DB.query(query);
			
			if(response.next() && response.getInt(1) == 0)
				return null;
			
			int chpt = 0;
			int cl = 0;
			int size = 0;
		
			while(!response.isAfterLast()){
					
				chpt+=response.getInt(5);
				cl+=response.getInt(6);
				size++;
					
				
				Map<String,String> d = new HashMap<String, String>();
					
				d.put("name", response.getString(2));
				d.put("chapter", String.valueOf((int)response.getInt(5)));			//d.put("chapter",lesson_complete)
				d.put("challenge", String.valueOf((int)response.getInt(6)));		//d.put("challenge", challenge_complete)
				
				m.put(response.getInt(3), d);		//m.put(student_ID, Map object)
				response.next();
			}
			
			double avgchpt = chpt/size;
			double avgcl = cl/size;
			
			average.put("name","average");
			average.put("chapter",String.valueOf((double)avgchpt));
			average.put("challenge",String.valueOf((double)avgcl));
			
			m.put(0,average);
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return m;
		
//		// FAKED OUTPUT
//		Map<Integer, Map<String,String>> m = new HashMap<Integer, Map<String, String>>();
//		Map<String,String> a = new HashMap<String, String>();
//		Map<String,String> b = new HashMap<String, String>();
//		Map<String,String> c = new HashMap<String, String>();
//		Map<String,String> D = new HashMap<String, String>();
//		Map<String,String> J = new HashMap<String, String>();
//		Map<String,String> M = new HashMap<String, String>();
//		Map<String,String> S = new HashMap<String, String>();
//		
//		a.put("name","Alice");
//		a.put("chapter","3");
//		a.put("challenge","1");
//	
//		b.put("name","Bob");
//		b.put("chapter","2");
//		b.put("challenge","2");
//
//		c.put("name","Class Average");
//		c.put("chapter","2.2");
//		c.put("challenge","1.5");
//		
//		D.put("name","David");
//		D.put("chapter","1");
//		D.put("challenge","0");
//		
//		J.put("name","Jobelle");
//		J.put("chapter","2");
//		J.put("challenge","1");
//		
//		M.put("name","Marshall");
//		M.put("chapter","1");
//		M.put("challenge","2");
//		
//		S.put("name","Sonny");
//		S.put("chapter","4");
//		S.put("challenge","4");
//		
//		m.put(111111, a);
//		m.put(222222, b);
//		m.put(333333, D);
//		m.put(444444, J);
//		m.put(555555, M);
//		m.put(666666, S);
//		m.put(0, c);
//		
//		return m;
	}
	
	@Override
	public Vector<Integer> getAllUserIDs(){
		
		Vector<Integer> v = new Vector<Integer>();
		String query = "SELECT * FROM sql24765.users";
		try {
			ResultSet response = DB.query(query);
			response.next();
			if(response.getInt("student_ID") == 0)
				return null;
			
			while(!response.isAfterLast()){
				
				v.add(response.getInt("student_ID"));
				response.next();
				
			}
			}catch (SQLException e){
				e.printStackTrace();
			}

		return v;
		
//		// FAKED OUTPUT
//		Vector<Integer> v = new Vector<Integer>();
//		v.add(111111);
//		v.add(222222);
//		v.add(333333);
//		v.add(444444);
//		v.add(555555);
//		v.add(666666);
//			
//		return v;
	}
	
	@Override
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
		
		return new ImageIcon(imgStr);
	}
	@Override
	public ImageIcon getUserManual(int Slide)
	{
		return null;
		
	}
	@Override
	public ImageIcon getChallenge(int tier, int number, boolean Slide){

		String imgStr = "Challenges/";
		
		if(tier == 0)
			return null;
		
		if(number == 0)
			imgStr += "Tier " + tier;
		else if(!Slide)
			imgStr += "Challenge " + tier + "-" + number;
		else
			imgStr += "ChallengeSlides/Challenge " + number + "-" + Slide;
		
		imgStr += ".png";
				
		return new ImageIcon(imgStr);
	}
	
	public void setUserChapter(int UserID, int progress) throws Exception{
		
		String query = "UPDATE sql24765.users SET 'lesson_complete'=" + String.valueOf(progress) + " WHERE 'student_ID'=" + String.valueOf(UserID);
		ResultSet response = DB.query(query);
		if(response.next() && response.getInt(1) == 0)
			throw new Exception("No user with the ID " + UserID);
	}
	

	public void setUserChallenge(int UserID, int progress) throws Exception{
		
		String query = "UPDATE sql24765.users SET 'challenge_complete'=" + String.valueOf(progress) + " WHERE 'student_ID'=" + String.valueOf(UserID);
		ResultSet response = DB.query(query);
		if(response.next() && response.getInt(1) == 0)
			throw new Exception("No user with the ID " + UserID);
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
