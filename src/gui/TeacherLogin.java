package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import api.User;

@SuppressWarnings("serial")
public class TeacherLogin extends Login
{
	private LessonsTab tab;
	
	TeacherLogin(JFrame frame1, RoboTeach myProg) 
	{
		super();
	}
	TeacherLogin(int Chapter, JFrame frame, RoboTeach myProg, LessonsTab tab)
	{
		super();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.tab = tab;
		
	}

	public void actionlogin(){
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				String uname = userName.getText();
				char[] pass = password.getPassword();
				
				User.authenticate_user(uname,String.valueOf(pass));
				System.out.println(UserID);
				
				//CHECKING VALID USERNAME AND PASSWORD
				if(UserID != 0) {
					
					//SHOULD BE CHANGED TO int type = api.getUserType(UserID);
					//int type = Authenticator.auth(uname, pass);
					
					if(User.getType() == 1)
					{
						dispose();
						tab.result2();
					}
					else
					{
						JOptionPane.showMessageDialog(panel,"Login Failed!");
					}
					
				} 
				else 
				{
					JOptionPane.showMessageDialog(panel,"Login Failed!");
					dispose();
				}

			}
		});
	}
}