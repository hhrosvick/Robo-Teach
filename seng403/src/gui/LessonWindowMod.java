package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class LessonWindowMod {

	private static int MAX_SECTION = 20;
	private static JFrame frame;
	private static String LessonName;
	private int sectionNumber;
	private String originalName;
	private final JLabel SlideLabel = new JLabel("");
	/**
	 * Create the application.
	 */
	public LessonWindowMod(String LN) 
	{
		LessonName = LN;
		originalName=LN;
		sectionNumber=0;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 825);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		String imgStr = "Lessons/" + LessonName + ".png";
		final ImageIcon LessonPicture = new ImageIcon(imgStr);
		
		JPanel SlidePanel = new JPanel();
		frame.getContentPane().add(SlidePanel, "2, 2, 1, 13, fill, fill");
		SlidePanel.setBackground(Color.WHITE);
		SlidePanel.add(SlideLabel);
		SlideLabel.setOpaque(true);
		SlideLabel.setBackground(Color.WHITE);
		SlideLabel.setIcon(LessonPicture);
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(ButtonPanel, "2, 16, fill, fill");
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton PreviousButton = new JButton("Previous");
		PreviousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sectionNumber > 1)
					sectionNumber--;
				String imgStr = "Lessons/" + LessonName + sectionNumber + ".png";
				final ImageIcon LessonPicture = new ImageIcon(imgStr);
				SlideLabel.setIcon(LessonPicture);
			}
		});
		ButtonPanel.add(PreviousButton);
		
		JButton NextButton = new JButton("Next");
		NextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sectionNumber < MAX_SECTION)
					sectionNumber++;
				String imgStr = "Lessons/" + LessonName + sectionNumber + ".png";
				final ImageIcon LessonPicture = new ImageIcon(imgStr);
				SlideLabel.setIcon(LessonPicture);
			}
		});
		ButtonPanel.add(NextButton);
	}

	public static void OpenWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Test window = new Test();
					frame.setVisible(true);
					frame.setTitle(LessonName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected static Window getFrame() {
		return frame;
	}
}
