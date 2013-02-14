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

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class RoboTeach {
/*
 * Jobelle Firmeeeeeeeeeeee
 */
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
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.getContentPane().setLayout(null);
		
// Creating home panel, set to CardLayout		
		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 255);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
// Creating MainPage panel, added to home panel		
		JPanel MainPage = new JPanel();
		panel.add(MainPage, "MainPage");
		MainPage.setLayout(null);
		
// StartButton initialisation and ActionListener
		// On click opens TabPage
		JButton StartButton = new JButton("Start");
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) panel.getLayout()).show(panel, "TabPage");
			}
		});
		StartButton.setBounds(61, 180, 120, 25);
		MainPage.add(StartButton);
		
// UserManualButton initialisation and ActionListener
		// On click open User Manual
		JButton UserManualButton = new JButton("User Manual");
		UserManualButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		UserManualButton.setBounds(250, 180, 120, 25);
		MainPage.add(UserManualButton);
		
// WelcomeTitleLabel initialisation
		// Set Banner here
			String imgStr = "Pic.png";
		ImageIcon image = new ImageIcon(imgStr);
		JLabel WelcomeTitleLabel = new JLabel(" ", image, JLabel.CENTER);
		WelcomeTitleLabel.setBounds(12, 13, 408, 229);
		
		MainPage.add(WelcomeTitleLabel);
		
// TabPage initialisation
		JTabbedPane TabPage = new JTabbedPane(JTabbedPane.TOP);
		panel.add(TabPage, "TabPage");
		
/*****************************************************************************************************************************************
 * WelcomeTab initialisation and components
 *****************************************************************************************************************************************/
		JPanel WelcomeTab = new JPanel();
		TabPage.addTab("Welcome", null, WelcomeTab, null);
		WelcomeTab.setLayout(null);
		
		JButton BackToMenu = new JButton("Back");
		BackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) panel.getLayout()).show(panel, "MainPage");
			}
		});
		BackToMenu.setBounds(12, 187, 97, 25);
		WelcomeTab.add(BackToMenu);
		
/*****************************************************************************************************************************************
* ProgramTab initialisation and components
******************************************************************************************************************************************/
		JPanel ProgramTab = new JPanel();
		TabPage.addTab("Program", null, ProgramTab, null);
		ProgramTab.setLayout(null);
		
		JButton btnStartEmulator = new JButton("Start Emulator");
		btnStartEmulator.setBounds(0, 13, 145, 33);
		ProgramTab.add(btnStartEmulator);
		
		btnStartEmulator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("ButtonPressed");
				Status p = CASAProcess.getInstance().abclEval("(load\"scripts/sim.lisp\")", null);
			}
		});
/*****************************************************************************************************************************************
* LessonsTab initialisation and components
******************************************************************************************************************************************/		
		JPanel LessonsTab = new JPanel();
		TabPage.addTab("Lessons", null, LessonsTab, null);
		LessonsTab.setLayout(null);
		
		JTree tree = new JTree();
		tree.setBounds(12, 65, 77, 64);
		LessonsTab.add(tree);
		
/*****************************************************************************************************************************************
* LessonsTab initialisation and components
******************************************************************************************************************************************/	
		JPanel ChallengesTab = new JPanel();
		TabPage.addTab("Challenges", null, ChallengesTab, null);
		ChallengesTab.setLayout(null);
		

	}
}
