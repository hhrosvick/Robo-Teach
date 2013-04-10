package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import api.Authenticator;


@SuppressWarnings("serial")
public class TeacherLogin extends Login
{
	private int Chapter;
	private LessonsTab tab;
	
	TeacherLogin(JFrame frame1, RoboTeach myProg) 
	{
		super(frame1, myProg);
	}
	TeacherLogin(int Chapter, JFrame frame, RoboTeach myProg, LessonsTab tab)
	{
		super(frame, myProg);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.Chapter = Chapter;
		this.tab = tab;
		
	}
	@SuppressWarnings("deprecation")
	public void actionlogin(){
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				String uname = userName.getText();
				String pass = password.getText();
				
				UserID = api.authenticate_user(uname,pass);
				System.out.println(UserID);
				
				//CHECKING VALID USERNAME AND PASSWORD
				if(UserID != 0) {
					
					int type = api.getUserType(UserID);
					//SHOULD BE CHANGED TO int type = api.getUserType(UserID);
					//int type = Authenticator.auth(uname, pass);
					
					if(type == 1)
					{
						frame.enable();
						frame.toFront();
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
					frame.setEnabled(true);
					JOptionPane.showMessageDialog(panel,"Login Failed!");
					dispose();
				}

			}
		});
	}
}