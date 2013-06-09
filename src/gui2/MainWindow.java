package gui2;

import javax.swing.*;

import java.awt.BorderLayout;

public class MainWindow extends JFrame {
	
	public MainWindow() {
		
		menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		editorPane = new EditorPane();
		getContentPane().add(editorPane, BorderLayout.CENTER);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JTabbedPane editorPane;
	private static MainWindow instance;
	
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
