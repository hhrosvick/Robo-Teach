package api;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class APIRobotControllerWindow extends JFrame {

	
	public static void main(String[] args) {
		
		APIRobotControllerWindow Controller = new APIRobotControllerWindow();
		API api = new API();
		Controller.robot = api.loadRobotController();
	}

	
	public RobotControl robot = null;
	
	JButton btnSTOP = new JButton("X");
	JButton btnFWD = new JButton("^");
	JButton btnBWD = new JButton("v");
	JButton btnROT_LEFT = new JButton("<");
	JButton btnROT_RIGHT = new JButton(">");
	JButton btnTURN_LEFT = new JButton("<-|");
	JButton btnTURN_RIGHT = new JButton("|->");
	
	
	JPanel panel = new JPanel();
	JTextField speed = new JTextField(15);
	JTextField radius = new JTextField(15);
	
	public APIRobotControllerWindow() {
		super("Contoller");
		
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
		
		int h = 60;
		int w = 60;
		
		x = 10;
		y = 10;
		
		btnFWD.setBounds(x+(w * 1), y+(h * 0), h, w);
		btnBWD.setBounds(x+(w * 1), y+(h * 2), h, w);
		btnSTOP.setBounds(x+(w * 1), y+(h * 1), h, w);
		btnROT_LEFT.setBounds(x+(w * 0), y+(h * 2), h, w);
		btnROT_RIGHT.setBounds(x+(w * 2), y+(h * 2), h, w);
		btnTURN_LEFT.setBounds(x+(w * 0), y+(h * 1), h, w);
		btnTURN_RIGHT.setBounds(x+(w * 2), y+(h * 1), h, w);
		speed.setBounds(x+(w * 3), y+(h * 0), h, w);
		radius.setBounds(x+(w*3), y+(h * 1), h, w);
		
		panel.add(btnFWD);
		panel.add(btnSTOP);
		panel.add(btnBWD);
		panel.add(btnROT_LEFT);
		panel.add(btnROT_RIGHT);
		panel.add(btnTURN_LEFT);
		panel.add(btnTURN_RIGHT);
		panel.add(speed);
		panel.add(radius);
	
		getContentPane().add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		btnFWD.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {				
			setValues();
			robot.forward();
		}});
		
		btnSTOP.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {				
			setValues();
			robot.stop();
		}});

		btnBWD.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {				
			setValues();
			robot.backward();
		}});

		btnROT_LEFT.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {				
			setValues();
			robot.rotate_LEFT();
		}});

		btnROT_RIGHT.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {				
			setValues();
			robot.rotate_RIGHT();
		}});

		btnTURN_LEFT.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {				
			setValues();
			robot.turn_LEFT();
		}});

		btnTURN_RIGHT.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {				
			setValues();
			robot.turn_RIGHT();
		}});

	}
	
	private void setValues(){
		
		int SPEED = 50;
		int RADIUS = 50;
		
		try{
			SPEED = Integer.parseInt(speed.getText());
			RADIUS = Integer.parseInt(radius.getText());
			
		}catch (NumberFormatException e){
			SPEED = 50;
			RADIUS = 50;
		}
		
		robot.setSpeed(SPEED);
		robot.setTurningRadius(RADIUS);
		speed.setText(String.valueOf(SPEED));
		radius.setText(String.valueOf(RADIUS));
		
	}
	
	
	
}
