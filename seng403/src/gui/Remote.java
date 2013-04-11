package gui;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import api.RobotControl;
import javax.swing.event.ChangeEvent;

public class Remote {

	private JFrame frame;
	public static RobotControl robot;
	private JLabel radiusLabel;
	private JLabel speedLabel;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Remote(boolean sim) 
	{
		if(sim)
			Remote.robot = RoboTeach.getAPI_Interface().loadSimulatorController();
		else
			Remote.robot = RoboTeach.getAPI_Interface().loadRobotController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		speedLabel = new JLabel("Robot Speed");
		speedLabel.setBounds(10, 206, 117, 16);
		frame.getContentPane().add(speedLabel);
		
		radiusLabel = new JLabel("Turn Radius");
		radiusLabel.setBounds(267, 206, 109, 16);
		frame.getContentPane().add(radiusLabel);
			
		ImageIcon stop = new ImageIcon("Pictures/STOP.png");
		JButton btnStop = new JButton(stop);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robot.stop();
			}
		});
		btnStop.setBounds(180, 90, 75, 75);
		frame.getContentPane().add(btnStop);
		
		ImageIcon rightTurn = new ImageIcon("Pictures/RightTurn.png");
		JButton TurnRight = new JButton(rightTurn);
		TurnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				robot.turn_RIGHT();
			}
		});
		TurnRight.setBounds(255, 90, 75, 75);
		frame.getContentPane().add(TurnRight);
		
		ImageIcon leftTurn = new ImageIcon("Pictures/LeftTurn.png");
		JButton TurnLeft = new JButton(leftTurn);
		TurnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robot.turn_LEFT();
			}
		});
		TurnLeft.setBounds(105, 90, 75, 75);
		frame.getContentPane().add(TurnLeft);
		
		ImageIcon GOB = new ImageIcon("Pictures/GOB.png");
		JButton Down = new JButton(GOB);
		Down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robot.backward();
			}
		});
		Down.setBounds(180, 165, 75, 75);
		frame.getContentPane().add(Down);
		
		ImageIcon GOF = new ImageIcon("Pictures/GOF.png");
		JButton Up = new JButton(GOF);
		Up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robot.forward();
			}
		});
		Up.setBounds(180, 15, 75, 75);
		frame.getContentPane().add(Up);
		
		ImageIcon RotateCCW = new ImageIcon("Pictures/RotateCCW.png");
		JButton RotateLeft = new JButton(RotateCCW);
		RotateLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robot.rotate_LEFT();
			}
		});
		RotateLeft.setBounds(105, 15, 75, 75);
		frame.getContentPane().add(RotateLeft);
		
		ImageIcon RotateCW = new ImageIcon("Pictures/RotateCW.png");
		JButton RotateRight = new JButton(RotateCW);
		RotateRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robot.rotate_RIGHT();
			}
		});
		RotateRight.setBounds(255, 15, 75, 75);
		frame.getContentPane().add(RotateRight);
		
		final JSlider radiusSlider = new JSlider();
		radiusSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				robot.setTurningRadius(radiusSlider.getValue());
				radiusLabel.setText("Turn Radius " + radiusSlider.getValue());
			}
		});
		radiusSlider.setBounds(258, 183, 174, 23);
		frame.getContentPane().add(radiusSlider);
		radiusSlider.setMaximum(500);
		radiusSlider.setMinimum(0);
		
		final JSlider speedSlider = new JSlider();
		speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				robot.setSpeed(speedSlider.getValue());
				speedLabel.setText("Robot Speed " + speedSlider.getValue());
			}
		});
		speedSlider.setBounds(0, 183, 174, 23);
		frame.getContentPane().add(speedSlider);
		speedSlider.setMaximum(500);
		speedSlider.setMinimum(0);
		

		
	}
	public void OpenWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setTitle("Remote");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
