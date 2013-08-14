package gui2;

import javax.swing.*;
import javax.swing.text.*;

import api.API;
import api.User;

import java.awt.*;
import java.awt.event.*;

import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame {
	
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
	
	public MainWindow() {
		
	//Frame setup ----------------------------------------------------------------------------
		
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

	// MENU ----------------------------------------------------------------------------
		
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		
		menuBar = new JMenuBar();
		topPanel.add(menuBar);
		
		// 	File menu
		JMenu mnFile = new JMenu("File");
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getEditor().createTab();
			}
		});
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getEditor().openFile();
			}
		});
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getEditor().saveFile();
			}
		});
		
		
		JMenuItem mntmExitRoboteach = new JMenuItem("Exit Robo-Teach");
		mntmExitRoboteach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				instance.dispose();
			}
		});
		
		mnFile.add(mntmNew);
		mnFile.add(mntmOpen);
		mnFile.add(mntmSave);
		mnFile.addSeparator();
		mnFile.add(mntmExitRoboteach);
		menuBar.add(mnFile);
		
		// Edit menu
		JMenu mnEdit = new JMenu("Edit");
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.addActionListener(new DefaultEditorKit.CutAction());
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.addActionListener(new DefaultEditorKit.CopyAction());
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mntmPaste.addActionListener(new DefaultEditorKit.PasteAction());
		
		mnEdit.add(mntmCut);
		mnEdit.add(mntmCopy);
		mnEdit.add(mntmPaste);
		menuBar.add(mnEdit);
		
		// Robot menu
		JMenu mnRobot = new JMenu("Robot");
		
		JMenuItem mntmRunSimulator = new JMenuItem("Run on Simulator");
		mntmRunSimulator.addActionListener(new RunToSimulator());
		
		JMenuItem mntmSimControl = new JMenuItem("Simulator Remote Control");
		mntmSimControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Remote(true).OpenWindow();
			}
		});
		
		JMenuItem mntmRunRobot = new JMenuItem("Run on Robot");
		mntmRunRobot.addActionListener(new RunToRobot());
		
		JMenuItem mntmRobotControl = new JMenuItem("Robot Remote Control");
		mntmRobotControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Remote(false).OpenWindow();
			}
		});
		
		mnRobot.add(mntmRunSimulator);
		mnRobot.add(mntmSimControl);
		mnRobot.addSeparator();
		mnRobot.add(mntmRunRobot);
		mnRobot.add(mntmRobotControl);
		menuBar.add(mnRobot);
		
		// Help menu
		JMenu mnHelp = new JMenu("Help");
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(instance, "This program was developed by Henry Rosvick.\n" +
						"The underlying CASA system was create and deveopled by the Knowledge Science Group (KSG), Dept. of Computer Science, University of Calgary\n" +
						"http://casa.cpsc.ucalgary.ca/");
			}
		});
		
		mnHelp.add(mntmAbout);
		menuBar.add(mnHelp);
		
		// Glue
		menuBar.add(Box.createHorizontalGlue());

		// Log in / Log out section
		lblUser = new JLabel();
		
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
		
		menuBar.add(lblUser);
		menuBar.add(btnLogInOut);

	// EDITOR TABS ----------------------------------------------------------------------------
		
		editorPane = new EditorPane();
		getContentPane().add(editorPane, BorderLayout.CENTER);
		
	// SIDE INTERFACE ----------------------------------------------------------------------------
		
		JPanel sidePanel = new JPanel();
		getContentPane().add(sidePanel, BorderLayout.WEST);
		sidePanel.setLayout(new MigLayout("", "[150px]", "[min][min][max]"));
		
		// Buttons
		JButton btnRunSimulator = new JButton("Run on Simulator");
		btnRunSimulator.addActionListener(new RunToSimulator());
		
		JButton btnRunRobot = new JButton("Run on Robot");
		btnRunRobot.addActionListener(new RunToRobot());
				
		// Examples
		JTree tree = new JTree();
		tree.setModel(new ExamplesTreeModel());
		tree.setDragEnabled(true);
		tree.setTransferHandler(new ExamplesTransferHandler());
		
		JScrollPane scrollPane = new JScrollPane(tree);
		
		sidePanel.add(btnRunSimulator, "cell 0 0,growx,aligny top");
		sidePanel.add(scrollPane, "cell 0 2,alignx left,growx,growy");
		sidePanel.add(btnRunRobot, "cell 0 1,growx,aligny top");
		
	}

	private class RunToSimulator implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getEditor().writeToTemp();
			
			NotificationWindow.createNotice("Loading Simulator\nPlease wait...");

			Thread t = new Thread(new Runnable() {
		         public void run()
		         {
		              API.getInstance().loadToSimulator(getEditor().getTempFile().getAbsolutePath());
		              NotificationWindow.closeNotice();
		         }
			});
			
			t.start();
		}
	}
	
	private class RunToRobot implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getEditor().writeToTemp();
			
			NotificationWindow.createNotice("Connecting to Robot\nPlease wait...");
			
			Thread t = new Thread(new Runnable() {
		         public void run()
		         {
		              API.getInstance().loadToRobot(getEditor().getTempFile().getAbsolutePath());
		              NotificationWindow.closeNotice();
		         }
			});
			
			t.start();
		}
	}

	
}
