package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;

import api.API;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class LessonWindow {

	private static JFrame frame;
	private static String LessonName;
	private final JLabel SlideLabel = new JLabel("");
	private int Chapter;
	private int Lesson;
	private int Slide = 0;

	public LessonWindow(int c, int l, String ln) 
	{
		LessonName = ln;
		Chapter = c+1;
		Lesson = l+1;
		initialize();
	}
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		frame.setResizable(true);
		frame.getContentPane().setBackground(Color.WHITE);
		final ImageIcon LessonPicture = API.getInstance().getLesson(Chapter, Lesson, Slide);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "2, 2, fill, fill");
		
		JPanel SlidePanel = new JPanel();
		scrollPane.setViewportView(SlidePanel);
		SlidePanel.setBackground(Color.WHITE);
		SlidePanel.add(SlideLabel);
		SlideLabel.setOpaque(true);
		SlideLabel.setBackground(Color.WHITE);
		SlideLabel.setIcon(LessonPicture);
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(ButtonPanel, "2, 4, fill, fill");
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton PreviousButton = new JButton("Previous");
		PreviousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Slide > 0)
				{
					ImageIcon image = API.getInstance().getLesson(Chapter, Lesson, --Slide);
					SlideLabel.setIcon(image);
				}	
			}
		});
		ButtonPanel.add(PreviousButton);
		
		JButton NextButton = new JButton("Next");
		NextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon image = API.getInstance().getLesson(Chapter, Lesson, ++Slide);
				if(image == null)
					image = API.getInstance().getLesson(Chapter, Lesson, --Slide);
				SlideLabel.setIcon(image);
			}
		});
		ButtonPanel.add(NextButton);
	}
	public void OpenWindow() 
	{
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
	public static Window getFrame() 
	{
		return frame;
	}
}
