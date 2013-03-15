package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Login extends JFrame {

	public static void main(String[] args) {
		Login frameTable = new Login(new JFrame());
	}

	JFrame frame;
	JButton btnLogin = new JButton("Login");
	JPanel panel = new JPanel();
	JTextField userName = new JTextField(15);
	JPasswordField password = new JPasswordField(15);
	
	Login(JFrame frame1){
		super("Login");
		
		//to disable the main frame
		frame = frame1;
		frame.setEnabled(false);
		
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogin();
	}
	
	@SuppressWarnings("deprecation")
	public void actionlogin(){
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				String uname = userName.getText();
				String pass = password.getText();
				
				//CHECKING VALID USERNAME AND PASSWORD
				if(uname.equals("test") && pass.equals("1234")) {
					//enable main frame now
					frame.setEnabled(true);
					frame.setUserID(Integer.parseInt(uname));
					dispose();
				} 
				else 
				{
					System.out.println("hello");
					JOptionPane.showMessageDialog(panel,"Incorrect Username / Password!");
					userName.setText("");
					password.setText("");
					userName.requestFocus();
				}

			}
		});
	}
}