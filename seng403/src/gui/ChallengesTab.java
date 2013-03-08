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

public class ChallengesTab {

	private JPanel ChallengesTab;
	private String Selection;
	private int Tier;
	private int Challenge;
	private boolean ChallengeSelected;

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
		JButton StartLessonButton = new JButton("Start Lesson");
		StartLessonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ChallengeSelected)
				{
					ChallengeWindow NewWindow = new ChallengeWindow(Tier, Challenge, Selection);
					NewWindow.OpenWindow();
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
		
		String imgStr = "Challenges/ChallengeTitle.png";
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
					node_1 = new DefaultMutableTreeNode("Easy Tier");
						node_1.add(new DefaultMutableTreeNode("Challenge I"));
						node_1.add(new DefaultMutableTreeNode("Challenge II"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Challenging Tier");
						node_1.add(new DefaultMutableTreeNode("Challenge III"));
						node_1.add(new DefaultMutableTreeNode("Challenge IV"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Difficult Tier");
						node_1.add(new DefaultMutableTreeNode("Challenge V"));
						node_1.add(new DefaultMutableTreeNode("Challenge VI"));
					add(node_1);
				}
			}
		));
		TreePane.setViewportView(LessonsTree);
		
		LessonsTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) LessonsTree.getLastSelectedPathComponent();	
				
				//Creates the string for challenge preview, preview will be local for quick response time
				Object nodeInfo = node.getUserObject();
				Selection = nodeInfo.toString();
				String imgStr = "Challenges/" + Selection + ".png";
				final ImageIcon LessonPreview = new ImageIcon(imgStr);
				LessonPreviewLabel.setIcon(LessonPreview);
				
				//Creates the integers for lesson selection for communication with API
				ChallengeSelected = true;
				try
				{
				Tier = ((node.getParent()).getParent()).getIndex(node.getParent());
				Challenge = (node.getParent()).getIndex(node);	
				}catch(NullPointerException NPE){ ChallengeSelected = false; }
				if(ChallengeSelected)
					System.out.println(Tier + "." + Challenge);
			}
		});
		return ChallengesTab;
	}
}
