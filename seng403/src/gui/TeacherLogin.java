package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import api.Authenticator;


public class TeacherLogin extends Login
{
	private int Chapter;
	private LessonsTab tab;
	private JFrame jframe;
	
	TeacherLogin(JFrame frame1, RoboTeach myProg) {
		super(frame1, myProg);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("deprecation")
	TeacherLogin(int Chapter, JFrame frame, RoboTeach myProg, LessonsTab tab)
	{
		super(frame, myProg);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
		        jframe.enable();
		        jframe.toFront();
			}
			@Override
			public void windowClosed(WindowEvent e) {
				setVisible(false);
		        jframe.enable();
		        jframe.toFront();
			}
		});
		
		this.Chapter = Chapter;
		this.tab = tab;
		jframe = frame;
		
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
					
					//int type = api.getUserType(UserID);
					//SHOULD BE CHANGED TO int type = api.getUserType(UserID);
					int type = Authenticator.auth(uname, pass);
					
					if(type == 1)
					{
						jframe.enable();
		        		jframe.toFront();
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