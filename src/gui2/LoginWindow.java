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
		
		//Frame setup
		setTitle("Robo-Teach: Login");
		setResizable(false);
		setSize(300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
		int x = (screenSize.width - getWidth()) / 2;  
		int y = (screenSize.height - getHeight()) / 2;  
		setLocation(x,y);

		//Panel setup
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(151, 11, 133, 14);
		mainPanel.add(lblUsername);
		
		final JTextField textUsername = new JTextField(15);
		textUsername.setBounds(151, 38, 133, 20);
		mainPanel.add(textUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(151, 71, 133, 14);
		mainPanel.add(lblPassword);
		
		final JPasswordField textPassword = new JPasswordField(15);
		textPassword.setBounds(151, 98, 133, 20);
		mainPanel.add(textPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(151, 131, 133, 23);
		mainPanel.add(btnLogin);
	
		JLabel lblImage = new JLabel("Robo-Teach Logo");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon("C:\\Users\\Henry\\Documents\\Programming\\Grad\\Robo-Teach\\images\\Pictures\\TitlePicture.png"));
		lblImage.setBounds(0, 0, 141, 172);
		mainPanel.add(lblImage);
		
		//Login Event
		class LoginEvent implements ActionListener{

			public void actionPerformed(ActionEvent event) {
				
				User.authenticate_user(textUsername.getText(), String.valueOf(textPassword.getPassword()));
				System.out.println(textUsername.getText()+ ", " + String.valueOf(textPassword.getPassword()) + " : " + User.isAuthenticated());
				if(User.isAuthenticated())
					MainWindow.start();
				else
					System.out.println("Error");
			}
		}
		
		textPassword.addActionListener(new LoginEvent());
		btnLogin.addActionListener(new LoginEvent());

		
		setVisible(true);
	}
}
