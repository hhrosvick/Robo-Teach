package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.util.Map;

import api.API;
import api.API_Interface;

public class ChallengesTab {

	private JPanel ChallengesTab;
	private String Selection;
	private int Tier;
	private int Challenge;
	private boolean ChallengeSelected;
	private int userChapter;
	Map<String, String> currentProgress;

	/**
	 * Create the application.
	 */
	public ChallengesTab() 
	{
		ChallengesTab = new JPanel();
		initialize();
	}

	/**
	 * Initialiyze the contents of the frame.
	 * @return 
	 */
	public JPanel initialize() {
		ChallengesTab.setBounds(100, 100, 450, 300);
		ChallengesTab.setLayout(new FormLayout(new ColumnSpec[] {
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
		JButton StartLessonButton = new JButton("Start Challenge");
		StartLessonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ChallengeSelected && RoboTeach.getAPI_Interface().getUserType(RoboTeach.getUserID()) == 1) //if a teacher
				{
					ChallengeWindow NewWindow = new ChallengeWindow(Tier, Challenge, Selection);
					NewWindow.OpenWindow();
				}
				else if(ChallengeSelected && RoboTeach.getAPI_Interface().getUserType(RoboTeach.getUserID()) == 2 ) //if a student
				if(ChallengeSelected)
				{
					if(Tier <= (userChapter-1))
					{
						ChallengeWindow NewWindow = new ChallengeWindow(Tier, Challenge, Selection);
						NewWindow.OpenWindow();
					}
				}
			}
		});
		ChallengesTab.add(StartLessonButton, "2, 2, 3, 1");
		
		JScrollPane PreviewPane = new JScrollPane();
		ChallengesTab.add(PreviewPane, "6, 2, 17, 15, fill, fill");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		PreviewPane.setViewportView(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		String imgStr = "Challenges/Challenges.png";
		final ImageIcon LessonsTitle = new ImageIcon(imgStr);
		final JLabel LessonPreviewLabel = new JLabel("", LessonsTitle, JLabel.CENTER);
		panel.add(LessonPreviewLabel);
		
		JScrollPane TreePane = new JScrollPane();
		ChallengesTab.add(TreePane, "2, 4, 3, 13, fill, fill");
		
		final JTree LessonsTree = new JTree();
		LessonsTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Challenges") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Easy");
						node_1.add(new DefaultMutableTreeNode("Arena Tag"));
						node_1.add(new DefaultMutableTreeNode("Hide & Seek"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Medium");
						node_1.add(new DefaultMutableTreeNode("iRobot Racing"));
						node_1.add(new DefaultMutableTreeNode("New Challenge"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Hard");
						node_1.add(new DefaultMutableTreeNode("Musical iRobots"));
						node_1.add(new DefaultMutableTreeNode("New Challenge"));
					add(node_1);
				}
			}
		));
		TreePane.setViewportView(LessonsTree);
		
		LessonsTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) LessonsTree.getLastSelectedPathComponent();	
				
				
				//constantly track user progresss
				if(RoboTeach.getAPI_Interface().getUserType(RoboTeach.getUserID()) == 1)
					currentProgress = RoboTeach.getAPI_Interface().getUserProgress(RoboTeach.getUserID());
				else
					currentProgress = null;
				if(currentProgress != null)
					userChapter = Integer.parseInt(currentProgress.get("chapter"));
				else
					userChapter = 100;
				
				
				// Information regarding PNG images, depending on what is selected:
				// Folder selected Challenges/Easy, Challenges/Medium, Challenges/Hard
				// - Displays a PNG describing the difficulty of the challenges
				
				// Challenge selected Challenges/Arena Tag Challenges/Hide & Seek
				// - Displays a PNG describing the challenge
				
				// "Start Challenge" button selected:
				// - Displays a PNG describing the instructions of the challenge
				//final ImageIcon ChallengePreview = API.getChallenge(1,0,0) potentially called;
				
				//Creates the integers for challenge selection for communication with API
				ChallengeSelected = true;
				try
				{
					Tier = ((node.getParent()).getParent()).getIndex(node.getParent());
					Challenge = (node.getParent()).getIndex(node);
					
					final ImageIcon LessonPreview = RoboTeach.getAPI_Interface().getChallenge(Tier+1, Challenge+1, false);
					LessonPreviewLabel.setIcon(LessonPreview);
				
				}catch(NullPointerException NPE){ ChallengeSelected = false; }
				
				if(ChallengeSelected)
				{
					
					final ImageIcon LessonPreview = RoboTeach.getAPI_Interface().getChallenge(Tier+1, Challenge+1, false);
					LessonPreviewLabel.setIcon(LessonPreview);
				}
				else
				{
					try
					
					{
						Tier = node.getParent().getIndex(node);
						final ImageIcon LessonPreview = RoboTeach.getAPI_Interface().getChallenge(Tier+1, 0, false);
						LessonPreviewLabel.setIcon(LessonPreview);
						
					}catch(NullPointerException NPE){ 
						final ImageIcon LessonPreview = new ImageIcon("Challenges/Challenges.png");
						LessonPreviewLabel.setIcon(LessonPreview);
					}
				}
			}
		});
		return ChallengesTab;
	}
}
