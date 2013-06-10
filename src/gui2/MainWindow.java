package gui2;

import javax.swing.*;

import api.API;
import api.User;

import java.awt.*;
import java.awt.event.*;

import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame {
	
	public MainWindow() {
		
		//Frame setup
		setTitle("Robo-Teach");
		setResizable(false);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
		int x = (screenSize.width - getWidth()) / 2;  
		int y = (screenSize.height - getHeight()) / 2;  
		setLocation(x,y);
		
		

		// MENU
		
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		menuBar = new JMenuBar();
		topPanel.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getEditor().createTab();
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getEditor().openFile();
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExitRoboteach = new JMenuItem("Exit Robo-Teach");
		mntmExitRoboteach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				instance.dispose();
			}
		});
		mnFile.add(mntmExitRoboteach);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmToBeCompleted = new JMenuItem("To be completed");
		mnEdit.add(mntmToBeCompleted);
		
		JMenu mnRobot = new JMenu("Robot");
		menuBar.add(mnRobot);
		
		JMenuItem mntmRemoteControl = new JMenuItem("Remote Control");
		mnRobot.add(mntmRemoteControl);
		
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		menuBar.add(Box.createHorizontalGlue());

		lblUser = new JLabel();
		menuBar.add(lblUser);
		btnLogInOut = new JButton();
		
		btnLogInOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(User.isAuthenticated()){
					User.logout();
					start();
				}
				else
				{
					instance.setEnabled(false);
					new LoginWindow(JFrame.DISPOSE_ON_CLOSE);
				}
			}});
		menuBar.add(btnLogInOut);
		
		JToolBar toolBar = new JToolBar();
		topPanel.add(toolBar);
		
		JButton btnSave = new JButton("SAVE");
		toolBar.add(btnSave);

		// EDITOR TABS
		
		editorPane = new EditorPane();
		getContentPane().add(editorPane, BorderLayout.CENTER);
		
		// SIDE INTERFACE
		
		JPanel sidePanel = new JPanel();
		getContentPane().add(sidePanel, BorderLayout.WEST);
		sidePanel.setLayout(new MigLayout("", "[150px]", "[min][min][max]"));
		
		JButton btnRunSimulator = new JButton("Run on Simulator");
		btnRunSimulator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getEditor().writeToTemp();
				API.getInstance().loadToSimulator(getEditor().getTempFile().getAbsolutePath());
			}
		});
		sidePanel.add(btnRunSimulator, "cell 0 0,growx,aligny top");
		
		JButton btnRunRobot = new JButton("Run on Robot");
		btnRunRobot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getEditor().writeToTemp();
				API.getInstance().loadToRobot(getEditor().getTempFile().getAbsolutePath());
			}
		});
		sidePanel.add(btnRunRobot, "cell 0 1,growx,aligny top");		
		
		JTree tree = new JTree();
		tree.setModel(new ExamplesTreeModel());
		
		JScrollPane scrollPane = new JScrollPane(tree);

		sidePanel.add(scrollPane, "cell 0 2,alignx left,growx,growy");

	}

	private static final long serialVersionUID = 1L;
	private static MainWindow instance;
	
	public static MainWindow getInstance() {
		return instance;
	}

	private JMenuBar menuBar;
	private EditorPane editorPane;
	private JButton btnLogInOut;
	private JLabel lblUser;	
	
	public static void start()
	{
		if(instance == null)
			instance = new MainWindow();

		if(User.isAuthenticated()){
			instance.btnLogInOut.setText("Logout");
			instance.lblUser.setText("User: "+ User.getUserName() + "    ");
		} else {
			instance.btnLogInOut.setText("Login..");
			instance.lblUser.setText("Guest    ");
		}
		
		instance.setEnabled(true);
		instance.requestFocus();	
		
		instance.setVisible(true);
	}

	public static JMenuBar getMenu() {
		return instance.menuBar;
	}
	
	public static EditorPane getEditor() {
		return instance.editorPane;
	}
}
