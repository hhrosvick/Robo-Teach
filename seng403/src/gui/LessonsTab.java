package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import api.API;
import api.API_Interface;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class LessonsTab {

	private JPanel LessonsTab;
	private JFrame jframe;
	private String Selection;
	private int Chapter;
	private int Lesson;
	private boolean LessonSelected;
	private Map<String,String> currentProgress;
	private int userChapter;
	private int UserID;
	private JButton StartLessonButton;
	private int result = 0;
	private Quizzes q = null;
	API_Interface api = null;

	/**
	 * Create the application.
	 */
	public LessonsTab(int UserID, JFrame frame) 
	{
		jframe = frame;
		
		this.UserID = UserID;
		LessonsTab = new JPanel();
		
		try {
			api = new API();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialiyze the contents of the frame.
	 * @return 
	 * 
	 */
	public JPanel initialize() {
		LessonsTab.setBounds(100, 100, 450, 300);
		LessonsTab.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;pref)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(39dlu;pref)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("8dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(7dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(9dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(9dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,}));
		// Sends request for lesson slides to database
		StartLessonButton = new JButton("Start Lesson");
		
		StartLessonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//THIS PART HAS TO BE UNCOMMENTED ONCE getUserType is done
				/*if(LessonSelected && api.getUserType(UserID) == 1) //if a teacher
				{
					LessonWindow NewWindow = new LessonWindow(Chapter, Lesson, Selection);
					NewWindow.OpenWindow();
				}
				else if(LessonSelected && api.getUserType(UserID) == 2 )*/ //if a student
				if(LessonSelected)
				{
					if(Chapter <= (userChapter-1))
					{
						LessonWindow NewWindow = new LessonWindow(Chapter, Lesson, Selection);
						NewWindow.OpenWindow();
					}
					else
					{
						//JOptionPane.showMessageDialog(LessonsTab,"Lesson Currently Not Available!");
						//Custom button text
						Object[] options = {"Quiz",
						                    "Teacher's Approval",
						                    "Cancel"};
						int n = JOptionPane.showOptionDialog(LessonsTab,
						    "Lesson Currently Not Available! \nWhat would you like to do?",
						    "Information",
						    JOptionPane.YES_NO_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,
						    options,
						    null);
						
						if(n==0)
						{
							doQuiz(Chapter);
						}
						else if(n == 1)
						{
							approval();
						}
					
					}
				}
				else 
				{
					if (Chapter > (userChapter-1))
					{
						Object[] options = {"Quiz", "Teacher's Approval","Cancel"};
						int n = JOptionPane.showOptionDialog(LessonsTab,
							"Lesson Currently Not Available! \nWhat would you like to do?",
							"Information",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,null);
					
						if(n==0)
						{
							doQuiz(Chapter);
						}
						else if(n == 1)
						{
							approval();
						}
					}
				
				}		
			}

		});
		LessonsTab.add(StartLessonButton, "2, 2, 3, 1");
		
		JScrollPane PreviewPane = new JScrollPane();
		LessonsTab.add(PreviewPane, "6, 2, 17, 15, fill, fill");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		PreviewPane.setViewportView(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		String imgStr = "Lessons/Lessons.png";
		final ImageIcon LessonsTitle = new ImageIcon(imgStr);
		final JLabel LessonPreviewLabel = new JLabel("", LessonsTitle, JLabel.CENTER);
		panel.add(LessonPreviewLabel);
		
		JScrollPane TreePane = new JScrollPane();
		LessonsTab.add(TreePane, "2, 4, 3, 13, fill, fill");
		
		final JTree LessonsTree = new JTree();
		LessonsTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Lessons") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Chapter 1");
						node_1.add(new DefaultMutableTreeNode("Lesson I"));
						node_1.add(new DefaultMutableTreeNode("Lesson II"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Chapter 2");
						node_1.add(new DefaultMutableTreeNode("Lesson III"));
						node_1.add(new DefaultMutableTreeNode("Lesson IV"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Chapter 3");
						node_1.add(new DefaultMutableTreeNode("Lesson V"));
						node_1.add(new DefaultMutableTreeNode("Lesson VI"));
					add(node_1);
				}
			}
		));
		TreePane.setViewportView(LessonsTree);
		
		LessonsTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) LessonsTree.getLastSelectedPathComponent();
				
				//constantly track user progresss
				currentProgress = api.getUserProgress(UserID);
				userChapter = Integer.parseInt(currentProgress.get("chapter"));
				
				//Creates the string for lesson preview, preview will be local for quick response time
				Object nodeInfo = node.getUserObject();
				Selection = nodeInfo.toString();
				String imgStr = "Lessons/" + Selection + ".png";
				System.out.println(imgStr);
				final ImageIcon LessonPreview = new ImageIcon(imgStr);
				LessonPreviewLabel.setIcon(LessonPreview);
				
				//Creates the integers for lesson selection for communication with API
				LessonSelected = true;
				StartLessonButton.setText("Start Lesson");
				
				try
				{
				Chapter = ((node.getParent()).getParent()).getIndex(node.getParent());
				Lesson = (node.getParent()).getIndex(node);	
				}catch(NullPointerException NPE){ 
					LessonSelected = false; 
				}
				
				if(LessonSelected)
				{
					System.out.println(Chapter + "." + Lesson);
				}
				else
				{
					try
					{
						Chapter = node.getParent().getIndex(node);
						System.out.println("try " + Chapter);
						if( Chapter > (userChapter-1))
							StartLessonButton.setText("Quiz/Teacher's Approval");
					}catch(NullPointerException NPE){ 
						LessonSelected = false; 
					}
				}	

			}
		});
		return LessonsTab;
	}
	
	public void doQuiz(int ch) {
		// TODO Auto-generated method stub
		 q = new Quizzes(ch, jframe, this);
	}

	public void result(int r)
	{
		if(r < 3)
		{
			JOptionPane.showMessageDialog(LessonsTab,"Failed! You got only " + r + " out of the 5 questions correctly.");
		} 
		else if(r>= 3)
		{
			StartLessonButton.setText("Start Lesson");
			
			JOptionPane.showMessageDialog(LessonsTab,"Access to Chapter " + (Chapter+1) + " granted! You got " + r + " out of the 5 questions correctly. ");
			try {
				api.setUserChapter(UserID, Chapter+1);
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void approval() {
		// TODO Auto-generated method stub
		
	}
	
	public void setUserID(int id)
	{
		UserID = id;
	}
}
