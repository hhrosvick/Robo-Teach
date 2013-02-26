package api;

import casa.CASAProcess;
import casa.Status;
import casa.abcl.ParamsMap;

public class API implements API_Interface {

	private CASAProcess CASA = null;
	
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
		CASA.abclEval("(load \"/scripts/startsimulatorevironment.lisp\")", null);
		CASA.abclEval("(load \"/scripts/startsimulatorrobot.lisp\")", null);
		
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
	
}
