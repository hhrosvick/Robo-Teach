package api;

import casa.CASAProcess;
import casa.Status;

public class API implements API_Interface {

	private CASAProcess CASA = null;
	private CASA_Interface Robot_Agent = null;
	
	public static void main(String[] args) {
		
		API api = new API();
		api.initialize();
		api.loadToSimulator("blah");
	}
	
	public API() {
		// TODO Auto-generated constructor stub
	}
	
	public Status initialize()	{
		CASA = CASAProcess.getInstance();
		return CASA.abclEval("(load\"scripts/sim.lisp\")", null);
	}

	@Override
	public Status initalaize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authenticate_user(String user_name, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String loadToRobot(String filepath) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public String loadToSimulator(String filepath) {
		
		
		System.out.println(CASA.getAgentName());
		
		return null;
		// TODO 
		
	}

	@Override
	public String translateLoadToRobot(String filepath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String translateLoadToSimulator(String filepath) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
