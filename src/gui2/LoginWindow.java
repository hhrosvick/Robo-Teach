package gui2;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

import api.User;

import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args){
		
		new LoginWindow();
	}

	LoginWindow()
	{
		this(JFrame.EXIT_ON_CLOSE);
	}
	
	LoginWindow(int CloseOperation)
	{
		
		//Frame setup
		setTitle("Robo-Teach: Login");
		setResizable(false);
		setSize(300,200);
		setDefaultCloseOperation(CloseOperation);
		
		if(MainWindow.getInstance() != null)
		{
			this.addWindowListener(new WindowListener() {
				public void windowActivated(WindowEvent arg0) {}
				public void windowClosed(WindowEvent arg0) { 
					MainWindow.start();
				}
				public void windowClosing(WindowEvent arg0) {}
				public void windowDeactivated(WindowEvent arg0) {}
				public void windowDeiconified(WindowEvent arg0) {}
				public void windowIconified(WindowEvent arg0) {}
				public void windowOpened(WindowEvent arg0) {}
				});
		}
		
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
		int x = (screenSize.width - getWidth()) / 2;  
		int y = (screenSize.height - getHeight()) / 2;  
		setLocation(x,y);

		//Panel setup
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		final JLabel lblError = new JLabel();
		lblError.setBounds(151, 5, 133, 14);
		lblError.setForeground(Color.red);
		lblError.setVisible(false);
		mainPanel.add(lblError);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(151, 24, 133, 14);
		mainPanel.add(lblUsername);
		
		final JTextField textUsername = new JTextField(15);
		textUsername.setBounds(151, 43, 133, 20);
		mainPanel.add(textUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(151, 68, 133, 14);
		mainPanel.add(lblPassword);
		
		final JPasswordField textPassword = new JPasswordField(15);
		textPassword.setBounds(151, 87, 133, 20);
		mainPanel.add(textPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(151, 112, 133, 23);
		mainPanel.add(btnLogin);
		
		JButton btnSkipLogin = new JButton("Skip...");
		btnSkipLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				User.logout();
				MainWindow.start();
				((JFrame) ((JComponent) event.getSource()).getTopLevelAncestor()).dispose();
			}
		});
		btnSkipLogin.setBounds(151, 140, 133, 23);
		mainPanel.add(btnSkipLogin);
	
		JLabel lblImage = new JLabel("Robo-Teach Logo");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		// C:\\Users\\Henry\\Documents\\Programming\\Grad\\Robo-Teach\\
		lblImage.setIcon(new ImageIcon("images\\Pictures\\TitlePicture.png"));
		lblImage.setBounds(0, 0, 141, 172);
		mainPanel.add(lblImage);
		
		//Login Event
		class LoginEvent implements ActionListener{

			public void actionPerformed(ActionEvent event) {
				
				lblError.setVisible(false);
				
				User.authenticate_user(textUsername.getText(), String.valueOf(textPassword.getPassword()));
				System.out.println(textUsername.getText()+ ", " + String.valueOf(textPassword.getPassword()) + " : " + User.isAuthenticated());
				if(User.isAuthenticated()){
					
					MainWindow.start();
					((JFrame) ((JComponent) event.getSource()).getTopLevelAncestor()).dispose();
				}
				else
				{
					User.logout();
					lblError.setVisible(true);
					lblError.setText("Login Failed.");
				}
			}
		}
		
		textPassword.addActionListener(new LoginEvent());
		btnLogin.addActionListener(new LoginEvent());

		
		setVisible(true);
	}
	
}
