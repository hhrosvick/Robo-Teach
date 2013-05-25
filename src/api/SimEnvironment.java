package api;

import java.awt.Container;

import casa.TransientAgent;
import casa.abcl.ParamsMap;
import casa.ui.AgentUI;
import casa.ui.TransientAgentInternalFrame;
//import casa.Agent;
//import casa.ui.AgentInternalFrame;

public class SimEnvironment extends iRobotCreate.simulator.Environment{

	public SimEnvironment(ParamsMap params, AgentUI ui) throws Exception {
		super(params, ui);
	}
	
	/**
	 * Ensures the environment loads without a second window interface.
	 */
	@Override
	protected TransientAgentInternalFrame makeDefaultInternalFrame(TransientAgent agent, String title, Container aFrame) {
		return null;
	 	//return new AgentInternalFrame((Agent)agent, title, aFrame);
	}

}
