package gui2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Font;

public class NotificationWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private static NotificationWindow currentNotice = null;
	
	public static void createNotice(String message) {
		
		createNotice(message, null, JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void createNotice(String message, JPanel buttons, int CloseOperation) {
		
		if(currentNotice != null)
			closeNotice();
			
		currentNotice = new NotificationWindow(message, buttons, CloseOperation);
		currentNotice.setVisible(true);
		currentNotice.requestFocus();
	}
	
	public static void closeNotice() {
		currentNotice.setVisible(false);
		currentNotice.dispose();
	}

	/**
	 * @wbp.parser.constructor
	 */
	private NotificationWindow(String message, JPanel buttons, int CloseOperation)
	{
		setType(Type.POPUP);
		setAlwaysOnTop(true);
		
		this.addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {
				
				arg0.getWindow().requestFocus();
			}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});
		
		
		//Frame setup
		setTitle("Robo-Teach: Notice");
		setResizable(false);
		setSize(300,200);
		setDefaultCloseOperation(CloseOperation);
		setUndecorated(true);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
		int x = (screenSize.width - getWidth()) / 2;  
		int y = (screenSize.height - getHeight()) / 2;  
		setLocation(x,y);

		//Panel setup
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JTextArea txtNotificationArea = new JTextArea(message);
		txtNotificationArea.setWrapStyleWord(true);
		txtNotificationArea.setLineWrap(true);
		txtNotificationArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtNotificationArea.setEditable(false);
		mainPanel.add(txtNotificationArea, BorderLayout.CENTER);
		
		
		JPanel topBorder = new JPanel();
		topBorder.setBackground(Color.GRAY);
		mainPanel.add(topBorder, BorderLayout.NORTH);
		
		JPanel bottomBorder = new JPanel();
		bottomBorder.setBackground(Color.GRAY);
		mainPanel.add(bottomBorder, BorderLayout.SOUTH);
		
		JPanel leftBorder = new JPanel();
		leftBorder.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(leftBorder, BorderLayout.WEST);
		
		JPanel rightBorder = new JPanel();
		rightBorder.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(rightBorder, BorderLayout.EAST);
		
		if(buttons != null) {
			JPanel buttonPanel = buttons;
			buttonPanel.setBounds(0, 123, 294, 49);
			mainPanel.add(buttonPanel);
		}

		System.out.println(txtNotificationArea.getText());	
	}
}
