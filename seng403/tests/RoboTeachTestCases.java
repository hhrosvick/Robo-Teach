import static org.junit.Assert.*;

import java.awt.Frame;

import javax.swing.JFrame;

import gui.ChallengeWindow;
import gui.LessonWindow;
import gui.LessonsTab;
import gui.Login;
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
		LessonsTab lessonsTestTab = new LessonsTab(0);
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
	/*
	@Test
	public void LoginInitializationTest() {
		JFrame DummyWindow = new JFrame();
		Login loginTestWindow = new Login(DummyWindow);
		assert(loginTestWindow != null);
	}
	*/
	@Test
	public void LessonWindowInitializationTest() {
		LessonWindow lessonTestWindow = new LessonWindow(0, 0, "test");
		assert(lessonTestWindow != null);
	}
	@Test
	public void ChallengeWindowInitializationTest() {
		ChallengeWindow challengeTestWindow = new ChallengeWindow(0, 0, "test");
		assert(challengeTestWindow != null);
	}
	@Test
	public void LessonWindowStringTest() {
		LessonWindow lessonTestWindow = new LessonWindow(0, 0, "StringTest");
		assert(((Frame) lessonTestWindow.getFrame()).getTitle().compareTo("StringTest") == 0);
	}
	@Test
	public void ChallengeWindowStringTest() {
		ChallengeWindow challengeTestWindow = new ChallengeWindow(0, 0, "StringTest");
		assert(challengeTestWindow.getFrame().getTitle().compareTo("StringTest") == 0);
	}
}
