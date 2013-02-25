package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class ChallengeWindow {

	private static JFrame frame;
	private static String ChallengeName;
// constructor takes lesson name argument
	public ChallengeWindow(String LN) 
	{
		ChallengeName = LN;
		initialize();
	}
// creates the new window
	public static void OpenWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Test window = new Test();
					frame.setVisible(true);
					frame.setTitle(ChallengeName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
// initializes components
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		// creates the image string and image icon
		String imgStr = "Challenges/" + ChallengeName + ".png";
		final ImageIcon LessonPicture = new ImageIcon(imgStr);
		// loads the scrollPane
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		// creates the label with the specified image icon
		JLabel LessonLabel = new JLabel("", LessonPicture, JLabel.CENTER);
		scrollPane.setViewportView(LessonLabel);
	}
}
