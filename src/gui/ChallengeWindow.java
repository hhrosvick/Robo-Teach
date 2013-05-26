package gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class ChallengeWindow {

	private static JFrame frame;
	private static String ChallengeName;
	private int Tier;
	private int Challenge;
	// constructor takes lesson name argument
	public ChallengeWindow(int t, int c, String CN) 
	{
		ChallengeName = CN;
		Challenge = c+1;
		Tier = t+1;
		initialize();
	}
	// creates the new window
	public void OpenWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Test window = new Test();
					getFrame().setVisible(true);
					getFrame().setTitle(ChallengeName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// initializes components
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 300);
		// creates the image string and image icon
		final ImageIcon ChallengePicture = Images.getChallenge(Tier, Challenge, true);
		// loads the scrollPane
		JScrollPane scrollPane = new JScrollPane();
		getFrame().getContentPane().add(scrollPane, BorderLayout.CENTER);
		// creates the label with the specified image icon
		JLabel ChallengeLabel = new JLabel("", ChallengePicture, JLabel.CENTER);
		ChallengeLabel.setOpaque(true);
		Color white = new Color(255,255,255);
		ChallengeLabel.setBackground(white);
		scrollPane.setViewportView(ChallengeLabel);
	}
	public static JFrame getFrame() 
	{
		return frame;
	}
	public static void setFrame(JFrame frame) 
	{
		ChallengeWindow.frame = frame;
	}
}
