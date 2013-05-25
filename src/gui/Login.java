package gui;

import javax.swing.*;

import api.User;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Login extends JFrame {

	public static void main(String[] args) 
	{
		@SuppressWarnings("unused")
		Login frameTable = new Login();
	}

	int UserID;
	RoboTeach myRobot;
	JButton btnLogin = new JButton("Login");
	JPanel panel = new JPanel();
	JTextField userName = new JTextField(15);
	JPasswordField password = new JPasswordField(15);
	
	Login() {
		super("Login");
		
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(300,200);
		
		//get size of the screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
		
		///TO SHOW THE WINDOW IN THE CENTER OF THE SCREEN
		int x = (screenSize.width - super.getWidth()) / 2;  
		int y = (screenSize.height - super.getHeight()) / 2;  
		
		setLocation(x,y);
		panel.setLayout (null); 
		userName.setBounds(103,29,150,20);
		password.setBounds(103,62,150,20);
		btnLogin.setBounds(110,100,80,20);
		
		panel.add(btnLogin);
		panel.add(userName);
		panel.add(password);
		
		getContentPane().add(panel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsername.setBounds(23, 28, 74, 20);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPassword.setBounds(23, 64, 74, 20);
		panel.add(lblPassword);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		actionlogin();
	}
	
	public void actionlogin(){
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				String uname = userName.getText();
				char[] pass = password.getPassword();
				
				User.authenticate_user(uname,String.valueOf(pass));
				
				//CHECKING VALID USERNAME AND PASSWORD
				if(User.getID() != 0) {
										
					GradesMatrix.instance.refreshMatrix();
					
					//call other function
					if(User.getType() != 2)
							GradesTab.RemoveButtonsForStudents();
					dispose();
				} 
				else 
				{
					JOptionPane.showMessageDialog(panel,"Incorrect Username / Password!");
					userName.setText("");
					password.setText("");
					userName.requestFocus();
				}

			}
		});
	}
}