package api;

import java.awt.Container;

import javax.swing.JPanel;

import casa.CASAProcess;
import casa.ProcessOptions;
import casa.Status;
import casa.TransientAgent;
import casa.abcl.ParamsMap;
import casa.ui.AgentUI;
import casa.ui.StandardOutAgentUI;

public class API implements API_Interface {

	private CASAProcess CASA = null;
	private TransientAgent Environment = null;
	private TransientAgent Robot = null;
	
	public static void main(String[] args) {
		
		API api = new API();
		api.initalize();
		api.loadToSimulator("blah");
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
		return "Not yet implemented";		
	}

	@Override
	public String loadToSimulator(String filepath) {
		
		// Start simulator environment
				
		String tracetags ="info5,warning,msg,iRobot,-boundSymbols,-policies9,-commitments,-eventqueue,-conversations";
		ProcessOptions options = new ProcessOptions(CASA);
		options.traceTags = tracetags;
		options.tracing = true;
		
		CASA.setOptions(options);
		
		AgentUI ui = new StandardOutAgentUI();
		Environment = CASAProcess.startAgent(ui, SimEnvironment.class,
				"SimEnvironment",
				5780,
				"LAC", "9000",
				"PROCESS", "CURRENT",
				"TRACETAGS", tracetags,
				"TRACE", "10",
				"MARKUP", "KQML"
				);
		
		Robot = CASAProcess.startAgent(ui, Simulator.class,
				"Cutesy",
				5781,
				"LAC", "9000",
				"PROCESS", "CURRENT",
				"TRACETAGS", tracetags,
				"TRACE", "10",
				"MARKUP", "KQML",
				"OUTSTREAM","sim.out", 
                "INSTREAM", "sim.in",
                "INTERFACE", "none"
				);
		
		// TEST DRIVE!!!
		Robot.abclEval("(irobot.drive 100 50)", null);
		
		
		return "Cutesy should be driving like my mother by now...";		
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
}
