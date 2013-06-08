package gui2;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import java.awt.FlowLayout;

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
		
		menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExitRoboteach = new JMenuItem("Exit Robo-Teach");
		mnFile.add(mntmExitRoboteach);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		menuBar.add(Box.createHorizontalGlue());
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(mnHelp);
		
		// EDITOR TABS
		
		editorPane = new EditorPane();
		getContentPane().add(editorPane, BorderLayout.CENTER);
		
		
		// SIDE INTERFACE
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new MigLayout("", "[150px]", "[min][min][max]"));
		
		JButton btnRunSimulator = new JButton("Run on Simulator");
		panel.add(btnRunSimulator, "cell 0 0,growx,aligny top");
		
		JButton btnRunRobot = new JButton("Run on Robot");
		panel.add(btnRunRobot, "cell 0 1,growx,aligny top");
		
		JTree tree = new JTree();
		//tree.setMinimumSize(new Dimension(200,0));
		tree.setModel(new ExamplesTreeModel());
		
		JScrollPane scrollPane = new JScrollPane(tree);
		//scrollPane.setPreferredSize(new Dimension(150,0));

		panel.add(scrollPane, "cell 0 2,alignx left,growx,growy");
		
		
	}

	private static final long serialVersionUID = 1L;
	private static MainWindow instance;
	
	private JMenuBar menuBar;
	private JTabbedPane editorPane;	
	private JPasswordField passwordField;
	private JTextField txtUsername;
	
	public static void start()
	{
		if(instance == null)
			instance = new MainWindow();
		else
			instance.requestFocus();
		
		instance.setVisible(true);
	}

	public JMenuBar getMenu() {
		return menuBar;
	}
	
	public JTabbedPane getEditor() {
		return editorPane;
	}
}
