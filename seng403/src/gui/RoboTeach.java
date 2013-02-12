package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import cpsc403.MyRobot;
import casa.CASAProcess;
import casa.Status;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import casa.abcl.ParamsMap;
import casa.ui.AgentUI;
import iRobotCreate.iRobotCreate;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTree;

public class RoboTeach {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoboTeach window = new RoboTeach();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RoboTeach() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 432, 255);
		frame.getContentPane().add(tabbedPane);
		
		JPanel ProgramPanel = new JPanel();
		tabbedPane.addTab("Program", null, ProgramPanel, null);
		ProgramPanel.setLayout(null);
		
		JButton btnStartEmulator = new JButton("Start Emulator");
		btnStartEmulator.setBounds(37, 96, 145, 33);
		ProgramPanel.add(btnStartEmulator);
		
		JPanel LessonsPanel = new JPanel();
		tabbedPane.addTab("Lessons", null, LessonsPanel, null);
		LessonsPanel.setLayout(null);
		
		JPanel ChallengesPanel = new JPanel();
		tabbedPane.addTab("Challenges", null, ChallengesPanel, null);
		ChallengesPanel.setLayout(null);
		
		btnStartEmulator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("ButtonPressed");
				Status p = CASAProcess.getInstance().abclEval("(load\"scripts/sim.lisp\")", null);
			}
		});
	}
}
