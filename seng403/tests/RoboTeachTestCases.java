import static org.junit.Assert.*;
import gui.LessonsTab;
import gui.ProgramTab;
import gui.RoboTeach;

import org.junit.Test;


public class RoboTeachTestCases {

	@Test
	public void RoboTeachInitializationTest() {
		RoboTeach roboTestWindow = new RoboTeach();
		assert(roboTestWindow != null);
	}
	@Test
	public void LessonsTabInitializationTest() {
		LessonsTab lessonsTestTab = new LessonsTab();
		assert(lessonsTestTab != null);
	}
	@Test
	public void ProgramTabInitializationTest() {
		ProgramTab programTestTab = new ProgramTab();
		assert(programTestTab != null);
	}
	@Test
	public void ChallengesTabInitializationTest() {
		ProgramTab challengesTestTab = new ProgramTab();
		assert(challengesTestTab != null);
	}

}
