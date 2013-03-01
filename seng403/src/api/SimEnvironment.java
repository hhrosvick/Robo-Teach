package api;

import java.awt.Container;

import casa.TransientAgent;
import casa.abcl.ParamsMap;
import casa.ui.AgentUI;
import casa.ui.TransientAgentInternalFrame;

public class SimEnvironment extends iRobotCreate.simulator.Environment{

	public SimEnvironment(ParamsMap params, AgentUI ui) throws Exception {
		super(params, ui);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected TransientAgentInternalFrame makeDefaultInternalFrame(TransientAgent agent, String title, Container aFrame) {
		return null;
	 	//return new AgentInternalFrame((Agent)agent, title, aFrame);
	}

}
