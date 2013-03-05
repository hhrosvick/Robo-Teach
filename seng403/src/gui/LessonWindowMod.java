package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class LessonWindowMod {

	private static int MAX_SECTION = 20;
	private JFrame frame;
	private static String LessonName;
	private int sectionNumber;
	private String originalName;
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
		frame.setBounds(100, 100, 450, 300);
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
		
		JPanel SlidePanel = new JPanel();
		frame.getContentPane().add(SlidePanel, "2, 2, 1, 13, fill, fill");
		SlidePanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		SlidePanel.add(scrollPane, BorderLayout.CENTER);
		
		final JLabel SlideLabel = new JLabel("");
		scrollPane.setViewportView(SlideLabel);
		
		JPanel ButtonPanel = new JPanel();
		frame.getContentPane().add(ButtonPanel, "2, 16, fill, fill");
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton PreviousButton = new JButton("Previous");
		PreviousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sectionNumber > 0)
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

	public void OpenWindow() {
		LessonWindowMod window = new LessonWindowMod(LessonName);
		window.frame.setVisible(true);
	}
}
