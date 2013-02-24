package cpsc403;

import casa.CASAProcess;
import casa.Status;

public class API implements API_Interface {

	public API() {
		// TODO Auto-generated constructor stub
	}
	
	public Status startEmulator()	{
		return CASAProcess.getInstance().abclEval("(load\"scripts/sim.lisp\")", null);
	}

}
