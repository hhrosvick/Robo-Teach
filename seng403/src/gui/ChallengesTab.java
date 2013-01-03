package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ChallengesTab implements ActionListener {
	private JPanel ChallengesTab;
	private JButton StartLesson;


public ChallengesTab()
{
	ChallengesTab = new JPanel();
	
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

		
		JScrollPane challengePane = new JScrollPane();
		ChallengesTab.add(challengePane, "4, 2, 1, 31, fill, fill");
		
		JButton BeginChallenge = new JButton("Begin Challenge");
		BeginChallenge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		
		
		BeginChallenge.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		ChallengesTab.add(BeginChallenge, "2,2");
		
		JTree ChallengeTree = new JTree();
		ChallengeTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Challenges") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Easy");
						node_1.add(new DefaultMutableTreeNode("E: 1.0"));
						node_1.add(new DefaultMutableTreeNode("E: 2.0"));
						node_1.add(new DefaultMutableTreeNode("E: 3.0"));
						node_1.add(new DefaultMutableTreeNode("E: 4.0"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Medium");
						node_1.add(new DefaultMutableTreeNode("M: 1.0"));
						node_1.add(new DefaultMutableTreeNode("M: 2.0"));
						node_1.add(new DefaultMutableTreeNode("M: 3.0"));
						node_1.add(new DefaultMutableTreeNode("M: 4.0"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Hard");
						node_1.add(new DefaultMutableTreeNode("H: 1.0"));
						node_1.add(new DefaultMutableTreeNode("H: 2.0"));
						node_1.add(new DefaultMutableTreeNode("H: 3.0"));
						node_1.add(new DefaultMutableTreeNode("H: 4.0"));
					add(node_1);
				}
			}
		));
		ChallengesTab.add(ChallengeTree, "2,4");
		
		JLabel challengeLabel = new JLabel("Challenge Info");
		challengePane.setViewportView(challengeLabel);
		return ChallengesTab;
}


	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
	}
}