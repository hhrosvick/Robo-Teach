package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Button;
import java.awt.Canvas;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LessonWindow{

	private static JFrame frame;
	private static String LessonName;
	private final JButton btnPrevious = new JButton("Previous");
	private final JButton btnNext = new JButton("Next");
	private JFrame frame_1;
	private int sectionNumber;
	private String originalName;
	
// constructor takes lesson name argument
	public LessonWindow(String LN) 
	{
		LessonName = LN;
		originalName=LN;
		frame_1 = new JFrame();
		sectionNumber=0;
		initialize();
		
	}
// creates the new window
	public static void OpenWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Test window = new Test();
					getFrame().setVisible(true);
					getFrame().setTitle(LessonName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
// initializes components
	private void initialize() {
		
		setFrame(frame_1);
		frame_1.getContentPane().setLayout(new BoxLayout(frame_1.getContentPane(), BoxLayout.X_AXIS));
		frame_1.getContentPane().add(btnNext);
		frame_1.getContentPane().add(btnPrevious);
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNext){
					sectionNumber+=1;
					String nm= String.valueOf(sectionNumber);
					LessonName=originalName+nm;
					frame_1.getContentPane().removeAll();
					OpenWindow();
					initialize();
				}
			}
		});
		
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnPrevious){
					sectionNumber-=1;
					String nm= String.valueOf(sectionNumber);
					LessonName=originalName+nm;
					frame_1.getContentPane().removeAll();
					OpenWindow();
					initialize();
				}
			}
		});
		
		System.out.println(sectionNumber);
		
		getFrame().setBounds(100, 100, 450, 300);
		// creates the image string and image icon
		String imgStr = "Lessons/" + LessonName + ".png";
		final ImageIcon LessonPicture = new ImageIcon(imgStr);
		// loads the scrollPane
		JScrollPane scrollPane = new JScrollPane();
		getFrame().getContentPane().add(scrollPane, BorderLayout.CENTER);
		// creates the label with the specified image icon
		
		JLabel LessonLabel = new JLabel("", LessonPicture, JLabel.CENTER);		
		LessonLabel.setOpaque(true);
		Color white = new Color(255,255,255);
		LessonLabel.setBackground(white);
		scrollPane.setViewportView(LessonLabel);
		frame_1.pack();
	}
	
	public static JFrame getFrame() {
		return frame;
	}
	
	public static void setFrame(JFrame frame) {
		LessonWindow.frame = frame;
	}
}
