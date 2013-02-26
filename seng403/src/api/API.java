package api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import casa.CASAProcess;
import casa.ProcessOptions;
import casa.TransientAgent;
import casa.ui.AgentUI;
import casa.ui.StandardOutAgentUI;

public class API implements API_Interface {

	private CASAProcess CASA = null;
	private TransientAgent Environment = null;
	private TransientAgent Robot = null;
	
	public static void main(String[] args) {
		
		API api = new API();
		api.initalize();
		api.loadToSimulator("example.lisp");
	}
	
	public API() {}
	
	@Override
	public boolean initalize()	{
		try {
			CASA = CASAProcess.getInstance();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean authenticate_user(String user_name, String password) {
		
		// TODO Implement authentication check to external system
		if(user_name == "test" && password == "1234") return true;
		return false;
	}

	@Override
	public String loadToRobot(String filepath) {
		
		// Can be create the same as the simulator, but do not create the environment.
		// The INSTREAM and OUTSTEAM need to be the same file the represents the port location of the actual robot.
		
		return "Not yet implemented";		
	}

	@Override
	public String loadToSimulator(String filepath) {
		
		
		// Agent and process options
		
		String tracetags ="info5,warning,msg,iRobot,-boundSymbols,-policies9,-commitments,-eventqueue,-conversations";
		
		ProcessOptions options = new ProcessOptions(CASA);
		options.traceTags = tracetags;
		options.tracing = true;
		
		CASA.setOptions(options);
		
		// UI instantiation
		
		AgentUI ui = new StandardOutAgentUI();
		
		// Start simulator environment
		
		Environment = CASAProcess.startAgent(ui, SimEnvironment.class,
				"SimEnvironment",
				5780,
				"LAC", "9000",
				"PROCESS", "CURRENT",
				"TRACETAGS", tracetags,
				"TRACE", "10",
				"MARKUP", "KQML"
				);
		
		// Start the sim robot
		
		Robot = CASAProcess.startAgent(ui, Simulator.class,
				"Cutesy",
				5781,
				"LAC", "9000",
				"PROCESS", "CURRENT",
				"TRACETAGS", tracetags,
				"TRACE", "10",
				"MARKUP", "KQML",
				"OUTSTREAM","sim.out", 
                "INSTREAM", "sim.in"
				);
		
		// Run code found in file at filepath
		
		Robot.abclEval(fileRead(filepath), null);
		
		
		return null;		
	}	
	
	@Override
	public String translateLoadToRobot(String filepath) {
		// TODO Implement translateLoadToRobot
		return "NOT YET IMPLEMENTED";
	}

	@Override
	public String translateLoadToSimulator(String filepath) {
		// TODO Implement translateLoadToSimulator
		return "NOT YET IMPLEMENTED";
	}
	
	/**
	 * Reads the file at filepath and returns the contents as a string
	 * @param filepath
	 * @return a String of the file contents
	 */
	private String fileRead(String filepath){
				
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			
			StringBuilder builder = new StringBuilder();
			
			String line = null;
			while((line = reader.readLine()) != null)
			{
				builder.append(" " + line);				
			}
			
			reader.close();
			
			return builder.toString();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return null;
		
	}
	
}
