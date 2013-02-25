package cpsc403;

import casa.CASAProcess;
import casa.Status;

public class API implements API_Interface {

	public API() {
		// TODO Auto-generated constructor stub
	}
	
	public Status initialize()	{
		return CASAProcess.getInstance().abclEval("(load\"scripts/sim.lisp\")", null);
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
	public void loadToRobot(String filepath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadToSimulator(String filepath) {
		// TODO Auto-generated method stub
		
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
