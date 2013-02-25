package gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class ChallengesTab implements ActionListener {
	private JPanel ChallengesTab;
	private JButton StartLesson;
	private String exercise;
	JTree ChallengeTree = new JTree();
	private JEditorPane textPane = null;

public ChallengesTab()
{
	ChallengesTab = new JPanel();
	initialize();
}

public JPanel initialize()
{
	ChallengesTab.setLayout(new FormLayout(new ColumnSpec[] {
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.DEFAULT_COLSPEC,
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("default:grow"),},
		new RowSpec[] {
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
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			RowSpec.decode("default:grow"),}));

		
		final JScrollPane challengePane = new JScrollPane();
		textPane = new JEditorPane();
		textPane.setEditable(false);
		ChallengesTab.add(challengePane, "4, 2, 1, 10, fill, fill");
		
		
		 // Potential Begin Challenge in New window button
		JButton BeginChallenge = new JButton("Begin Challenge");
		BeginChallenge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Create .png Challenge images E1.png etc.
				ChallengeWindow challWindow = new ChallengeWindow(exercise);
				challWindow.OpenWindow();
			}
		});
		
		BeginChallenge.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		ChallengesTab.add(BeginChallenge, "2,2");
		
		
		DefaultMutableTreeNode Challenges = new DefaultMutableTreeNode("Challenges");
		createNodes(Challenges);
		
		ChallengeTree = new JTree(Challenges);
		ChallengeTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		ChallengeTree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e){
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)ChallengeTree.getLastSelectedPathComponent();
				if (node == null)
					return; // no node selected
				
				else // display seleted node
				{
					Object nodeInfo = node.getUserObject();
					exercise = nodeInfo.toString();
					
					String description = insertLabel(exercise);
					JLabel lblNewLabel = new JLabel(description);
					challengePane.setViewportView(lblNewLabel);
				}	
			}
		});
		
		ChallengesTab.add(ChallengeTree, "2,4");
		return ChallengesTab;
}
	private void createNodes(DefaultMutableTreeNode challenges) 
	{
		DefaultMutableTreeNode Easy = null;
		DefaultMutableTreeNode Medium = null;
		DefaultMutableTreeNode Hard = null;
		DefaultMutableTreeNode exercises = null;
		
		
		// Add Chapters
		Easy = new DefaultMutableTreeNode("Easy");
		challenges.add(Easy);
		//add exercise nodes
		exercises = new DefaultMutableTreeNode("E1");
		Easy.add(exercises);
		
		exercises = new DefaultMutableTreeNode("E2");
		Easy.add(exercises);
		
		exercises = new DefaultMutableTreeNode("E3");
		Easy.add(exercises);
		
		Medium = new DefaultMutableTreeNode("Medium");
		challenges.add(Medium);
		
		exercises = new DefaultMutableTreeNode("M1");
		Medium.add(exercises);
		
		exercises = new DefaultMutableTreeNode("M2");
		Medium.add(exercises);
		
		exercises = new DefaultMutableTreeNode("M3");
		Medium.add(exercises);
		
		Hard = new DefaultMutableTreeNode("Hard");
		challenges.add(Hard);
		
		exercises = new DefaultMutableTreeNode("H1");
		Hard.add(exercises);
		
		exercises = new DefaultMutableTreeNode("H2");
		Hard.add(exercises);
		
		exercises = new DefaultMutableTreeNode("H3");
		Hard.add(exercises);
	}
	
	private String insertLabel(String exercise)
	{
		String returnDescription = null;
		
		// Exercise descriptions, potentially pulled from a .txt document
		// ** EASY EXERCISES ** //
		if (exercise == "E1")
		{
			returnDescription = ("Description for easy challenge 1.");
		}
		
		if (exercise == "E2")
		{
			returnDescription = ("Description for easy challenge 2.");
		}
		
		if (exercise == "E3")
		{
			returnDescription = ("Description for easy challenge 3.");
		}
		
		
		// ** MEDIUM Exercises ** //
		if (exercise == "M1")
		{
			returnDescription = ("Description for medium challenge 1.");
		}
				
		if (exercise == "M2")
		{
			returnDescription = ("Description for medium challenge 2.");
		}
				
		if (exercise == "M3")
		{
			returnDescription = ("Description for medium challenge 3.");
		}		
				
		// ** HARD EXERCISES ** //
		if (exercise == "H1")
		{
			returnDescription = ("Description for hard challenge 1.");
		}
		
		if (exercise == "H2")
		{
			returnDescription = ("Description for hard challenge 2.");
		}
		
		if (exercise == "H3")
		{
			returnDescription = ("Description for hard challenge 3.");
		}		
				
		// Return challenge description
		return returnDescription;
	}

	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
	}
}